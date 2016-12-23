package action;

import java.util.Arrays;

import domain.Role;

public abstract class AuthorizedUserAction extends Action {
	public AuthorizedUserAction() {
		getAllowRoles().addAll(Arrays.asList(Role.values()));
	}
}
