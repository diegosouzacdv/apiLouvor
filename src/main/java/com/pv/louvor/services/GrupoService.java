package com.pv.louvor.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Grupo;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.dto.GrupoDTO;
import com.pv.louvor.repositories.GrupoRepository;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.security.UserSS;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository repo;
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
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
		Igreja igreja = getIgreja();
		if(igreja != null) {			
			obj.setIgreja(igreja);
		}
		if (igreja.getNome().equalsIgnoreCase("SEDE")) {
			obj.setSede(true);
		} else {
			obj.setSede(false);
		}
		obj.setId(null);
		obj.setAtivo(true);
		//obj = isExist(obj);
		return repo.save(obj);
	}

	public Grupo update(Grupo obj) {
		find(obj.getId());
		isExist(obj);
		obj.setAtivo(true);
		return repo.save(obj);
	}
	
	public Grupo isExist(Grupo obj) {
	Grupo obj1 = repo.findByNome(obj.getNome());
		if((obj1 != null && obj1.isAtivo()) && obj1.getNome().equals(obj.getNome())) {
			throw new ObjectFoundException("Objeto Já existe com Id: " + obj1.getId() + 
					", Tipo: " + Grupo.class.getName());
		}
		if((obj1 != null && !obj1.isAtivo()) && obj1.getNome().equals(obj.getNome())) {
			obj1.setAtivo(true);
			return obj1;
		}else {
			
			return obj;
		}
	}
	
	public Grupo desabilitar(Integer id) {
		Grupo obj = find(id);
		obj.setAtivo(false);
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
	
	public Page<Grupo> findPage(GrupoDTO grupoDto, Pageable pageable){
		Igreja igreja = getIgreja();
		Igreja sede = getSede();
		Page<Grupo> obj = repo.filtrar(grupoDto, igreja, sede, pageable);
		return obj;
	}
	
	public UserSS getUsuario() {
		return UserService.authenticated();
	}
	
	public Igreja getIgreja() {
		UserSS user = getUsuario();
		return igrejaRepository.findOne(user.getIgreja().getId());
	}
	
	public Igreja getSede() {
		return igrejaRepository.findByNome("Sede");
	}
}
