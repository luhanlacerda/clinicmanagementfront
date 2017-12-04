package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	private SessionUtils() {
	}

	private static HttpSession getSession() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);

		return session;
	}

	public static void setAttribute(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getAttribute(String key) {
		return getSession().getAttribute(key);
	}

	public static void remove(String key) {
		getSession().removeAttribute(key);
	}

	public static void invalidate() {
		getSession().invalidate();
	}
}