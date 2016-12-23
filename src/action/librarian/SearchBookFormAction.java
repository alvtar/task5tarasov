package action.librarian;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Author;
import domain.Book;
import service.AuthorService;
import service.BookService;
import action.Action;
import exception.PersistentException;

public class SearchBookFormAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/search/book/form.jsp", false);
		Integer readerIdentity = (Integer)request.getAttribute("readerIdentity");
		if(readerIdentity != null) {
			forward.getAttributes().put("readerIdentity", readerIdentity);
		}
		AuthorService authorService = factory.getService(AuthorService.class);
		Map<Author, Integer> authors = authorService.findAllWithNumberOfBooks();
		request.setAttribute("authors", authors);
		BookService bookService = factory.getService(BookService.class);
		List<Book> books = bookService.findByAuthor(null);
		if(!books.isEmpty()) {
			request.setAttribute("numberOfBooksWithoutAuthor", books.size());
		}
		return forward;
	}
}
