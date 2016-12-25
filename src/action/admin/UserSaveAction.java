package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import service.UserService;
import validator.Validator;
import validator.ValidatorFactory;
import domain.User;
import exception.IncorrectFormDataException;
import exception.PersistentException;

public class UserSaveAction extends AdministratorAction {
	private static Logger logger = Logger.getLogger(UserSaveAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/user/list.html");
		//Forward forward = new Forward("/user/edit.html");
		try {
			Validator<User> validator = ValidatorFactory.createValidator(User.class);
			User user = validator.validate(request);
			UserService service = factory.getService(UserService.class);
			service.save(user);
			forward.getAttributes().put("id", user.getId());
			forward.getAttributes().put("message", "Данные пользователя успешно сохранены");
			logger.info(String.format("User \"%s\" saved user with id %d", getAuthorizedUser().getLogin(), user.getId()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
