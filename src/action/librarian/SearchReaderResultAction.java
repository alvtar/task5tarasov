package action.librarian;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReaderService;
import action.Action;
import domain.Reader;
import exception.PersistentException;

public class SearchReaderResultAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/search/reader/result.jsp", false);
		Integer bookIdentity = (Integer)request.getAttribute("bookIdentity");
		if(bookIdentity != null) {
			forward.getAttributes().put("bookIdentity", bookIdentity);
		}
		List<Reader> readers;
		ReaderService service = factory.getService(ReaderService.class);
		String search = request.getParameter("search");
		String libraryCardNumber = request.getParameter("libraryCardNumber");
		if(search != null) {
			readers = service.findBySurname(search);
		} else if(libraryCardNumber != null) {
			readers = new ArrayList<>();
			Reader reader = service.findByLibraryCardNumber(libraryCardNumber);
			if(reader != null) {
				readers.add(reader);
			}
		} else {
			readers = new ArrayList<>();
		}
		request.setAttribute("readers", readers);
		return forward;
	}
}
