package com.pv.louvor.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Funcao;

@RestController
@RequestMapping(value="/funcoes")
public class FuncaoResource  {
	@GetMapping
	public List<Funcao> listar() {
		
	Funcao i1 = new Funcao(1, "Águas Claras");
	Funcao i2 = new Funcao(1, "Samambaia");
	List<Funcao> lista = new ArrayList<>();
	lista.add(i1);
	lista.add(i2);
	return lista;
 	}
}
