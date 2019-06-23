package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.services.exceptions.DataIntegrityException;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public List<Categoria> buscarTodos() {
		List<Categoria> obj = repo.findAll();
		return obj;
	}

	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		isExist(obj);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		isExist(obj);
		return repo.save(obj);
	}
	
	public Categoria isExist(Categoria obj) {
	Categoria obj1 = repo.findByNome(obj.getNome());
		if(obj1 != null && obj1.getNome().equals(obj.getNome())) {
			throw new ObjectFoundException("Objeto Já existe com Id: " + obj.getId() + 
					", Tipo: " + Categoria.class.getName());
		}		
		return obj;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não é possível excluir!");
		}
	}
}
