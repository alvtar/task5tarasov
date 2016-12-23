package validator;

import java.util.HashMap;
import java.util.Map;

import domain.Author;
import domain.Book;
import domain.Entity;
import domain.Reader;
import domain.User;

public class ValidatorFactory {
	private static Map<Class<? extends Entity>, Class<? extends Validator<?>>> validators = new HashMap<>();

	static {
		validators.put(Author.class, AuthorValidator.class);
		validators.put(Book.class, BookValidator.class);
		validators.put(Reader.class, ReaderValidator.class);
		validators.put(User.class, UserValidator.class);
	}

	@SuppressWarnings("unchecked")
	public static <Type extends Entity> Validator<Type> createValidator(Class<Type> entity) {
		try {
			return (Validator<Type>)validators.get(entity).newInstance();
		} catch(InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
