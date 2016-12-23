package action.admin;

import action.Action;
import domain.Role;

abstract public class AdministratorAction extends Action {
	public AdministratorAction() {
		getAllowRoles().add(Role.ADMINISTRATOR);
	}
}
