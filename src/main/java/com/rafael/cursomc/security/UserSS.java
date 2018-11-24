package com.rafael.cursomc.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rafael.cursomc.domain.enums.Perfil;

public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Integer cdUsuario;
	private String dsEmail;
	private String dsSenha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
	}
	
	
	public UserSS(Integer cdUsuario, String dsEmail, String dsSenha,
			Set<Perfil> perfis) {
		super();
		this.cdUsuario = cdUsuario;
		this.dsEmail = dsEmail;
		this.dsSenha = dsSenha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDsPerfil())).collect(Collectors.toList());
	}


	public Integer getCdUsuario() {
		return cdUsuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return dsSenha;
	}

	@Override
	public String getUsername() {
		return dsEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Perfil perfil){
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDsPerfil()));
	}
	
}
