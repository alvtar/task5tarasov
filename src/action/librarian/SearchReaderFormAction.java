package action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import exception.PersistentException;

public class SearchReaderFormAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/search/reader/form.jsp", false);
		Integer bookId = (Integer)request.getAttribute("bookId");
		if(bookId != null) {
			forward.getAttributes().put("bookId", bookId);
		}
		return forward;
	}
}
