package com.pv.louvor.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.pv.louvor.model.Musica;
import com.pv.louvor.model.dto.Filtro;
import com.pv.louvor.model.dto.MusicaDTO;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.services.MusicaService;

@RestController
@RequestMapping(value="/musicas")
public class MusicaResource {
	
	@Autowired
	private MusicaService service;
	
	@Autowired
	private MusicaRepository repo;
	
	@GetMapping
	public ResponseEntity<List<MusicaDTO>> findAll() {
		List<Musica> list = service.buscarTodos();
		List<MusicaDTO> listDto = list.stream().map(obj -> new MusicaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}

	@GetMapping("/{id}")
	public ResponseEntity<Musica> find(@PathVariable Integer id) {
		Musica obj = service.find(id);
		return ResponseEntity.ok().body(obj);
 	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Musica>> findPage(MusicaDTO musicaDto, Pageable pageable) {
		Page<Musica> musicas = service.getMusicas(musicaDto, pageable);
		return ResponseEntity.ok().body(musicas);
 	}
	
	@GetMapping("/anos")
	public ResponseEntity<List<Filtro>> getAnos() {
		List<Filtro> anos = service.getAnos();
		return ResponseEntity.ok().body(anos);
 	}
	
	@GetMapping("/grupos")
	public ResponseEntity<List<Filtro>> getGrupos() {
		List<Filtro> grupos = service.getGrupos();
		return ResponseEntity.ok().body(grupos);
 	}
	
	@GetMapping("/categorias")
	public ResponseEntity<List<Filtro>> getCategorias() {
		List<Filtro> categorias = service.getCategorias();
		return ResponseEntity.ok().body(categorias);
 	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Musica> insert(@Valid @RequestBody Musica obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Musica> update(@RequestBody Musica obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/desativar/{id}")
	public ResponseEntity<Musica> disabledMusica(@RequestBody Musica obj, @PathVariable Integer id) {
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
