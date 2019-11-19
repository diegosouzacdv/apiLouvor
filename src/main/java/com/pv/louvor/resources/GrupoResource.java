package com.pv.louvor.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pv.louvor.model.Grupo;
import com.pv.louvor.model.dto.GrupoDTO;
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
		Grupo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
 	}
	
	@GetMapping("/{igreja}/page")
	public ResponseEntity<Page<Grupo>> findPage(@PathVariable Integer igreja, GrupoDTO grupoDto, Pageable pageable) {
		Page<Grupo> obj = service.findPage(grupoDto, pageable, igreja);
		return ResponseEntity.ok().body(obj);
 	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/{igreja}")
	public ResponseEntity<Grupo> insert(@Valid @RequestBody Grupo obj, @PathVariable Integer igreja) {
		obj = service.insert(obj, igreja);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Grupo> update(@RequestBody Grupo obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/desativar/{id}")
	public ResponseEntity<Grupo> disabledGrupo(@RequestBody Grupo obj, @PathVariable Integer id) {
		service.desabilitar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
