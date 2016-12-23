package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.PersistentException;

public class MainAction extends AuthorizedUserAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		@SuppressWarnings("unchecked")
		List<MenuItem> menu = (List<MenuItem>)request.getSession(false).getAttribute("menu");
		return new Forward(menu.get(0).getUrl());
	}
}
