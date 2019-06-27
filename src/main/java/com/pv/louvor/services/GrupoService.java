package com.pv.louvor.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Grupo;
import com.pv.louvor.repositories.GrupoRepository;
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

	@Transactional
	public Grupo insert(Grupo obj) {
		obj.setId(null);
		obj.setAtivo(true);
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
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir!");
		}
	}
	
	public Page<Grupo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
}
