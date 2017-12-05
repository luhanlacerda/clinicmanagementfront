package beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import classesBasicas.Secretaria;
import classesBasicas.incorporada.EstadoCivil;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudSecretaria {

	private String mensagem;
	private Secretaria secretaria;
	private Fachada fachada;
	private List<Secretaria> secretarias;

	public CrudSecretaria() {
		this.fachada = new Fachada();
		this.secretaria = new Secretaria();
		this.secretarias = new ArrayList<Secretaria>();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public Fachada getFachada() {
		return fachada;
	}

	public void setFachada(Fachada fachada) {
		this.fachada = fachada;
	}

	public List<Secretaria> getSecretarias() throws Exception {
		this.secretarias = fachada.getAllByName(secretaria);
		return secretarias;
	}

	public void setSecretarias(List<Secretaria> secretarias) {
		this.secretarias = secretarias;
	}

	public String novo() {
		secretaria = new Secretaria();
		return "cadastrarsecretaria";
	}

	public String editar() {
		return "cadastrarsecretaria";
	}

	public String remover() {
		// REMOVER CLIENTE
		return null;
	}

	public String cadastrarSecretaria() throws Exception {
		if (!secretaria.getNome().trim().isEmpty()) {
			fachada.insert(secretaria);
			setMensagem("Convenio cadastrado com sucesso");
		} else {
			setMensagem("Favor informar a descrição");
		}
		return mensagem;
	}
	
	public EstadoCivil[] getEstadoCivis(){
		return EstadoCivil.values();
	}

}
