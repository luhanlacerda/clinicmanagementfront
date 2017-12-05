package beans;

import javax.faces.bean.ManagedBean;

import classesBasicas.Especialidade;
import negocios.Fachada;

@ManagedBean
public class CrudEspecialidade {

	private String id;
	private String descricao;
	
	Fachada fachada = new Fachada();
	Especialidade especialidade = new Especialidade();
	String mensagem;
	
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
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String cadastrarEspecialidade() throws Exception {
		especialidade.setDescricao(descricao);
		if (especialidade.getDescricao().trim().isEmpty()) {
			setMensagem("Favor, informar a descrição.");
		} else {
			fachada.insert(especialidade);
			setMensagem("Especialidade Cadastrada com Sucesso!");
		}
		return null;
	}
	
}
