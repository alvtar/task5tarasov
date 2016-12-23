package action.registrar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import service.AuthorService;
import domain.Author;
import exception.PersistentException;

public class AuthorListAction extends RegistrarAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		AuthorService service = factory.getService(AuthorService.class);
		List<Author> authors = service.findAll();
		request.setAttribute("authors", authors);
		return null;
	}
}
