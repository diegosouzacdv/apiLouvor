package com.pv.louvor.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Categoria;



@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@GetMapping
	public List<Categoria> listar() {
		
	Categoria i1 = new Categoria(1, "Ministro");
	Categoria i2 = new Categoria(1, "Violinista");
	List<Categoria> lista = new ArrayList<>();
	lista.add(i1);
	lista.add(i2);
	return lista;
 	}
}
