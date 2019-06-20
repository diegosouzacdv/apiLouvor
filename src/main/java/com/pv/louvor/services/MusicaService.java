package com.pv.louvor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Musica;
import com.pv.louvor.repositories.MusicaRepository;

@Service
public class MusicaService {
	
	@Autowired
	private MusicaRepository repo;

	public List<Musica> buscarTodos() {
		List<Musica> obj = repo.findAll();
		return obj;
	}

	public Musica buscar(Integer id) {
		Musica obj = repo.findOne(id);
		return obj;
	}

}
