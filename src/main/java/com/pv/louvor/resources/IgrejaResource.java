package com.pv.louvor.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Igreja;
import com.pv.louvor.services.IgrejaService;

@RestController
@RequestMapping(value="/igrejas")
public class IgrejaResource {

	@Autowired
	private IgrejaService service;
	
	@GetMapping
	public ResponseEntity<List<Igreja>> findAll() {
		List<Igreja> obj = service.buscarTodos();
		return ResponseEntity.ok().body(obj);

 	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Igreja> find(@PathVariable Integer id) {
	
		Igreja obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

 	}
}
