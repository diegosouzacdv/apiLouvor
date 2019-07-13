package com.pv.louvor.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioDTO;
import com.pv.louvor.model.dto.UsuarioEmailDTO;
import com.pv.louvor.model.dto.UsuarioNewDTO;
import com.pv.louvor.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> list = service.buscarTodos();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/allemail")
	public ResponseEntity<List<UsuarioEmailDTO>> findAllEmail() {
		List<Usuario> list = service.buscarTodos();
		List<UsuarioEmailDTO> listDto = list.stream().map(obj -> new UsuarioEmailDTO(obj.getEmail())).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
 	}
	
	@GetMapping("/email")
	public ResponseEntity<Usuario> findByEmail(@RequestParam(value="value") String email) {
		Usuario obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
 	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<Usuario>> findPage(@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value="direction", defaultValue = "DESC") String direction) {
		Page<Usuario> obj = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(obj);
 	}
	
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody UsuarioNewDTO objDTO) {
		Usuario obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping("/pessoais/{id}")
	public ResponseEntity<Usuario> update(@RequestBody Usuario obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/picture")
	public ResponseEntity<Usuario> uploadProfilePicture(@RequestParam(name="file") MultipartFile multipartFile) {
		URI uri = service.uploadProfilePicture(multipartFile);
		return ResponseEntity.created(uri).build();
	}

}
