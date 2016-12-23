package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import action.Action;
import service.ReaderService;
import validator.Validator;
import validator.ValidatorFactory;
import domain.Reader;
import exception.IncorrectFormDataException;
import exception.PersistentException;

public class ReaderSaveAction extends AdministratorAction {
	private static Logger logger = Logger.getLogger(ReaderSaveAction.class);
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/reader/edit.html");
		try {
			Validator<Reader> validator = ValidatorFactory.createValidator(Reader.class);
			Reader reader = validator.validate(request);
			ReaderService service = factory.getService(ReaderService.class);
			service.save(reader);
			forward.getAttributes().put("identity", reader.getIdentity());
			forward.getAttributes().put("message", "Данные читателя успешно сохранены");
			logger.info(String.format("User \"%s\" saved reader with identity %d", getAuthorizedUser().getLogin(), reader.getIdentity()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save reader", getAuthorizedUser().getLogin()), e);
		}
		return forward;
	}
}
