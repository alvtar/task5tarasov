package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.UserService;
import domain.User;
import exception.PersistentException;

public class ProfileSaveAction extends AuthorizedUserAction {
	private static Logger logger = Logger.getLogger(ProfileSaveAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/profile/edit.html");
		String oldPassword = request.getParameter("old-password");
		String newPassword = request.getParameter("new-password");
		User authorizedUser = getAuthorizedUser();
		if(oldPassword != null && newPassword != null) {
			UserService service = factory.getService(UserService.class);
			User user = service.findByLoginAndPassword(authorizedUser.getLogin(), oldPassword);
			if(user != null) {
				user.setPassword(newPassword);
				service.save(user);
				forward.getAttributes().put("message", "Пароль успешно изменён");
				logger.info(String.format("User \"%s\" changed password", authorizedUser.getLogin()));
			} else {
				forward.getAttributes().put("message", "Старый пароль неопознан");
				logger.info(String.format("User \"%s\" tried to change password and specified the incorrect previous password", authorizedUser.getLogin()));
			}
		} else {
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to change password", authorizedUser.getLogin()));
		}
		return forward;
	}
}
