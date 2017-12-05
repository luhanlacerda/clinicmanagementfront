package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import classesBasicas.Convenio;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudConvenio {
	private String id;
	private String descricao;
	private Fachada fachada;
	private Convenio convenio;
	private String mensagem;
	private List<Convenio> convenios;

	public CrudConvenio() {
		this.fachada = new Fachada();
		this.convenio = new Convenio();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Convenio> getConvenios() throws Exception {
		this.convenios = fachada.getAll(Convenio.class);
		return this.convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	public String novo() {
		convenio = new Convenio();
		return "CadastrarConvenio";
	}

	public String editar(Convenio convenio) throws Exception {
		fachada.update(convenio);
		return "";
	}

	public String remover(Convenio convenio) throws Exception {
		fachada.remove(convenio);
		return "";
	}

	public String cadastrarConvenio() throws Exception {
		convenio.setDescricao(descricao);
		if (!convenio.getDescricao().trim().isEmpty()) {
			fachada.insert(convenio);
			setMensagem("Convenio cadastrado com sucesso");
		} else {
			setMensagem("Favor informar a descrição");
		}
		return mensagem;
	}

}