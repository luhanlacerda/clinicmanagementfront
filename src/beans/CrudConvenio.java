package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import classesBasicas.Convenio;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudConvenio {

	private Fachada fachada;
	private Convenio convenio;
	private String mensagem;
	private DataModel listaConvenios;
	private List<Convenio> convenios;

	public CrudConvenio() {
		this.fachada = new Fachada();
		this.convenio = new Convenio();
	}
	
	public DataModel getListaConvenios() throws Exception {
		List<Convenio> lista = new Fachada().getAll(Convenio.class);
		listaConvenios = new ListDataModel(lista);
		return listaConvenios;
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

	public String prepararNovoConvenio() {
		convenio = new Convenio();
		return "CadastrarConvenio";
	}

	public String prepararEditarConvenio() {
		convenio = (Convenio) (listaConvenios.getRowData());
		return "CadastrarConvenio";
	}
	
	public String editar() throws Exception {
		fachada.update(convenio);
		mensagem = "Convenio editado com sucesso";
		return "ListarConvenio";
	}

	public String remover() throws Exception {
		Convenio convenioTemp = (Convenio) (listaConvenios.getRowData());
		fachada.remove(convenioTemp);
		return "ListarConvenio";
	}

	public String cadastrar() throws Exception {
		if (!convenio.getDescricao().trim().isEmpty()) {
			fachada.insert(convenio);
			setMensagem("Convenio cadastrado com sucesso");
		} else {
			setMensagem("Favor informar a descrição");
		}
		return "ListarConvenio";
	}

}