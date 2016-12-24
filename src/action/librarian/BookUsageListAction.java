package action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.PublicationService;
import action.Action;

import domain.Publication;
import exception.PersistentException;

public class BookUsageListAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/author/book/usages.jsp", false);
		Integer userId = (Integer)request.getAttribute("readerId");
		if(userId != null) {
			forward.getAttributes().put("userId", userId);
		}
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			PublicationService publicationService = factory.getService(PublicationService.class);
			Publication publication = publicationService.findById(id);
			if(publication != null) {
				request.setAttribute("book", publication); // TODO
			}
		} catch(NumberFormatException e) {}
		return forward;
	}
}
