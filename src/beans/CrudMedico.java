package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import classesBasicas.Especialidade;
import classesBasicas.Medico;
import classesBasicas.Secretaria;
import classesBasicas.incorporada.EstadoCivil;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudMedico {

	private String mensagem;
	private Fachada fachada;
	private Medico medico;
	private DataModel listaMedicos;
	private List<Medico> medicos;

	public CrudMedico() {
		this.fachada = new Fachada();
		this.medico = new Medico();
	}

	public DataModel getListaMedicos() throws Exception {
		List<Medico> lista = new Fachada().getAll(Medico.class);
		listaMedicos = new ListDataModel(lista);
		return listaMedicos;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Fachada getFachada() {
		return fachada;
	}

	public void setFachada(Fachada fachada) {
		this.fachada = fachada;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Medico> getMedicos() throws Exception {
		this.medicos = fachada.getAll(Medico.class);
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public void setListaMedicos(DataModel listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public String prepararNovoMedico() {
		medico = new Medico();
		return "CadastrarMedico";
	}

	public String prepararEditarMedico() {
		medico = (Medico) (listaMedicos.getRowData());
		return "CadastrarMedico";
	}

	public String editar() throws Exception {
		fachada.update(medico);
		mensagem = "Médico editado com sucesso";
		return "ListarMedico";
	}

	public String remover() throws Exception {
		Medico medicoTemp = (Medico) (listaMedicos.getRowData());
		fachada.remove(medicoTemp);
		return "ListarMedico";
	}

	public String cadastrar() throws Exception {
		if (!medico.getNome().trim().isEmpty()) {
			fachada.insert(medico);
			setMensagem("Medico cadastrado com sucesso");
		} else {
			setMensagem("Favor informar o nome");
		}
		return "ListarMedico";
	}

	public EstadoCivil[] getEstadoCivis() {
		return EstadoCivil.values();
	}
	
	public List<Especialidade> getEspecialidades() throws Exception {
		return fachada.getAll(Especialidade.class);
	}

}
