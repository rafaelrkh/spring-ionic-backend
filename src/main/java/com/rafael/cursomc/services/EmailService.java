package com.rafael.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.rafael.cursomc.domain.Clientes;
import com.rafael.cursomc.domain.Pedidos;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedidos obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedidos obj);
	
	void sendHtmlEmail(MimeMessage ms);

	void sendNewPasswordEmail(Clientes cliente, String newPass);
}
