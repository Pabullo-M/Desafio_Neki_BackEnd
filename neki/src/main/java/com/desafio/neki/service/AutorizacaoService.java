package com.desafio.neki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desafio.neki.repository.UsuarioRepository;


@Service
public class AutorizacaoService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository repository;

	 @Override
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	        UserDetails userDetails = repository.findByUsuario(userName);
	        if (userDetails == null) {
	            throw new UsernameNotFoundException("Não foi encontrado o usuário " + userName);
	        }
	        return userDetails;
	    }

}
