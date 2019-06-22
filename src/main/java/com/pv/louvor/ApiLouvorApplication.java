package com.pv.louvor;

import java.io.ObjectInputStream.GetField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.model.Estudo;
import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Grupo;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Musica;
import com.pv.louvor.model.NotasMusicais;
import com.pv.louvor.model.Pessoa;
import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Tutorial;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.repositories.GrupoRepository;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.repositories.RepertorioRepository;
import com.pv.louvor.repositories.UsuarioRepository;

@SpringBootApplication
public class ApiLouvorApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RepertorioRepository repertorioRepository;


	public static void main(String[] args) {
		SpringApplication.run(ApiLouvorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Categorias
		Categoria c1 = new Categoria(null, "Rádipa");
		Categoria c2 = new Categoria(null, "Média-Rápida");
		categoriaRepository.save(Arrays.asList(c1, c2));
		
		//Funções
		Funcao f1 = new Funcao(null, "Ministro");
		Funcao f2 = new Funcao(null, "Violonista");
		Funcao f3 = new Funcao(null, "Guitarrista");
		//fucaoRepository.save(Arrays.asList(f1, f2,f3));
				
		//Igrejas
		Igreja i1 = new Igreja(null, "Águas Claras");
		Igreja i2 = new Igreja(null, "Samambaia");
		igrejaRepository.save(Arrays.asList(i1, i2));
		
		//Grupo
		Grupo g1 = new Grupo(null, "Avivah", true);
		Grupo g2 = new Grupo(null, "HillSong", true);
		grupoRepository.save(Arrays.asList(g1, g2));
		
		//Tutorial
		Tutorial t1 = new Tutorial();
		t1.setBaixo(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setBateria(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setGuitarra(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setTeclado(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setViolao(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		
		//Estudo
		Estudo e1 = new Estudo("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms", "https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms", 
				120, "https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW", 
				"https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW");	
		
		//NotasMusicais
		
		//Musica
		Musica m1 = new Musica(null, "O Senhor é Bom", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), NotasMusicais.D, NotasMusicais.D, true);
		m1.setEstudo(e1);
		m1.setGrupo(Arrays.asList(g1));
		m1.setCategorias(Arrays.asList(c1));
		m1.setTutorial(t1);	
		musicaRepository.save(m1);
		
		//Pessoa
		Pessoa p1 = new Pessoa("Admin", "(xx)xxxxx-xxxx");
		Pessoa p2 = new Pessoa("Diego Souza", "(61)98576-9860");
		
		//Usuario
		Usuario u1 = new Usuario( null, p2, i1, "diegoguitaibanez@gmail.com", this.geradorSenha("godemais"), false);
		Usuario u2 = new Usuario( null, p1, i1, "admin@gmail.com", this.geradorSenha("admin"), true);
		u1.setFuncao(Arrays.asList(f3));
		u2.setFuncao(Arrays.asList(f1,f2,f3));
		usuarioRepository.save(Arrays.asList(u1, u2));
		
		//Repertorio
		Repertorio r1 = new Repertorio(null, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		//r1.setMusica(Arrays.asList(m1));
		repertorioRepository.save(r1);
		}
	
	@SuppressWarnings("unused")
	private String geradorSenha(String senha) {
		String encoder = "";
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//encoder.encode(senha);
		return senha;
	}
	
	//System.out.println(encoder.encode("admin.planaltosei"));
	//System.out.println(encoder.encode("victor.planaltosei"));
	
	

}
