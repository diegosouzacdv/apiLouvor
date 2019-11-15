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

import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Perfil;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioEmailDTO;
import com.pv.louvor.model.dto.UsuarioNewDTO;
import com.pv.louvor.repositories.IgrejaRepository;
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
	private IgrejaRepository igrejaRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	public List<Usuario> buscarTodos(Integer id) {
		Igreja igreja = getIgreja(id);
		List<Usuario> obj = repo.findByAtivoAndIgrejaId(true, igreja.getId());
		return obj;
	}

	public List<Usuario> novosUsuarios(Integer id) {
		Igreja igreja = getIgreja(id);
		if(igreja != null) {
		List<Usuario> obj = repo.findByAtivoAndIgrejaId(false, igreja.getId());
		return obj;
		} else {			
			throw new ObjectNotFoundException("Igreja não existe! Id: " + id + 
					", Tipo: " + Igreja.class.getName());
		}
	}

	public List<UsuarioEmailDTO> buscarTodosEmails() {
		List<Usuario> list = repo.findAll();
		List<UsuarioEmailDTO> listDto = list.stream().map(obj -> new UsuarioEmailDTO(obj.getEmail()))
				.collect(Collectors.toList());
		return listDto;
	}

	public Usuario find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {

			throw new AuthorizationException("Acesso Negado");
		}

		Usuario obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public Usuario update(Usuario obj) {

		Usuario aux = repo.findOne(obj.getId());
		if (aux != null) {
			obj.setSenha(aux.getSenha());
		}
		this.isExistePorId(obj);
		find(obj.getId());
		if (aux.getPerfis().size() > 1) {
			obj.addPerfil(Perfil.ADMIN);
		}
		return repo.save(obj);
	}

	public void ativarUsuario(Integer id, String igreja) {
		Igreja objIgreja = igrejaRepository.findByNome(igreja);
		Usuario obj = repo.findOne(id);
		if(obj != null) {
			obj.setAtivo(true);	
			obj.setIgreja(objIgreja);
		}
		repo.save(obj); 
	}
	
	public void disponivelRepertorio(Integer id) {
		Usuario obj = repo.findOne(id);
		if(obj.isDisponivel()) {
			obj.setDisponivel(false);
		System.err.println("Não disponivel ");
		} else {
			obj.setDisponivel(true);
		System.err.println("disponivel para o repertorio");
		}
		repo.save(obj); 
	}
	
	

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir!");
		}
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Igreja getIgreja(Integer id) {
		return igrejaRepository.findOne(id);
	}
	

	@Transactional
	public Usuario insert(Usuario obj) {
		this.isExistePorEmail(obj);
		obj.setId(null);
		obj.setAtivo(false);
		
		return repo.save(obj);
	}
	
	@Transactional
	public Usuario updateRetornUser(UsuarioNewDTO objNew) {
		System.err.println(objNew);
		Igreja igreja = igrejaRepository.findOne(objNew.getIgreja());
		System.err.println(igreja);
		Usuario obj = repo.findById(objNew.getId());
		if(obj == null) {
			throw new ObjectNotFoundException("Usuário não encontrado com id: " + objNew.getId());
		} else {
			obj.setIgreja(igreja);
			System.err.println(obj);
			obj = this.updateDto(objNew, obj);
			obj.setAtivo(false);
			return repo.save(obj);
		}
	}
	
	public Usuario updateDto(UsuarioNewDTO objDTO, Usuario user) {
		Usuario obj = new Usuario(user.getId(), objDTO.getNome(), objDTO.getTelefone(), user.getEmail(), user.getSenha(), user.getIgreja());
		return obj;
	}

	public Usuario fromDTO(UsuarioNewDTO objDTO) {
		Usuario user = new Usuario(null, objDTO.getNome(), objDTO.getTelefone(), objDTO.getEmail(),
				pe.encode(objDTO.getSenha()));
		return user;
	}

	public Usuario isExistePorEmail(Usuario obj) {
		Usuario obj1 = repo.findByEmail(obj.getEmail());
		if (obj1 != null && obj1.getEmail().equals(obj.getEmail())) {
			throw new ObjectFoundException("Usuário Já existe com esse e-mail: " + obj.getEmail());
		}
		return obj;
	}

	public Usuario isExistePorId(Usuario obj) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriID = Integer.parseInt(map.get("id"));

		Usuario aux = repo.findByEmail(obj.getEmail());
		if (aux != null && !aux.getId().equals(uriID)) {
			throw new ObjectFoundException("Usuário Já existe com esse e-mail: " + obj.getEmail());
		}
		return obj;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";
		//return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		return null;
	}

	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	public void salvarNoBanco(String nomeFoto, String contentType) {
		System.err.println("entrando aqui");
		UserSS user = UserService.authenticated();
		if(user != null) {			
			Usuario obj = repo.findOne(user.getId());
			if(obj != null) {
				obj.setFoto(nomeFoto);
				obj.setContentType(contentType);
				repo.save(obj);
			}
		}
	}
}
