package action.registrar;

import action.Action;
import domain.Role;

abstract public class RegistrarAction extends Action {
	public RegistrarAction() {
		getAllowRoles().add(Role.REGISTRAR);
	}
}
