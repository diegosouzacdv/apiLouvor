package com.pv.louvor.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.louvor.model.dto.UsuarioEmailDTO;
import com.pv.louvor.security.JWTUtil;
import com.pv.louvor.security.UserSS;
import com.pv.louvor.services.AuthService;
import com.pv.louvor.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
	UserSS user = UserService.authenticated();
	String token = jwtUtil.generateToken(user.getUsername());
	response.addHeader("Authorization", "Bearer " + token);
	response.addHeader("access-control-expose-headers", "Authorization");
	return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@RequestBody UsuarioEmailDTO objDto) {
		System.err.println(objDto.getEmail());
		service.sendNewPassword(objDto.getEmail());
	return ResponseEntity.noContent().build();
	}
}
