package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import service.UserService;
import exception.PersistentException;

public class UserDeleteAction extends AdministratorAction {
	private static Logger logger = Logger.getLogger(UserDeleteAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/user/list.html");
		try {
			UserService service = factory.getService(UserService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "Пользователь успешно удалён");
			logger.info(String.format("User \"%s\" deleted user with id %d", getAuthorizedUser().getLogin(), id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete user", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
