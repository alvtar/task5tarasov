package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

import service.Service;
import service.UserService;

import domain.User;
import exception.PersistentException;

public class nnnnnnReaderEditAction extends AdministratorAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			UserService service = factory.getService(UserService.class);
			User user = service.findById(id);
			if(user != null) {
				request.setAttribute("reader", user);
			}
		} catch(NumberFormatException e) {}
		return null;
	}
}
