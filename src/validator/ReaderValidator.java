package validator;

import javax.servlet.http.HttpServletRequest;

import domain.User;
import exception.IncorrectFormDataException;

public class ReaderValidator implements Validator<User> {
	@Override
	public User validate(HttpServletRequest request) throws IncorrectFormDataException {
		User user = new User();
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				user.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		parameter = request.getParameter("login");
		if(parameter != null && !parameter.isEmpty()) {
			user.setLogin(parameter);
		} else {
			throw new IncorrectFormDataException("login", parameter);
		}
		parameter = request.getParameter("password");
		if(parameter != null && !parameter.isEmpty()) {
			user.setPassword(parameter);
		} else {
			throw new IncorrectFormDataException("password", parameter);
		}
		
		parameter = request.getParameter("fullName");
		if(parameter != null && !parameter.isEmpty()) {
			user.setFullName(parameter);
		} else {
			throw new IncorrectFormDataException("fullName", parameter);
		}
		
		parameter = request.getParameter("zipCode");
		if(parameter != null && !parameter.isEmpty()) {
			user.setZipCode(Integer.parseInt(parameter));
		} else {
			throw new IncorrectFormDataException("zipCode", parameter);
		}
		
		parameter = request.getParameter("address");
		if(parameter != null && !parameter.isEmpty()) {
			user.setAddress(parameter);
		} else {
			throw new IncorrectFormDataException("address", parameter);
		}
		
		return user;
	}
}
