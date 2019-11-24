package com.pv.louvor.repositories.repertorio;

import java.util.List;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Usuario;

public interface RepertorioRepositoryQuery {
	
	public List<Usuario> funcoes(Funcao funcao, Igreja igreja);

}
