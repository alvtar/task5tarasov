package exception;

public class IncorrectFormDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public IncorrectFormDataException(String param, String value) {
		super(String.format("Empty or incorrect \"%s\" parameter was found: %s", param, value));
	}
}
