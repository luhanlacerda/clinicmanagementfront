package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import classesBasicas.Consulta;
import classesBasicas.incorporada.EstadoCivil;
import classesBasicas.incorporada.EstadoConsulta;
import negocios.Fachada;

@ManagedBean
@SessionScoped
public class CrudConsulta {

	private Fachada fachada;
	private Consulta consulta;
	private String mensagem;
	private DataModel listaConsultas;
	private List<Consulta> consultas;

	public CrudConsulta() {
		this.fachada = new Fachada();
		this.consulta = new Consulta();
	}

	public DataModel getListaConvenios() throws Exception {
		List<Consulta> lista = new Fachada().getAll(Consulta.class);
		listaConsultas = new ListDataModel(lista);
		return listaConsultas;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<Consulta> getConsultasByCRM() throws Exception {
		this.consultas = fachada.getAllByCRM(consulta);
		return this.consultas;
	}

	public List<Consulta> getConsultasByMedico() throws Exception {
		this.consultas = fachada.getAllByMedico(consulta);
		return this.consultas;
	}

	public List<Consulta> getConsultaByPaciente() throws Exception {
		this.consultas = fachada.getAllByPaciente(consulta);
		return this.consultas;
	}

	public List<Consulta> getConsultasBySecretaria() throws Exception {
		this.consultas = fachada.getAllBySecretria(consulta);
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public String prepararNovaConsulta() {
		consulta = new Consulta();
		return "CadastrarConsulta";
	}

	public String prepararEditarConsulta() {
		consulta = (Consulta) (listaConsultas.getRowData());
		return "CadastrarConsulta";
	}

	public String editar() throws Exception {
		fachada.update(consulta);
		mensagem = "Consulta editada com sucesso";
		return "ListarConsulta";
	}

	public String remover() throws Exception {
		Consulta consultaTemp = (Consulta) (listaConsultas.getRowData());
		fachada.remove(consultaTemp);
		return "ListarConsulta";
	}

	public String cadastrar() throws Exception {
		if ((!consulta.getPaciente().equals(null)) && (!consulta.getMedico().equals(null))
				&& (!consulta.getSecretaria().equals(null))) {
			fachada.insert(consulta);
			setMensagem("Consulta cadastradada com sucesso");
		} else {
			setMensagem("Favor informar o paciente, médico e secretária");
		}
		return "ListarConsulta";
	}
	
	public EstadoConsulta[] getEstadosConsulta(){
		return EstadoConsulta.values();
	}
}
