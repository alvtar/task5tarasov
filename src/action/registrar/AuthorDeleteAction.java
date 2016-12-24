package action.registrar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import service.AuthorService;
import exception.PersistentException;

public class AuthorDeleteAction extends RegistrarAction {
	private static Logger logger = Logger.getLogger(AuthorDeleteAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/author/list.html");
		try {
			AuthorService service = factory.getService(AuthorService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "Автор успешно удалён");
			logger.info(String.format("User \"%s\" deleted author with id %d", getAuthorizedUser().getLogin(), id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete author", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
