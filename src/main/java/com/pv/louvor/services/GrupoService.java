package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Grupo;
import com.pv.louvor.repositories.GrupoRepository;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository repo;
	
	public List<Grupo> buscarTodos() {
		List<Grupo> obj = repo.findAll();
		return obj;
	}

	public Grupo buscar(Integer id) {
		Grupo obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Grupo.class.getName());
		}
		return obj;
	}
}
