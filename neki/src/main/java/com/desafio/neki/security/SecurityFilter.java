package com.desafio.neki.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.desafio.neki.repository.UsuarioRepository;
import com.desafio.neki.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;
	
	@Autowired
	UsuarioRepository repository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    String token = recuperarToken(request);
	    
	    if (token != null) {
	            String user = tokenService.validacaoToken(token); 
	            UserDetails usuario = repository.findByUsuario(user);
	            if (usuario != null) {
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	    }
	    
	    filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
	    String authHeader = request.getHeader("Authorization");
	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        return authHeader.replace("Bearer ", "");
	    }
	    return null;
	}

}

