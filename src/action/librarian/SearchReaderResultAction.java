package action.librarian;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import action.Action;
import domain.User;
import exception.PersistentException;

public class SearchReaderResultAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/search/user/result.jsp", false);
		Integer publicationId = (Integer)request.getAttribute("publicationId");
		if(publicationId != null) {
			forward.getAttributes().put("publicationId", publicationId);
		}
		List<User> users;
		UserService service = factory.getService(UserService.class);
		String search = request.getParameter("search");
		String id = request.getParameter("id");
	//	if(search != null) {
	//		users = service.findBySurname(search);
	//	} else if(libraryCardNumber != null) {
			users = new ArrayList<>();
			User user = service.findById(Integer.parseInt(id));
			if(user != null) {
				users.add(user);
			}
	//	} else {
	//		users = new ArrayList<>();
	//	}
		request.setAttribute("users", users);
		return forward;
	}
}
