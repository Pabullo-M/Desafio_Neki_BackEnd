package com.desafio.neki.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String usuario;
	private String senha;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<UsuarioSkill> usuarioSkills;

	public Usuario() {
		super();
	}
	
	
	
	public Usuario(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}



	public Usuario(Long id, String usuario, String senha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public List<UsuarioSkill> getUsuarioSkills() {
		return usuarioSkills;
	}


	public void setUsuarioSkills(List<UsuarioSkill> usuarioSkills) {
		this.usuarioSkills = usuarioSkills;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}



	@Override
	public String getPassword() {
		return senha;
	}



	@Override
	public String getUsername() {
		return usuario;
	}
	
	
	
	
	
	
}
