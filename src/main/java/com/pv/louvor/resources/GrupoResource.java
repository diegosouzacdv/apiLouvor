package com.pv.louvor.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Grupo;
import com.pv.louvor.services.GrupoService;

@RestController
@RequestMapping(value="/grupos")
public class GrupoResource {
	
	@Autowired
	private GrupoService service;
	
	@GetMapping
	public ResponseEntity<List<Grupo>> findAll() {
		List<Grupo> obj = service.buscarTodos();
		return ResponseEntity.ok().body(obj);

 	}

	@GetMapping("/{id}")
	public ResponseEntity<Grupo> find(@PathVariable Integer id) {
		Grupo obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
 	}

}
