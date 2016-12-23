package action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReaderService;
import action.Action;
import domain.Reader;
import exception.PersistentException;

public class ReaderUsageListAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/reader/usages.jsp", false);
		Integer bookIdentity = (Integer)request.getAttribute("bookIdentity");
		if(bookIdentity != null) {
			forward.getAttributes().put("bookIdentity", bookIdentity);
		}
		try {
			Integer identity = (Integer)request.getAttribute("identity");
			if(identity == null) {
				identity = Integer.parseInt(request.getParameter("identity"));
			}
			ReaderService readerService = factory.getService(ReaderService.class);
			Reader reader = readerService.findByIdentity(identity);
			if(reader != null) {
				request.setAttribute("reader", reader);
			}
		} catch(NumberFormatException e) {}
		return forward;
	}
}
