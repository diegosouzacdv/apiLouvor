package com.pv.louvor;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pv.louvor.model.Categoria;
import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Igreja;
import com.pv.louvor.repositories.CategoriaRepository;
import com.pv.louvor.repositories.FuncaoRepository;
import com.pv.louvor.repositories.IgrejaRepository;

@SpringBootApplication
public class ApiLouvorApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private FuncaoRepository fucaoRepository;
	
	@Autowired
	private IgrejaRepository igrejaRepository;

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
		Igreja i1 = new Igreja(null, "Ministro");
		Igreja i2 = new Igreja(null, "Violonista");
		igrejaRepository.save(Arrays.asList(i1, i2));
		
	}

}
