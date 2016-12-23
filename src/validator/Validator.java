package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Entity;
import exception.IncorrectFormDataException;

public interface Validator<Type extends Entity> {
	Type validate(HttpServletRequest request) throws IncorrectFormDataException;
}
