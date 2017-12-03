package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import negocios.Fachada;
import util.FacesUtils;
import util.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
	Fachada fachada = new Fachada();
	
	private static final long serialVersionUID = -1379924857284538386L;

	private String username;
	private String password;
	private String message;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String validateLogin() {
		classesBasicas.Login login = new classesBasicas.Login();
		login.setUsername(this.username);
		login.setPassword(this.password);
		
		try {
			classesBasicas.Login result = fachada.login(login);
			
			((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute("user", result);
			return "index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return "login.xhtml";
		}
	}

	public String logout() {
		SessionUtils.invalidate();
		return "login.xhtml?faces-redirect=true";
	}
	
	public classesBasicas.Login getSessionUser() {
		return (classesBasicas.Login) SessionUtils.getAttribute("user");
	}
	
}
