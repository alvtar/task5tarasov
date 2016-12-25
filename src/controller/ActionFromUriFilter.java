package controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import action.Action;
import action.LoginAction;
import action.LogoutAction;
import action.MainAction;
import action.ProfileEditAction;
import action.ProfileSaveAction;
import action.PublicationListAction;
import action.admin.UserDeleteAction;
import action.admin.UserEditAction;
import action.admin.UserListAction;
import action.admin.UserSaveAction;



public class ActionFromUriFilter implements Filter {
	private static Logger logger = Logger.getLogger(ActionFromUriFilter.class);

	private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();

	static {
		actions.put("/", MainAction.class);
		actions.put("/index", MainAction.class);
		actions.put("/login", LoginAction.class);
		actions.put("/logout", LogoutAction.class);

		actions.put("/profile/edit", ProfileEditAction.class);
		actions.put("/profile/save", ProfileSaveAction.class);

		actions.put("/user/list", UserListAction.class);
		actions.put("/user/edit", UserEditAction.class);
		actions.put("/user/save", UserSaveAction.class);
		actions.put("/user/delete", UserDeleteAction.class);

		actions.put("/publication/list", PublicationListAction.class);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			String contextPath = httpRequest.getContextPath();
			String uri = httpRequest.getRequestURI();
			logger.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
			int beginAction = contextPath.length();
			int endAction = uri.lastIndexOf('.');
			String actionName;
			if(endAction >= 0) {
				actionName = uri.substring(beginAction, endAction);
			} else {
				actionName = uri.substring(beginAction);
			}
			Class<? extends Action> actionClass = actions.get(actionName);
			try {
				Action action = actionClass.newInstance();
				action.setName(actionName);
				httpRequest.setAttribute("action", action);
				chain.doFilter(request, response);
			} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
				logger.error("It is impossible to create action handler object", e);
				httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
				httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
		} else {
			logger.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {}
}
