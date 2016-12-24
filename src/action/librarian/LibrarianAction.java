package action.librarian;

import org.apache.log4j.Logger;

import action.Action;
import console.AppInit;
import controller.DispatcherServlet;
import domain.Role;


abstract public class LibrarianAction extends Action {
    
    private static Logger logger = Logger.getLogger(AppInit.class);
    
	public LibrarianAction() {
		getAllowRoles().add(Role.SUBSCRIBER);
		
		logger.info("DISPATCHER.INIT.BEFORE POOL");
	}
}
