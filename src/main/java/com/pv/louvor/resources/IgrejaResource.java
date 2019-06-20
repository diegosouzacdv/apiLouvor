package com.pv.louvor.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/igrejas")
public class IgrejaResource {

	@GetMapping
	public String listar() {
		return "REST está funcionando"; 
 	}
}
