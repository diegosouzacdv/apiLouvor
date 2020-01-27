package com.pv.louvor.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.VersaoApp;
import com.pv.louvor.repositories.VersaoRepository;

@RestController
@RequestMapping(value="/versao")
public class VersaoResource {
	
	@Autowired
	private VersaoRepository repo;
	
	@GetMapping
	public ResponseEntity<List<VersaoApp>> findAll() {
		List<VersaoApp> obj = repo.findAll();
		return ResponseEntity.ok().body(obj);
 	}

}
