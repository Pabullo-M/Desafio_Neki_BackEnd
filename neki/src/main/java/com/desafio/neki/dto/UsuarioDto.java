package com.desafio.neki.dto;

import com.desafio.neki.model.Usuario;

public record UsuarioDto(
		
		String usuario,
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
