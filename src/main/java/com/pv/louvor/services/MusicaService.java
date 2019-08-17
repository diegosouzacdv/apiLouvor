package com.pv.louvor.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.model.Musica;
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class MusicaService {
	
	@Autowired
	private MusicaRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Musica> buscarTodos() {
		List<Musica> obj = repo.findAll();
		return obj;
	}

	public Musica find(Integer id) {
		Musica obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Musica.class.getName());
		}
		return obj;
	}

	@Transactional
	public Musica insert(Musica obj) {
		//obj.setId(null);
		obj.setAtivo(true);
		obj.setDataInserida(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
		isExist(obj);
		return repo.save(obj);
	}

	public Musica update(Musica obj) {
		obj.setAtivo(true);
		return repo.save(obj);
	}
	
	
	public Musica desabilitar(Integer id) {
		Musica obj = find(id);
		obj.setAtivo(false);
		System.err.println(">>>>>>>>>>>>" +obj.getDataInserida());
		return repo.save(obj);
	}
	
	public Musica isExist(Musica obj) {
	Musica obj1 = repo.findByNome(obj.getNome());
		if(obj1 != null && obj1.getNome().equals(obj.getNome())) {
			throw new ObjectFoundException("Objeto Já existe com Id: " + obj.getId() + 
					", Tipo: " + Musica.class.getName());
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

	public Page<Musica> findPage(String nome, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		boolean ativo = true;
		return repo.findDistinctByNomeIgnoreCaseContainingAndAtivoIs(nome, ativo, pageRequest);
	}
}
