package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.services.exceptions.DataIntegrityException;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	public List<Usuario> buscarTodos() {
		List<Usuario> obj = repo.findAll();
		return obj;
	}

	public Usuario find(Integer id) {
		Usuario obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Usuário não encontrado! Id: " + id + 
					", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		isExist(obj);
		return repo.save(obj);
	}

	public Usuario update(Usuario obj) {
		find(obj.getId());
		isExist(obj);
		return repo.save(obj);
	}
	
	public Usuario isExist(Usuario obj) {
	Usuario obj1 = repo.findByEmail(obj.getEmail());
		if(obj1 != null && obj1.getEmail().equals(obj.getEmail())) {
			throw new ObjectFoundException("Usuário Já existe com Id: " + obj.getId() + 
					", Tipo: " + Usuario.class.getName());
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
