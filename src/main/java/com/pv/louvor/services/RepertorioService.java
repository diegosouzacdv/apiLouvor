package com.pv.louvor.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

	public Repertorio find(Integer id) {
		Repertorio obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Repertorio.class.getName());
		}
		return obj;
	}

	@Transactional
	public Repertorio insert(Repertorio obj) {		
		obj.setId(null);
		return repo.save(obj);
	}

	public Repertorio update(Repertorio obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir!");
		}
	}

	public Page<Repertorio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
}
