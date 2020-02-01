package com.pv.louvor.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Musica;
import com.pv.louvor.model.MusicaRepertorio;
import com.pv.louvor.model.Perfil;
import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.repositories.MusicaRepertorioRepository;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.repositories.RepertorioRepository;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.security.UserSS;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

/**
 * @author Diego
 *
 */
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
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
	@Autowired
	private FuncaoRepository funcaoRepository;
	
	
	/**
	 * Busca todos os Repertórios Ativos com o id da igreja.
	 * @return
	 */
	public List<Repertorio> buscarTodos() {
		Igreja igreja = getIgreja();
		if(igreja != null) {
			List<Repertorio> obj = repo.findDistinctByAtivoIsAndIgrejaId(true, igreja.getId());
			return obj;
		} else {			
			throw new ObjectNotFoundException("Igreja não existe! " + 
					", Tipo: " + Igreja.class.getName());
		}
	}
	
	/**
	 * Método que desativa o repertório um dia após a data usando Scheduled rodando dos os dias as 00:10:00;
	 */
	@Scheduled(cron = "0 10 0 * * *")
	public void desativarRepertorio() {
		verificarSegunda();
		List<Repertorio> allRepertorio = repo.findDistinctByAtivoIs(true);
		List<Repertorio> retorno = new ArrayList<>();
		
		for(Repertorio repertorio: allRepertorio) {
			Repertorio obj = repo.findOne(repertorio.getId());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			if(obj != null) {
				LocalDate hoje = LocalDate.now();
				String data = repertorio.getData();
				LocalDate dataRepertorio = LocalDate.parse(data,formatter);
				long diferencaEmDias = ChronoUnit.DAYS.between(hoje, dataRepertorio);
				if(diferencaEmDias <= -1) {
					obj.setAtivo(false);
					repo.save(obj);
				}
			}
			if(repertorio.isAtivo()) {
				retorno.add(repertorio);
			}
		}
				
	}

	/**
	 * Método que busca repertório pelo ID, retornando erro caso não for encontrado.
	 * @param id
	 * @return
	 */
	public Repertorio find(Integer id) {
		Repertorio obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Repertório não encontrado! Id: " + id + 
					", Tipo: " + Repertorio.class.getName());
		}
		return obj;
	}

	/**
	 * O metodo faz um insert de uma repertorio novo
	 * @param obj
	 * @return
	 * @throws ParseException
	 */
	@Transactional
	public Repertorio insert(Repertorio obj) throws ParseException {
		obj.setAtivo(true);
		String data = obj.getData();
		data = data(data);
		obj.setDataSemana(data);
		
		Igreja igreja = getIgreja();
		obj.setIgreja(igreja);
		
		UserSS user = UserService.authenticated();
		if(user != null && user.hasRole(Perfil.ADMIN)) {
			Usuario usuario = usuarioRepository.findByEmail(user.getUsername());
			obj.setCriador(usuario.getPessoa().getNome());
		}
		obj.setId(null);
		obj =  repo.save(obj);
		
		for (MusicaRepertorio mr : obj.getMusicasRepertorio()) {
			System.err.println(obj.getMusicasRepertorio());
			mr.setMusica(musicaRepository.findOne(mr.getMusica().getId()));
			mr.setRepertorio(obj);
		}
		musicaRepertorioRepository.save(obj.getMusicasRepertorio());
		
		/*for(UsuarioEmailDTO email: usuario.buscarTodosEmails()) {
			System.err.println(email);
		emailService.sendOrderConfirmationHtmlEmail(obj, email.getEmail());
		}*/
		
		return obj;
	}

	/**
	 * Metodo desativa um repertorio
	 * @param obj
	 * @param id 
	 * @return
	 */
	@Transactional
	public void update(Repertorio obj, Integer id) {
		Repertorio rep = repo.getOne(id);
		musicaRepertorioRepository.deleteInBatch(rep.getMusicasRepertorio());
		rep.setMusicasRepertorio(obj.getMusicasRepertorio());
		rep = obj;
		repo.save(rep);
		
		for (MusicaRepertorio mr : obj.getMusicasRepertorio()) {
			mr.setMusica(musicaRepository.findOne(mr.getMusica().getId()));
			mr.setRepertorio(obj);
		}
		musicaRepertorioRepository.save(obj.getMusicasRepertorio());
	}
	
	public Repertorio getRepertorio(int id) {
		return repo.findOne(id);
	}
	
	
	/**
	 * Metodo deleta os repertorios por id
	 * @param id
	 */
	public void delete(Integer id) {
		Repertorio obj = find(id);
		obj.setEquipeDoDia(null);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir!");
		}
	}

	/**
	 * metodo faz a paginação dos repertorios que estão ativos
	 * @param igreja 
	 * @param data
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Repertorio> findPage(String data, Integer page, Integer linesPerPage, String orderBy, String direction){
		Igreja igreja = getIgreja();
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findDistinctByDataIgnoreCaseContainingAndIgrejaId(data, igreja.getId(), pageRequest);
	}
	
	/**
	 * metodo retorna o dia da semana conforme o dia do mes passado
	 * @param data
	 * @return
	 * @throws ParseException
	 */
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
        return nome;
        
	}
	
	public void verificarSegunda() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String segunda = hoje.format(formatter);
		try {
			segunda = data(segunda);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(segunda.equals("Segunda-Feira")) {
			List<Usuario> users = usuarioRepository.findAll();
			for (Usuario usuario : users) {
				usuario.setDisponivel(true);
				usuarioRepository.save(usuario);
			}
		}
	}
	
	/**
	 * metodo recebe todos os usuarios naquela determinada funcao
	 * @param id
	 * @return
	 */
	public List<Usuario> getFuncao(Integer id) {
		Igreja igreja = getIgreja();
		System.err.println(igreja.getNome());
		int parseInt = Integer.valueOf(id);
		Funcao funcao = funcaoRepository.findOne(parseInt);
		List<Usuario> listDto = repo.funcoes(funcao, igreja);
		return listDto;
	}
	
	public Igreja getIgreja() {
		UserSS user = getUsuario();
		return igrejaRepository.findOne(user.getIgreja().getId());
	}
	
	public UserSS getUsuario( ) {
		return UserService.authenticated();
	}
}
