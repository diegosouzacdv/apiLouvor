package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Igreja;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class IgrejaService  {
	
	@Autowired
	private IgrejaRepository repo;
	
	public List<Igreja> buscarTodos() {
		List<Igreja> obj = repo.findAll();
		return obj;
	}

	public Igreja find(Integer id) {
		Igreja obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Igreja.class.getName());
		}
		return obj;
	}

	public Igreja insert(Igreja obj) {
		obj.setId(null);
		isExist(obj);
		return repo.save(obj);
	}

	public Igreja update(Igreja obj) {
		find(obj.getId());
		isExist(obj);
		return repo.save(obj);
	}
	
	public Igreja isExist(Igreja obj) {
	Igreja obj1 = repo.findByNome(obj.getNome());
		if(obj1 != null && obj1.getNome().equals(obj.getNome())) {
			throw new ObjectFoundException("Objeto Já existe com Id: " + obj.getId() + 
					", Tipo: " + Igreja.class.getName());
		}		
		return obj;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir!");
		}
	}
	
	public Page<Igreja> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
}
