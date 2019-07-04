package com.pv.louvor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.model.Equipe;
import com.pv.louvor.model.Estudo;
import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Grupo;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Musica;
import com.pv.louvor.model.MusicaRepertorio;
import com.pv.louvor.model.NotasMusicais;
import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Tutorial;
import com.pv.louvor.model.Usuario;
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.repositories.GrupoRepository;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.repositories.MusicaRepertorioRepository;
import com.pv.louvor.repositories.MusicaRepository;
import com.pv.louvor.repositories.RepertorioRepository;
import com.pv.louvor.repositories.UsuarioRepository;

@SpringBootApplication
public class ApiLouvorApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
//	@Autowired
//	private FuncaoRepository funcaoRepository;
	
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
	
	@Autowired
	private MusicaRepertorioRepository musicaRepertorioRepository;
	
	@Autowired
	private FuncaoRepository fucaoRepository;


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
		fucaoRepository.save(Arrays.asList(f1, f2,f3));
				
		//Igrejas
		Igreja i1 = new Igreja(null, "Águas Claras");
		i1.setAtivo(true);
		Igreja i2 = new Igreja(null, "Samambaia");
		i2.setAtivo(true);
		igrejaRepository.save(Arrays.asList(i1, i2));
		
		//Grupo
		Grupo g1 = new Grupo(null, "Avivah");
		Grupo g2 = new Grupo(null, "HillSong");
		grupoRepository.save(Arrays.asList(g1, g2));
		
		//Tutorial
		Tutorial t1 = new Tutorial();
		t1.setBaixo(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setBateria(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setGuitarra(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setTeclado(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t1.setViolao(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		
		Tutorial t2 = new Tutorial();
		t2.setBaixo(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t2.setBateria(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t2.setGuitarra(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t2.setTeclado(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		t2.setViolao(Arrays.asList("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms"));
		
		//Estudo
		Estudo e1 = new Estudo("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms", "https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms", 
				120, "https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW", 
				"https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW");	
		
		//NotasMusicais
		
		//Musica
		Musica m1 = new Musica(null, "O Senhor é Bom", NotasMusicais.D, NotasMusicais.D, true);
		m1.setEstudo(e1);
		m1.setDataInserida(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		m1.setGrupo(Arrays.asList(g1));
		m1.setCategorias(Arrays.asList(c1));
		m1.setTutorial(t1);	
		Musica m2 = new Musica(null, "O Senhor é Bom", NotasMusicais.D, NotasMusicais.D, true);
		m2.setEstudo(e1);
		m2.setDataInserida(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		m2.setGrupo(Arrays.asList(g1));
		m2.setCategorias(Arrays.asList(c1));
		m2.setTutorial(t2);	
		musicaRepository.save(Arrays.asList(m1,m2));
		
		
		//Usuario
		Usuario u1 = new Usuario( null, "Diego", "(xx)xxxxx-xxxx", "diegoguitaibanez@gmail.com", this.geradorSenha("godemais"));
		Usuario u2 = new Usuario( null, "Admin", "(61)98576-9860", "admin@gmail.com", this.geradorSenha("admin"));
		u1.setFuncao(Arrays.asList(f3));
		u2.setFuncao(Arrays.asList(f1,f2,f3));
		u1.setIgreja(i1);
		u2.setIgreja(i1);
		usuarioRepository.save(Arrays.asList(u1, u2));
		
		//Equipe
		Equipe eq1 = new Equipe();
		eq1.setBaterista(u1.getPessoa().getNome());
		eq1.setGuitarrista(u2.getPessoa().getNome());
		eq1.setMinistro(Arrays.asList(u1.getPessoa().getNome()));
		eq1.setTecladista(u2.getPessoa().getNome());
		eq1.setViolonista(u2.getPessoa().getNome());
		

		//Repertorio
		Repertorio r1 = new Repertorio();
		r1.setData(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		r1.setEquipeDoDia(eq1);
		
		repertorioRepository.save(r1);

		//MusicaRepertorio
		MusicaRepertorio ms1 = new MusicaRepertorio(r1, m1);
		MusicaRepertorio ms2 = new MusicaRepertorio(r1, m2);
		
		r1.getMusicasRepertorio().addAll(Arrays.asList(ms1,ms2));
		m1.getMusicasRepertorio().addAll(Arrays.asList(ms1,ms2));
		

		musicaRepertorioRepository.save(Arrays.asList(ms1,ms2));
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
