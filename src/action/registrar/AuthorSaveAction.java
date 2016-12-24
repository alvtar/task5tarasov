package action.registrar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import service.AuthorService;
import validator.Validator;
import validator.ValidatorFactory;
import domain.Author;
import exception.IncorrectFormDataException;
import exception.PersistentException;

public class AuthorSaveAction extends RegistrarAction {
	private static Logger logger = Logger.getLogger(AuthorSaveAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/author/book/list.html");
		try {
			Validator<Author> validator = ValidatorFactory.createValidator(Author.class);
			Author author = validator.validate(request);
			AuthorService service = factory.getService(AuthorService.class);
			service.save(author);
			forward.getAttributes().put("id", author.getId());
			forward.getAttributes().put("message", "Данные автора успешно сохранены");
			logger.info(String.format("User \"%s\" saved author with id %d", getAuthorizedUser().getLogin(), author.getId()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save author", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
