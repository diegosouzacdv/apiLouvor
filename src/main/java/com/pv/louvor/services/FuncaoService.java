package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.repositories.FuncaoRepository;

@Service
public class FuncaoService  {
	
	@Autowired
	private FuncaoRepository repo;
	
	public List<Funcao> buscarTodos() {
		List<Funcao> obj = repo.findAll();
		return obj;
	}

	public Funcao buscar(Integer id) {
		Funcao obj = repo.findOne(id);
		return obj;
	}
}
