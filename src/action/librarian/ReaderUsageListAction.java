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
		Integer bookId = (Integer)request.getAttribute("bookId");
		if(bookId != null) {
			forward.getAttributes().put("bookId", bookId);
		}
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			ReaderService readerService = factory.getService(ReaderService.class);
			Reader reader = readerService.findById(id);
			if(reader != null) {
				request.setAttribute("reader", reader);
			}
		} catch(NumberFormatException e) {}
		return forward;
	}
}
