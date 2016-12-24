package action.registrar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import service.BookService;
import domain.Book;
import exception.PersistentException;

public class BookDeleteAction extends RegistrarAction {
	private static Logger logger = Logger.getLogger(BookDeleteAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/author/book/list.html");
		try {
			BookService service = factory.getService(BookService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			Book book = service.findById(id);
			if(book != null) {
				if(book.getAuthor() != null && book.getAuthor().getId() != null) {
					forward.getAttributes().put("id", book.getAuthor().getId());
				}
				service.delete(id);
				forward.getAttributes().put("message", "Книга успешно удалена");
				logger.info(String.format("User \"%s\" deleted book with id %d", getAuthorizedUser().getLogin(), id));
			}
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete book", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
