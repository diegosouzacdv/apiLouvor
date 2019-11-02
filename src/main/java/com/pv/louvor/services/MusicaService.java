package com.pv.louvor.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Musica;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class MusicaService {
	
	@Autowired
	private MusicaRepository repo;

	public List<Musica> buscarTodos() {
		List<Musica> obj = repo.findAll();
		return obj;
	}

	public Musica find(Integer id) {
		Musica obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Música não encontrada! Id: " + id + 
					", Tipo: " + Musica.class.getName());
		}
		return obj;
	}

	@Transactional
	public Musica insert(Musica obj) {
		obj = isExist(obj);
		obj.setAtivo(true);
		return repo.save(obj);
	}

	public Musica update(Musica obj) {
		obj.setAtivo(true);
		return repo.save(obj);
	}
	
	
	public Musica desabilitar(Integer id) {
		Musica obj = find(id);
		obj.setAtivo(false);
		return repo.save(obj);
	}
	
	public Musica isExist(Musica obj) {
	Musica obj1 = repo.findByNomeIgnoreCase(obj.getNome());
		if((obj1 != null && obj1.isAtivo()) && 
				obj1.getNome().toLowerCase().equals(obj.getNome().toLowerCase()) && 
				obj1.getGrupo().getId().equals(obj.getGrupo().getId()) &&
				obj1.getDataInserida().toLowerCase().equals(obj.getDataInserida())) {
			throw new ObjectFoundException("Objeto Já existe com Id: " + obj1.getId() + 
					", Tipo: " + Musica.class.getName());
		}
		if((obj1 != null && !obj1.isAtivo()) && 
				obj1.getNome().toLowerCase().equals(obj.getNome().toLowerCase()) && 
				obj1.getGrupo().getId().equals(obj.getGrupo().getId()) &&
				obj1.getDataInserida().toLowerCase().equals(obj.getDataInserida())) {
			return obj1;
		} else {
			return obj;
		}
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir!");
		}
	}

	public Page<Musica> findPage(String nome, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		boolean ativo = true;
		return repo.findDistinctByNomeIgnoreCaseContainingAndAtivoIs(nome, ativo, pageRequest);
	}
}
