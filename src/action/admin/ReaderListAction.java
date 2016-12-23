package action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import service.ReaderService;
import domain.Reader;
import exception.PersistentException;

public class ReaderListAction extends AdministratorAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		ReaderService service = factory.getService(ReaderService.class);
		List<Reader> readers = service.findAll();
		request.setAttribute("readers", readers);
		return null;
	}
}
