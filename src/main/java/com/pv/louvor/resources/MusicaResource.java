package com.pv.louvor.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Musica;
import com.pv.louvor.services.MusicaService;

@RestController
@RequestMapping(value="/musicas")
public class MusicaResource {
	
	@Autowired
	private MusicaService service;
	
	@GetMapping
	public ResponseEntity<List<Musica>> findAll() {
		List<Musica> obj = service.buscarTodos();
		return ResponseEntity.ok().body(obj);

 	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Musica> find(@PathVariable Integer id) {
	
		Musica obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

 	}

}
