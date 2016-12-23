package action.librarian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;
import action.Action;
import domain.Book;
import exception.PersistentException;

public class BookUsageListAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/author/book/usages.jsp", false);
		Integer readerIdentity = (Integer)request.getAttribute("readerIdentity");
		if(readerIdentity != null) {
			forward.getAttributes().put("readerIdentity", readerIdentity);
		}
		try {
			Integer identity = Integer.parseInt(request.getParameter("identity"));
			BookService bookService = factory.getService(BookService.class);
			Book book = bookService.findByIdentity(identity);
			if(book != null) {
				request.setAttribute("book", book);
			}
		} catch(NumberFormatException e) {}
		return forward;
	}
}
