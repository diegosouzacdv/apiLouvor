package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.repositories.RepertorioRepository;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class RepertorioService {
	
	@Autowired
	private RepertorioRepository repo;
	
	public List<Repertorio> buscarTodos() {
		List<Repertorio> obj = repo.findAll();
		return obj;
	}

	public Repertorio buscar(Integer id) {
		Repertorio obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Repertorio.class.getName());
		}
		return obj;
	}
}
