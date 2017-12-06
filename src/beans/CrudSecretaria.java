package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import classesBasicas.Convenio;
import classesBasicas.Secretaria;
import classesBasicas.incorporada.EstadoCivil;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudSecretaria {

	private String mensagem;
	private Secretaria secretaria;
	private Fachada fachada;
	private DataModel listaSecretarias;
	private List<Secretaria> secretarias;

	public CrudSecretaria() {
		this.fachada = new Fachada();
		this.secretaria = new Secretaria();
	}
	
	public DataModel getListaSecretarias() throws Exception {
		List<Secretaria> lista = new Fachada().getAll(Secretaria.class);
		listaSecretarias = new ListDataModel(lista);
		return listaSecretarias;
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
		this.secretarias = fachada.getAll(Secretaria.class);
		return secretarias;
	}

	public void setSecretarias(List<Secretaria> secretarias) {
		this.secretarias = secretarias;
	}

	public String prepararNovaSecretaria() {
		secretaria = new Secretaria();
		return "CadastrarSecretaria";
	}

	public String prepararEditarSecretaria() {
		secretaria = (Secretaria) (listaSecretarias.getRowData());
		return "CadastrarSecretaria";
	}
	
	public String editar() throws Exception {
		fachada.update(secretaria);
		mensagem = "Secretaria editada com sucesso";
		return "ListarSecretaria";
	}

	public String remover() throws Exception {
		Secretaria secretariaTemp = (Secretaria) (listaSecretarias.getRowData());
		fachada.remove(secretariaTemp);
		return "ListarSecretaria";
	}

	public String cadastrar() throws Exception {
		if (!secretaria.getNome().trim().isEmpty()) {
			fachada.insert(secretaria);
			setMensagem("Secretaria cadastrada com sucesso");
		} else {
			setMensagem("Favor informar o nome");
		}
		return "ListarSecretaria";
	}
	
	public EstadoCivil[] getEstadoCivis(){
		return EstadoCivil.values();
	}

}
