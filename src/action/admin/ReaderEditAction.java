package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import service.ReaderService;
import domain.Reader;
import exception.PersistentException;

public class ReaderEditAction extends AdministratorAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		try {
			Integer identity = (Integer)request.getAttribute("identity");
			if(identity == null) {
				identity = Integer.parseInt(request.getParameter("identity"));
			}
			ReaderService service = factory.getService(ReaderService.class);
			Reader reader = service.findByIdentity(identity);
			if(reader != null) {
				request.setAttribute("reader", reader);
			}
		} catch(NumberFormatException e) {}
		return null;
	}
}
