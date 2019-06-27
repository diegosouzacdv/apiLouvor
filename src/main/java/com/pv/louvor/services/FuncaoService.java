package com.pv.louvor.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class FuncaoService  {
	
	@Autowired
	private FuncaoRepository repo;
	
	public List<Funcao> buscarTodos() {
		List<Funcao> obj = repo.findAll();
		return obj;
	}

	public Funcao find(Integer id) {
		Funcao obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Funcao.class.getName());
		}
		return obj;
	}

	@Transactional
	public Funcao insert(Funcao obj) {
		obj.setId(null);
		isExist(obj);
		return repo.save(obj);
	}

	public Funcao update(Funcao obj) {
		find(obj.getId());
		isExist(obj);
		return repo.save(obj);
	}
	
	public Funcao isExist(Funcao obj) {
	Funcao obj1 = repo.findByNome(obj.getNome());
		if(obj1 != null && obj1.getNome().equals(obj.getNome())) {
			throw new ObjectFoundException("Objeto Já existe com Id: " + obj.getId() + 
					", Tipo: " + Funcao.class.getName());
		}		
		return obj;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é ´possível excluir!");
		}
	}
}
