package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import classesBasicas.Convenio;
import classesBasicas.Paciente;
import classesBasicas.Secretaria;
import classesBasicas.incorporada.EstadoCivil;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudPaciente {

	private String mensagem;
	private Paciente paciente;
	private Fachada fachada;
	private DataModel listaPacientes;
	private List<Paciente> pacientes;
	private Convenio selectedConvenio;
	private List<Convenio> convenios;

	public CrudPaciente() {
		this.fachada = new Fachada();
		this.paciente = new Paciente();
		try {
			convenios = fachada.getAll(Convenio.class);
		} catch (Exception e) {
			convenios = new ArrayList<Convenio>();
		}
	}

	public DataModel getListaSecretarias() throws Exception {
		List<Paciente> lista = new Fachada().getAll(Paciente.class);
		listaPacientes = new ListDataModel(lista);
		return listaPacientes;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Fachada getFachada() {
		return fachada;
	}

	public void setFachada(Fachada fachada) {
		this.fachada = fachada;
	}

	public List<Paciente> getPacientes() throws Exception {
		this.pacientes = fachada.getAll(Paciente.class);
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public String prepararNovoPaciente() {
		paciente = new Paciente();
		return "CadastrarPaciente";
	}

	public String prepararEditarPaciente() {
		paciente = (Paciente) (listaPacientes.getRowData());
		return "CadastrarPaciente";
	}

	public String editar() throws Exception {
		fachada.update(paciente);
		mensagem = "Paciente editado com sucesso";
		return "ListarPaciente";
	}

	public String remover() throws Exception {
		Paciente pacienteTemp = (Paciente) (listaPacientes.getRowData());
		fachada.remove(pacienteTemp);
		return "ListarPaciente";
	}

	public String cadastrar() throws Exception {
		if (!paciente.getNome().trim().isEmpty()) {
			fachada.insert(paciente);
			setMensagem("Paciente cadastrado com sucesso");
		} else {
			setMensagem("Favor informar o nome");
		}
		return "ListarPaciente";
	}

	public EstadoCivil[] getEstadoCivis() {
		return EstadoCivil.values();
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}
	
	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	public Convenio getSelectedConvenio() {
		return selectedConvenio;
	}

	public void setSelectedConvenio(Convenio selectedConvenio) {
		this.selectedConvenio = selectedConvenio;
	}
	
	public Convenio getConvenio(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Convenio convenio : convenios){
            if (id.equals(convenio.getId())){
                return convenio;
            }
        }
        return null;
    }
}
