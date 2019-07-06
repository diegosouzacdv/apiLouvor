package com.pv.louvor.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import com.pv.louvor.model.Perfil;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioEmailDTO;
import com.pv.louvor.model.dto.UsuarioNewDTO;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.security.UserSS;
import com.pv.louvor.services.exceptions.AuthorizationException;
import com.pv.louvor.services.exceptions.ObjectFoundException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private HttpServletRequest request;

	public List<Usuario> buscarTodos() {
		List<Usuario> obj = repo.findAll();
		return obj;
	}
	
	public List<UsuarioEmailDTO> buscarTodosEmails() {
		List<Usuario> list = repo.findAll();
		List<UsuarioEmailDTO> listDto = list.stream().map(obj -> new UsuarioEmailDTO(obj.getEmail())).collect(Collectors.toList());
		return listDto;
	}

	public Usuario find(Integer id) {
		
		UserSS user = UserService.authenticated();
		System.err.println(user);
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			
			throw new AuthorizationException("Acesso Negado");
		}
		
		Usuario obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Usuário não encontrado! Id: " + id + 
					", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
	
	

	public Usuario update(Usuario obj) {
		this.isExistePorId(obj);
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir!");
		}
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}

	@Transactional
	public Usuario insert(Usuario obj) {
		this.isExistePorEmail(obj);
		obj.setId(null);
		obj.setAtivo(false);
		return repo.save(obj);
	}

	public Usuario fromDTO(UsuarioNewDTO objDTO) {
		Usuario user = new Usuario(null, objDTO.getNome(), objDTO.getTelefone(), objDTO.getEmail(), pe.encode(objDTO.getSenha()));
		return user;
	}
	
	public Usuario isExistePorEmail(Usuario obj) {
		Usuario obj1 = repo.findByEmail(obj.getEmail());
			if(obj1 != null && obj1.getEmail().equals(obj.getEmail())) {
				throw new ObjectFoundException("Usuário Já existe com esse e-mail: " + obj.getEmail());
			}		
			return obj;
		}
		
		public Usuario isExistePorId(Usuario obj) {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			Integer uriID = Integer.parseInt(map.get("id"));
			
			Usuario aux = repo.findByEmail(obj.getEmail());
			if(aux != null && !aux.getId().equals(uriID)) {
				throw new ObjectFoundException("Usuário Já existe com esse e-mail: " + obj.getEmail());
			}
			return obj;
		}
	
	
}
