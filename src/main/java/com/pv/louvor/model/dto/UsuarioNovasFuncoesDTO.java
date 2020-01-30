package com.pv.louvor.model.dto;

import java.io.Serializable;
import java.util.List;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Usuario;

public class UsuarioNovasFuncoesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Funcao> funcao;
	
	public UsuarioNovasFuncoesDTO() {
	}

	public UsuarioNovasFuncoesDTO(Usuario usuario) {
		super();
		this.funcao = usuario.getFuncao();
	}

	public List<Funcao> getFuncao() {
		return funcao;
	}

	public void setFuncao(List<Funcao> funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "UsuarioNovasFuncoesDTO [funcao=" + funcao + "]";
	}

	

}
