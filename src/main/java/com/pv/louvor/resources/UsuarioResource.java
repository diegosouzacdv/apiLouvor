package com.pv.louvor.resources;

import java.net.URI;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioDTO;
import com.pv.louvor.model.dto.UsuarioEmailDTO;
import com.pv.louvor.model.dto.UsuarioIgrejaDTO;
import com.pv.louvor.model.dto.UsuarioNewDTO;
import com.pv.louvor.model.dto.UsuarioNovasFuncoesDTO;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FuncaoRepository funcaoRepository;
	

	@GetMapping("/funcoes/all")
	public ResponseEntity<List<Funcao>> findTodasFuncoes() {
		List<Funcao> obj = funcaoRepository.findAll();
		return ResponseEntity.ok().body(obj);
 	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<Usuario> list = service.buscarTodos();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}
	
	@GetMapping("/novosusuarios")
	public ResponseEntity<List<UsuarioDTO>> novosUsuarios() {
		List<Usuario> list = service.novosUsuarios();
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
 	}
	
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
	public ResponseEntity<Usuario> insert(@Valid @RequestBody UsuarioNewDTO objDTO) {
		Usuario obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> updateCadastro(@Valid @RequestBody UsuarioNewDTO objDTO) {
		System.err.println(objDTO.getFuncao());
		Usuario obj = service.updateRetornUser(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		System.err.println(obj);
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	@PutMapping("/pessoais/{id}")
	public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario obj, @PathVariable Integer id) {
		obj.setId(id);   
		obj = service.update(obj);
		System.err.println(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/funcoes/{id}")
	public ResponseEntity<Usuario> updateUsuarioFuncao(@Valid @RequestBody UsuarioNovasFuncoesDTO obj, @PathVariable Integer id) {
		Usuario usuario = service.novasFuncoes(obj, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	} 
	
	@PutMapping("/igreja/{id}")
	public ResponseEntity<Usuario> updateUsuarioIgreja(@Valid @RequestBody UsuarioIgrejaDTO obj, @PathVariable Integer id) {
		Usuario usuario = service.usuarioIgreja(obj, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	} 
	
	@PutMapping("/ativar/{id}")
	public ResponseEntity<Usuario> ativarUsuario(@PathVariable Integer id) {
		service.ativarUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/disponivel/{id}")
	public ResponseEntity<Usuario> disponivelRepertorio(@PathVariable Integer id) {
		service.disponivelRepertorio(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/perfil/{perfil}/{id}")
	public ResponseEntity<Usuario> updatePerfil(@PathVariable int perfil, @PathVariable Integer id) {
		Usuario usuario = service.perfisADD(id, perfil);
		System.err.println(usuario);
		service.update(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/change/{id}/{idUser}")
	public ResponseEntity<Usuario> changeIgreja(@PathVariable int id, @PathVariable Integer idUser) {
		service.changeIgreja(idUser, id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/picture")
	public ResponseEntity<Usuario> uploadProfilePicture(@RequestParam MultipartFile multipartFile) {
		URI uri = service.uploadProfilePicture(multipartFile);
		return ResponseEntity.created(uri).build();
	}

}
