package com.pv.louvor.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.services.FuncaoService;

@RestController
@RequestMapping(value="/funcoes")
public class FuncaoResource  {

	@Autowired
	private FuncaoService service;
	
	@GetMapping
	public ResponseEntity<List<Funcao>> findAll() {
		List<Funcao> obj = service.buscarTodos();
		return ResponseEntity.ok().body(obj);
 	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcao> find(@PathVariable Integer id) {
		Funcao obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
 	}
}
