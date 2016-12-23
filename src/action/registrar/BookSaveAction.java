package action.registrar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import service.BookService;
import validator.Validator;
import validator.ValidatorFactory;
import domain.Book;
import exception.IncorrectFormDataException;
import exception.PersistentException;

public class BookSaveAction extends RegistrarAction {
	private static Logger logger = Logger.getLogger(BookSaveAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/author/book/edit.html");
		try {
			Validator<Book> validator = ValidatorFactory.createValidator(Book.class);
			Book book = validator.validate(request);
			BookService service = factory.getService(BookService.class);
			service.save(book);
			forward.getAttributes().put("identity", book.getIdentity());
			forward.getAttributes().put("message", "Данные книги успешно сохранены");
			logger.info(String.format("User \"%s\" saved book with identity %d", getAuthorizedUser().getLogin(), book.getIdentity()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save book", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
