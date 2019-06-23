package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Grupo;
import com.pv.louvor.repositories.GrupoRepository;
import com.pv.louvor.services.exceptions.DataIntegrityException;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository repo;
	
	public List<Grupo> buscarTodos() {
		List<Grupo> obj = repo.findAll();
		return obj;
	}

	public Grupo find(Integer id) {
		Grupo obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Grupo.class.getName());
		}
		return obj;
	}

	public Grupo insert(Grupo obj) {
		obj.setId(null);
		isExist(obj);
		return repo.save(obj);
	}

	public Grupo update(Grupo obj) {
		find(obj.getId());
		isExist(obj);
		return repo.save(obj);
	}
	
	public Grupo isExist(Grupo obj) {
	Grupo obj1 = repo.findByNome(obj.getNome());
		if(obj1 != null && obj1.getNome().equals(obj.getNome())) {
			throw new ObjectFoundException("Objeto Já existe com Id: " + obj.getId() + 
					", Tipo: " + Grupo.class.getName());
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
