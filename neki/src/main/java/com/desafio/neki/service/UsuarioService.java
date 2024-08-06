package com.desafio.neki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.neki.dto.UsuarioDto;
import com.desafio.neki.model.Usuario;
import com.desafio.neki.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	TokenService tokenService;
	
	public ResponseEntity cadastroUsuario (UsuarioDto data) {

		String senhaEncryp = new BCryptPasswordEncoder().encode(data.senha());
		Usuario usuario =  data.toEntity(data);
		usuario.setSenha(senhaEncryp);
		repository.save(usuario);
		
		return ResponseEntity.ok("Cadastro de usuario realizado com sucesso!!");
		
	}
	public ResponseEntity loginUsuario(@RequestBody @Valid UsuarioDto data) {
	    try {
	        var senhaUsuario = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
	        var auth = authManager.authenticate(senhaUsuario);
	        var token = tokenService.geracaoToken((Usuario) auth.getPrincipal());

	        return ResponseEntity.ok(token);
	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}
	
}
