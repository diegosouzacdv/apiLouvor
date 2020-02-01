package com.pv.louvor.resources;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.RepertorioDTO;
import com.pv.louvor.model.dto.UsuarioFuncoesDTO;
import com.pv.louvor.resources.utils.URL;
import com.pv.louvor.services.RepertorioService;

@RestController
@RequestMapping(value="/repertorios")
public class RepertorioResource {
	
	@Autowired
	private RepertorioService service;
	
	@GetMapping
	public ResponseEntity<List<RepertorioDTO>> findAll() {
		List<Repertorio> list = service.buscarTodos();
		List<RepertorioDTO> listDto = list.stream().map(obj -> new RepertorioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

 	}
	
	@GetMapping("/funcoes/{id}")
	public ResponseEntity<List<UsuarioFuncoesDTO>> funcoes(@PathVariable Integer id) {
		List<Usuario> listUsuario = service.getFuncao(id);
		List<UsuarioFuncoesDTO> listDto = listUsuario.stream().map(obj -> new UsuarioFuncoesDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Repertorio>> findAllComplet() {
		List<Repertorio> list = service.buscarTodos();
		return ResponseEntity.ok().body(list);

 	}

	@GetMapping("/{id}")
	public ResponseEntity<Repertorio> find(@PathVariable Integer id) {
		Repertorio obj = service.find(id);
		return ResponseEntity.ok().body(obj);
 	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Repertorio>> findPage(
			@RequestParam(value="data", defaultValue = "") String data,
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value="direction", defaultValue = "DESC") String direction) {
		String dataDecoded = URL.decodeParam(data);
		Page<Repertorio> obj = service.findPage(dataDecoded, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(obj);
 	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Repertorio> insert(@Valid @RequestBody Repertorio obj) throws ParseException {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Repertorio obj, @PathVariable Integer id) {
		obj.setId(id);
		service.update(obj, id);
		return ResponseEntity.ok("Atualizado");
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
