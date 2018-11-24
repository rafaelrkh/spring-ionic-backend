package com.rafael.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.repositories.ClienteRepository;
import com.rafael.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String dsEmail) {
		
		Clientes cliente = clienteRepository.findByDsEmail(dsEmail);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado!");
		}
		
		String newPass = NewPassword();
		cliente.setDsSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String NewPassword() {
		char[]vet = new char[10];
		for(int i =0; i<10;i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		//https://unicode-table.com/pt/
		
		if(opt == 0) {// gera um digito
			return (char) (rand.nextInt(10) + 48);
		}else if(opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}else {// gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}		
		
	}
}
