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
		Integer id = (Integer)request.getAttribute("id");
		if(id == null) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
			} catch(NumberFormatException e) {}
		}
		if(id != null) {
			AuthorService authorService = factory.getService(AuthorService.class);
			Author author = authorService.findById(id);
			request.setAttribute("author", author);
		}
		BookService bookService = factory.getService(BookService.class);
		List<Book> books = bookService.findByAuthor(id);
		request.setAttribute("books", books);
		return null;
	}
}
