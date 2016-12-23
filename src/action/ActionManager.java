package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.PersistentException;

public interface ActionManager {
	Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws PersistentException;

	void close();
}
