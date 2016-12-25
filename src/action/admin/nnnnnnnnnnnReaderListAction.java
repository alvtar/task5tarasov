package action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import domain.User;
import service.UserService;

import exception.PersistentException;

public class nnnnnnnnnnnReaderListAction extends AdministratorAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		UserService service = factory.getService(UserService.class);
		List<User> users = service.findAll();
		request.setAttribute("users", users); // TODO
		return null;
	}
}
