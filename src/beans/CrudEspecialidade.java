package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import classesBasicas.Especialidade;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudEspecialidade {

	private Especialidade especialidade;
	private Fachada fachada;
	private String mensagem;
	private DataModel listaEspecialidades;
	private List<Especialidade> especialidades;

	public CrudEspecialidade() {
		this.fachada = new Fachada();
		this.especialidade = new Especialidade();
	}

	public DataModel getListaEspecialidades() throws Exception {
		List<Especialidade> lista = new Fachada().getAll(Especialidade.class);
		listaEspecialidades = new ListDataModel(lista);
		return listaEspecialidades;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public void setListaEspecialidades(DataModel listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public Fachada getFachada() {
		return fachada;
	}

	public void setFachada(Fachada fachada) {
		this.fachada = fachada;
	}

	public List<Especialidade> getEspecialidades() throws Exception {
		this.especialidades = fachada.getAll(Especialidade.class);
		return this.especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String prepararNovaEspecialidade() {
		especialidade = new Especialidade();
		return "CadastrarEspecialidade";
	}

	public String prepararEditarEspecialidade() {
		especialidade = (Especialidade) (listaEspecialidades.getRowData());
		return "CadastrarEspecialidade";
	}
	
	public String editar() throws Exception {
		fachada.update(especialidade);
		mensagem = "Especialidade editada com sucesso";
		return "ListarEspecialidade";
	}

	public String remover() throws Exception {
		Especialidade especialidadeTemp = (Especialidade) (listaEspecialidades.getRowData());
		fachada.remove(especialidadeTemp);
		return "ListarEspecialidade";
	}

	public String cadastrar() throws Exception {
		if (!especialidade.getDescricao().trim().isEmpty()) {
			fachada.insert(especialidade);
			setMensagem("Especialidade cadastrada com sucesso");
		} else {
			setMensagem("Favor informar a descrição");
		}
		return "ListarEspecialidade";
	}

}
