package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import service.UserService;
import domain.Role;
import domain.User;
import exception.PersistentException;

public class LoginAction extends Action {
	private static Logger logger = Logger.getLogger(LoginAction.class);

	private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

	static {
		menu.put(Role.SUBSCRIBER, new ArrayList<>(Arrays.asList(	
			//new MenuItem("/publication/search/form.html", "поиск издания по наименованию")
		        new MenuItem("/publication/list.html", "издания") //+++
		        
		)));
		menu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
		        //new MenuItem("/subscription/list.html", "подписки"),
			new MenuItem("/user/list.html", "пользователи") //+++
		)));

		
	/*	lst.add("МЕНЮ ПОДПИСЧИКА");
	        lst.add("Сделайте свой выбор:");
	        lst.add("1 - Поиск издания по индексу;");
	        lst.add("2 - Поиск издания по наименованию;");
	        lst.add("3 - Вывод полного списка изданий;");
	        lst.add("4 - Вывод истории подписок;");
	        lst.add("5 - Новая подписка;");
	        lst.add("6 - Выход.");
	        lst.add("> ");
		
		
		     lst.add("МЕНЮ АДМИНИСТРАТОРА");
        lst.add("Сделайте свой выбор:");
        lst.add("1 - Поиск издания по индексу;");
        lst.add("2 - Поиск издания по наименованию;");
        lst.add("3 - Вывод полного списка изданий;");
        lst.add("4 - Вывод полного списка подписок;");
        lst.add("5 - Вывод полного списка пользователей;");
        lst.add("6 - Новое издание;");
        lst.add("7 - Выход.");
        lst.add("> ");*/
		
		
		
	}

	@Override
	public Set<Role> getAllowRoles() {
		return null;
	}

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login != null && password != null) {
			UserService service = factory.getService(UserService.class);
			User user = service.findByLoginAndPassword(login, password);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("authorizedUser", user);
				session.setAttribute("menu", menu.get(user.getRole()));
				logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
				return new Forward("/index.html");
			} else {
				request.setAttribute("message", "Имя пользователя или пароль не опознанны");
				logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
			}
		}
		return null;
	}
}
