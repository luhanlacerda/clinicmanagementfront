package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import classesBasicas.Convenio;
import classesBasicas.Login;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudLogin {

	private Login login;
	private Fachada fachada;
	private String mensagem;
	private DataModel listaLogins;
	private List<Login> logins;
	
	public CrudLogin() {
		this.fachada = new Fachada();
		this.login = new Login();
	}
	
	public DataModel getListaLogins() throws Exception {
		List<Login> lista = new Fachada().getAll(Login.class);
		listaLogins = new ListDataModel(lista);
		return listaLogins;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Fachada getFachada() {
		return fachada;
	}

	public void setFachada(Fachada fachada) {
		this.fachada = fachada;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Login> getLogins() throws Exception {
		this.logins = fachada.getAll(Login.class);
		return this.logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}

	public void setListaLogins(DataModel listaLogins) {
		this.listaLogins = listaLogins;
	}
	
	public String prepararNovoLogin() {
		login = new Login();
		return "CadastrarLogin";
	}

	public String prepararEditarLogin() {
		login = (Login) (listaLogins.getRowData());
		return "CadastrarLogin";
	}
	
	public String editar() throws Exception {
		fachada.update(login);
		mensagem = "Login editado com sucesso";
		return "ListarLogin";
	}

	public String remover() throws Exception {
		Login loginTemp = (Login) (listaLogins.getRowData());
		fachada.remove(loginTemp);
		return "ListarLogin";
	}

	public String cadastrar() throws Exception {
		if (login.getUsername().isEmpty()) {
			if (!login.getUsername().isEmpty() && !login.getPassword().isEmpty()) {
				fachada.insert(login);
				setMensagem("Login cadastrado com sucesso");
			} else {
				setMensagem("Favor informar username e password");
			}
		} else {
			if (!login.getUsername().isEmpty() && !login.getPassword().isEmpty()) {
				fachada.update(login);
				setMensagem("Login atualizado com sucesso");
			} else {
				setMensagem("Favor informar username e password");
			}
		}
		return "ListarLogin";
	}
	
}
