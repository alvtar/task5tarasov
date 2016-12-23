package action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UsageService;
import domain.Book;
import domain.Reader;
import domain.Usage;
import action.Action;
import exception.PersistentException;

public class DeliverBookAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Integer bookIdentity = null;
		Integer readerIdentity = null;
		try {
			bookIdentity = Integer.parseInt(request.getParameter("bookIdentity"));
		} catch(NumberFormatException e) {}
		try {
			readerIdentity = Integer.parseInt(request.getParameter("readerIdentity"));
		} catch(NumberFormatException e) {}
		if(bookIdentity == null && readerIdentity == null) {
			return new Forward("/");
		}
		if(bookIdentity == null) {
			bookIdentity = (Integer)request.getAttribute("bookIdentity");
			if(bookIdentity == null) {
				Forward forward = new Forward("/search/book/form.html");
				forward.getAttributes().put("readerIdentity", readerIdentity);
				return forward;
			}
		}
		if(readerIdentity == null) {
			readerIdentity = (Integer)request.getAttribute("readerIdentity");
			if(readerIdentity == null) {
				Forward forward = new Forward("/search/reader/form.html");
				forward.getAttributes().put("bookIdentity", bookIdentity);
				return forward;
			}
		}
		Usage usage = new Usage();
		usage.setBook(new Book());
		usage.getBook().setIdentity(bookIdentity);
		usage.setReader(new Reader());
		usage.getReader().setIdentity(readerIdentity);
		Forward forward = new Forward("/reader/usages.html");
		forward.getAttributes().put("identity", readerIdentity);
		UsageService service = factory.getService(UsageService.class);
		if(service.save(usage)) {
			forward.getAttributes().put("message", "Книга успешно выдана читателю");
		}
		return forward;
	}
}
