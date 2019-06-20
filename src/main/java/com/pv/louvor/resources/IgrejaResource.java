package com.pv.louvor.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.Igreja;

@RestController
@RequestMapping(value="/igrejas")
public class IgrejaResource {

	@GetMapping
	public List<Igreja> listar() {
		
	Igreja i1 = new Igreja(1, "Águas Claras");
	Igreja i2 = new Igreja(1, "Samambaia");
	List<Igreja> lista = new ArrayList<>();
	lista.add(i1);
	lista.add(i2);
	return lista;
 	}
}
