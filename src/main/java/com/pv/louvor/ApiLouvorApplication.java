package com.pv.louvor;

import java.time.LocalDate;
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
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.repositories.GrupoRepository;
import com.pv.louvor.repositories.IgrejaRepository;
import com.pv.louvor.repositories.MusicaRepository;

@SpringBootApplication
public class ApiLouvorApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private FuncaoRepository fucaoRepository;
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private MusicaRepository musicaRepository;


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
		fucaoRepository.save(Arrays.asList(f1, f2));
		
		//Igrejas
		Igreja i1 = new Igreja(null, "Águas Claras");
		Igreja i2 = new Igreja(null, "Samambaia");
		igrejaRepository.save(Arrays.asList(i1, i2));
		
		//Grupo
		Grupo g1 = new Grupo(null, "Avivah");
		Grupo g2 = new Grupo(null, "HillSong");
		grupoRepository.save(Arrays.asList(g1, g2));
		
		//Estudo
		Estudo e1 = new Estudo("https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms", "https://drive.google.com/open?id=0BzCIxMGAHmIkNHVMQlBLa0loRms", 
				120, "https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW", 
				"https://drive.google.com/open?id=1bsaEgF12BDMrEcwWtqm8-viERnghPEqW");
		//Musica
		Musica m1 = new Musica(null, "O Senhor é Bom",LocalDate.now(), "D", "D");
		m1.setEstudo(e1);
		m1.setGrupo(Arrays.asList(g1));
		m1.setCategorias(Arrays.asList(c1));
		musicaRepository.save(m1);
	}

}
