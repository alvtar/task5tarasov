package action.librarian;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BookService;
import action.Action;
import domain.Book;
import exception.PersistentException;

public class SearchBookResultAction extends LibrarianAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/search/book/result.jsp", false);
		Integer readerIdentity = (Integer)request.getAttribute("readerIdentity");
		if(readerIdentity != null) {
			forward.getAttributes().put("readerIdentity", readerIdentity);
		}
		List<Book> books;
		BookService service = factory.getService(BookService.class);
		String inventoryNumber = request.getParameter("inventoryNumber");
		String search = request.getParameter("search");
		Integer identity = null;
		try {
			identity = Integer.parseInt(request.getParameter("identity"));
		} catch(NumberFormatException e) {}
		if(inventoryNumber != null) {
			books = new ArrayList<>();
			Book book = service.findByInventoryNumber(inventoryNumber);
			if(book != null) {
				books.add(book);
			}
		} else if(search != null) {
			books = service.findByTitle(search);
		} else {
			books = service.findByAuthor(identity);
		}
		request.setAttribute("books", books);
		return forward;
	}
}
