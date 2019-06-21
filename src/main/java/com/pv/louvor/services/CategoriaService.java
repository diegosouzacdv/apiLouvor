package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categoria> buscarTodos() {
		List<Categoria> obj = repo.findAll();
		return obj;
	}

	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
}