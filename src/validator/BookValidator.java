package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Author;
import domain.Book;
import exception.IncorrectFormDataException;

public class BookValidator implements Validator<Book> {
	@Override
	public Book validate(HttpServletRequest request) throws IncorrectFormDataException {
		Book book = new Book();
		String parameter = request.getParameter("identity");
		if(parameter != null) {
			try {
				book.setIdentity(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("identity", parameter);
			}
		}
		parameter = request.getParameter("inventoryNumber");
		if(parameter != null && !parameter.isEmpty()) {
			book.setInventoryNumber(parameter);
		} else {
			throw new IncorrectFormDataException("inventoryNumber", parameter);
		}
		parameter = request.getParameter("author");
		if(parameter != null && !parameter.isEmpty()) {
			Author author = new Author();
			try {
				author.setIdentity(Integer.parseInt(parameter));
				book.setAuthor(author);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("author", parameter);
			}
		}
		parameter = request.getParameter("title");
		if(parameter != null && !parameter.isEmpty()) {
			book.setTitle(parameter);
		} else {
			throw new IncorrectFormDataException("title", parameter);
		}
		parameter = request.getParameter("year");
		try {
			book.setYear(Integer.parseInt(parameter));
		} catch(NumberFormatException e) {
			throw new IncorrectFormDataException("year", parameter);
		}
		return book;
	}
}
