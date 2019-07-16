package com.pv.louvor.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Musica;
import com.pv.louvor.model.MusicaRepertorio;
import com.pv.louvor.model.Perfil;
import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioEmailDTO;
import com.pv.louvor.repositories.MusicaRepertorioRepository;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.repositories.RepertorioRepository;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.security.UserSS;
import com.pv.louvor.services.exceptions.AuthorizationException;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class RepertorioService {
	
	@Autowired
	private RepertorioRepository repo;
	@Autowired
	private MusicaRepertorioRepository musicaRepertorioRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UsuarioService usuario;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Repertorio> buscarTodos() {
		List<Repertorio> obj = repo.findAll();
		return obj;
	}

	public Repertorio find(Integer id) {
		Repertorio obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Repertorio.class.getName());
		}
		return obj;
	}

	@Transactional
	public Repertorio insert(Repertorio obj) throws ParseException {
		
		String data = obj.getData();
		data = this.data(data);
		obj.setData(obj.getData() + " " + data );

		UserSS user = UserService.authenticated();
		if(user != null && user.hasRole(Perfil.ADMIN)) {
			Usuario usuario = usuarioRepository.findByEmail(user.getUsername());
			obj.setCriador(usuario.getPessoa().getNome());
		}
		obj.setId(null);
		obj =  repo.save(obj);
		
		for (MusicaRepertorio mr : obj.getMusicasRepertorio()) {
			mr.setMusica(musicaRepository.findOne(mr.getMusica().getId()));
			mr.setRepertorio(obj);
		}
		musicaRepertorioRepository.save(obj.getMusicasRepertorio());
		for(UsuarioEmailDTO email: usuario.buscarTodosEmails()) {
		emailService.sendOrderConfirmationHtmlEmail(obj, email.getEmail());
		}
		return obj;
	}

	public Repertorio update(Repertorio obj) {
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

	public Page<Repertorio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
	
	public String data(String data ) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(data);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        String nome = ""; 
        int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
        switch(diaDaSemana){ 
        case Calendar.SUNDAY: nome = "Domingo"; break; 
        case Calendar.MONDAY: nome = "Segunda-Feira"; break; 
        case Calendar.TUESDAY: nome = "Terça-Feira"; break; 
        case Calendar.WEDNESDAY: nome = "Quarta-Feira"; break; 
        case Calendar.THURSDAY: nome = "Quinta-Feira"; break; 
        case Calendar.FRIDAY: nome = "Sexta-Feira"; break; 
        case Calendar.SATURDAY: nome = "Sábado"; break; 
        } 
        System.err.println(nome);
        return nome;
        
	}
}
