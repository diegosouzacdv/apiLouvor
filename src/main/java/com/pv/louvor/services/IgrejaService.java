package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Igreja;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class IgrejaService  {
	
	@Autowired
	private IgrejaRepository repo;
	
	public List<Igreja> buscarTodos() {
		List<Igreja> obj = repo.findAll();
		return obj;
	}

	public Igreja buscar(Integer id) {
		Igreja obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Igreja.class.getName());
		}
		return obj;
	}
}
