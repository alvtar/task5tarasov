package action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import exception.PersistentException;

public class DeliverBookAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Integer publicationId = null;
		Integer userId = null;
		try {
			publicationId = Integer.parseInt(request.getParameter("publicationId"));
		} catch(NumberFormatException e) {}
		try {
			userId = Integer.parseInt(request.getParameter("userId"));
		} catch(NumberFormatException e) {}
		if(publicationId == null && userId == null) {
			return new Forward("/");
		}
		if(publicationId == null) {
			publicationId = (Integer)request.getAttribute("publicationId");
			if(publicationId == null) {
				Forward forward = new Forward("/search/publication/form.html");
				forward.getAttributes().put("userId", userId);
				return forward;
			}
		}
		if(userId == null) {
			userId = (Integer)request.getAttribute("userId");
			if(userId == null) {
				Forward forward = new Forward("/search/reader/form.html");
				forward.getAttributes().put("publicationId", publicationId);
				return forward;
			}
		}
	/*	Usage usage = new Usage();
		usage.setBook(new Book());
		usage.getBook().setId(publicationId);
		usage.setReader(new Reader());
		usage.getReader().setId(userId);*/
		Forward forward = new Forward("/reader/usages.html");
		forward.getAttributes().put("id", userId);
	/*	UsageService service = factory.getService(UsageService.class);
		if(service.save(usage)) {
			forward.getAttributes().put("message", "Книга успешно выдана читателю");
		}*/
		return forward;
	}
}
