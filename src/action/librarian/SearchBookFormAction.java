package action.librarian;

import java.util.List;
///import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import domain.Publication;
import domain.Subscription;
import exception.PersistentException;
import service.PublicationService;
import service.SubscriptionService;

public class SearchBookFormAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/search/book/form.jsp", false);
		Integer userId = (Integer)request.getAttribute("userId");
		if(userId != null) {
			forward.getAttributes().put("userId", userId);
		}
		PublicationService publicationService = factory.getService(PublicationService.class);
		List<Publication> publications = publicationService.findAll();
		request.setAttribute("authors", publications);
		SubscriptionService subscriptionService = factory.getService(SubscriptionService.class);
		List<Subscription> subscriptions = subscriptionService.findAll();
		if(!subscriptions.isEmpty()) {
			request.setAttribute("numberOfBooksWithoutAuthor", subscriptions.size());
		}
		return forward;
	}
}
