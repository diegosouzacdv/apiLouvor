package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	public List<Usuario> buscarTodos() {
		List<Usuario> obj = repo.findAll();
		return obj;
	}

	public Usuario buscar(Integer id) {
		Usuario obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
}
