package com.pv.louvor.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
	
	@Autowired
	private S3Services s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

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
		Usuario aux = repo.findOne(obj.getId());
		if(aux != null) {
			obj.setSenha(aux.getSenha());
		}
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
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario obj = repo.findByEmail(email);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId()
					+ ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
}
