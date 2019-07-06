package com.pv.louvor.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.pv.louvor.model.Musica;
import com.pv.louvor.model.dto.MusicaDTO;
import com.pv.louvor.resources.utils.URL;
import com.pv.louvor.services.MusicaService;

@RestController
@RequestMapping(value="/musicas")
public class MusicaResource {
	
	@Autowired
	private MusicaService service;
	
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
	public ResponseEntity<Page<MusicaDTO>> findPage(
			@RequestParam(value="nome", defaultValue = "") String nome,
			@RequestParam(value="categorias", defaultValue = "") String categorias,
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Musica> list = service.findPage(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<MusicaDTO> listDTO = list.map(obj -> new MusicaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
 	}
	
	@PostMapping
	public ResponseEntity<Musica> insert(@Valid @RequestBody Musica obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Musica> update(@RequestBody Musica obj, @PathVariable Integer id) {
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
