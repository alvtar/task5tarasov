package action.registrar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import service.AuthorService;
import service.BookService;
import domain.Author;
import domain.Book;
import exception.PersistentException;

public class BookListAction extends RegistrarAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Integer identity = (Integer)request.getAttribute("identity");
		if(identity == null) {
			try {
				identity = Integer.parseInt(request.getParameter("identity"));
			} catch(NumberFormatException e) {}
		}
		if(identity != null) {
			AuthorService authorService = factory.getService(AuthorService.class);
			Author author = authorService.findByIdentity(identity);
			request.setAttribute("author", author);
		}
		BookService bookService = factory.getService(BookService.class);
		List<Book> books = bookService.findByAuthor(identity);
		request.setAttribute("books", books);
		return null;
	}
}
