package com.pv.louvor.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.services.RepertorioService;

@RestController
@RequestMapping(value="/repertorios")
public class RepertorioResource {
	
	@Autowired
	private RepertorioService service;
	
	@GetMapping
	public ResponseEntity<List<Repertorio>> findAll() {
		List<Repertorio> obj = service.buscarTodos();
		return ResponseEntity.ok().body(obj);

 	}

	@GetMapping("/{id}")
	public ResponseEntity<Repertorio> find(@PathVariable Integer id) {
		Repertorio obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
 	}

}
