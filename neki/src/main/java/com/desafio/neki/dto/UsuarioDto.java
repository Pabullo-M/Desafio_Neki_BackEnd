package com.desafio.neki.dto;

import com.desafio.neki.model.Usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
		
		@NotBlank(message = "Usuario deve estar preenchido!!")
		String usuario,
		@NotBlank(message = "Senha deve estar preenchida!!")
		String senha
		
		) {
	
	
	public Usuario toEntity(UsuarioDto u) {
		
		Usuario usuario = new Usuario(
				u.usuario,
				u.senha
		);
		
		return usuario;
				
				
	}

}
