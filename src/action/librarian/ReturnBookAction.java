package action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UsageService;
import action.Action;
import domain.Usage;
import exception.PersistentException;

public class ReturnBookAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/reader/usages.html");
		try {
			Integer usageId = Integer.parseInt(request.getParameter("usageId"));
			UsageService service = factory.getService(UsageService.class);
			Usage usage = service.findById(usageId);
			if(usage != null) {
				service.save(usage);
				forward.getAttributes().put("id", usage.getReader().getId());
			}
		} catch(NumberFormatException e) {}
		return forward;
	}
}
