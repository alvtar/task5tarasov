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

public class AuthorEditAction extends RegistrarAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			AuthorService authorService = factory.getService(AuthorService.class);
			Author author = authorService.findById(id);
			if(author != null) {
				request.setAttribute("author", author);
				BookService bookService = factory.getService(BookService.class);
				List<Book> books = bookService.findByAuthor(id);
				request.setAttribute("books", books);
			}
		} catch(NumberFormatException e) {}
		return null;
	}
}
