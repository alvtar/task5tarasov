package action.librarian;

import action.Action;
import domain.Role;

abstract public class LibrarianAction extends Action {
	public LibrarianAction() {
		getAllowRoles().add(Role.LIBRARIAN);
	}
}
