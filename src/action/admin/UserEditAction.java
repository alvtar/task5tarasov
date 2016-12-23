package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import service.UserService;
import domain.Role;
import domain.User;
import exception.PersistentException;

public class UserEditAction extends AdministratorAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		request.setAttribute("roles", Role.values());
		try {
			Integer identity = (Integer)request.getAttribute("identity");
			if(identity == null) {
				identity = Integer.parseInt(request.getParameter("identity"));
			}
			UserService service = factory.getService(UserService.class);
			User user = service.findByIdentity(identity);
			if(user != null) {
				request.setAttribute("user", user);
			}
		} catch(NumberFormatException e) {}
		return null;
	}
}
