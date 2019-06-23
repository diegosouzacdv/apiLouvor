package com.pv.louvor.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pv.louvor.model.Repertorio;
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
		Repertorio obj = service.find(id);
		return ResponseEntity.ok().body(obj);
 	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Repertorio>> findPage(@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		Page<Repertorio> obj = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(obj);
 	}
	
	@PostMapping
	public ResponseEntity<Repertorio> insert(@RequestBody Repertorio obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Repertorio> update(@RequestBody Repertorio obj, @PathVariable Integer id) {
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
