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
			Integer usageIdentity = Integer.parseInt(request.getParameter("usageIdentity"));
			UsageService service = factory.getService(UsageService.class);
			Usage usage = service.findByIdentity(usageIdentity);
			if(usage != null) {
				service.save(usage);
				forward.getAttributes().put("identity", usage.getReader().getIdentity());
			}
		} catch(NumberFormatException e) {}
		return forward;
	}
}
