package controller;

import javax.faces.bean.ManagedBean;

import classesBasicas.Convenio;
import negocios.Fachada;

@ManagedBean
public class CrudConvenio {
	private String id;
	private String descricao;
	
	Fachada fachada = Fachada.getInstance();
	Convenio convenio = new Convenio();
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

	public String cadastrarConvenio() {
		convenio.setDescricao(descricao);
		if (convenio.getDescricao().equals(null)) {
			setMensagem("Favor informar a descrição");
		} else {
			fachada.insert(convenio);
			setMensagem("Convenio cadastrado com sucesso");
		}
		return mensagem;
	}
}
