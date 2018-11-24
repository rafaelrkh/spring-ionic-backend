package com.rafael.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.repositories.ClienteRepository;
import com.rafael.cursomc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private ClienteRepository repCliente;

	@Override
	public UserDetails loadUserByUsername(String dsEmail) throws UsernameNotFoundException {
		Clientes cli = repCliente.findByDsEmail(dsEmail);
		
		if(cli == null) {
			throw new UsernameNotFoundException(dsEmail);
		}
		
		
		return new UserSS(cli.getCdCliente(), cli.getDsEmail(), cli.getDsSenha(), cli.getPerfis());
	}

}
