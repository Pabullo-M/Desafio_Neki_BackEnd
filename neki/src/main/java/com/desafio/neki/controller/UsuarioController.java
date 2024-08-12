package com.desafio.neki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.neki.dto.LoginResponseDto;
import com.desafio.neki.dto.UsuarioDto;
import com.desafio.neki.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {
	
	@Autowired
	UsuarioService service;

	@PostMapping("/cadastro")
	public ResponseEntity cadastroUsuario (@RequestBody @Valid UsuarioDto data) {
		
		return service.cadastroUsuario(data);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> loginUsuario (@RequestBody @Valid UsuarioDto data) {
		
		return service.loginUsuario(data);
	}
	
}
