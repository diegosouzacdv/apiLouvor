package com.pv.louvor.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
		Igreja obj = service.find(id);
		return ResponseEntity.ok().body(obj);

 	}
	
	@PostMapping
	public ResponseEntity<Igreja> insert(@RequestBody Igreja obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Igreja> update(@RequestBody Igreja obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
