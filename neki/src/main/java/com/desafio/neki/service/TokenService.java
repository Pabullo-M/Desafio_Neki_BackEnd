package com.desafio.neki.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.desafio.neki.dto.LoginResponseDto;
import com.desafio.neki.model.Usuario;


@Service
public class TokenService {
		
String secret = "a";

public LoginResponseDto geracaoToken(Usuario usuario) {

		
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("pmp-api")
					.withSubject(usuario.getUsuario())
					.withExpiresAt(geracaoDeDataExpiracao())
					.sign(algorithm);
					//caso precise expirar o token adc o .withExpiredAt()
			LoginResponseDto login = new LoginResponseDto(token, usuario.getId());
			return login;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro na geração do token", e);
		}
	}
	private Instant geracaoDeDataExpiracao () {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	public String validacaoToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("pmp-api")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException e) {
			return "";
		}
	}
	
	
}
