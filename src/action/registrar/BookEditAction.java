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

public class BookEditAction extends RegistrarAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		AuthorService authorService = factory.getService(AuthorService.class);
		List<Author> authors = authorService.findAll();
		request.setAttribute("authors", authors);
		try {
			Integer identity = (Integer)request.getAttribute("identity");
			if(identity == null) {
				identity = Integer.parseInt(request.getParameter("identity"));
			}
			BookService service = factory.getService(BookService.class);
			Book book = service.findByIdentity(identity);
			if(book != null) {
				request.setAttribute("book", book);
			}
		} catch(NumberFormatException e) {
			try {
				Integer selectedAuthorIdentity = Integer.parseInt(request.getParameter("authorIdentity"));
				request.setAttribute("selectedAuthorIdentity", selectedAuthorIdentity);
			} catch(NumberFormatException e1) {}
		}
		return null;
	}
}
