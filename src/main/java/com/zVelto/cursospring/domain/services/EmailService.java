package com.zVelto.cursospring.domain.services;

import org.springframework.mail.SimpleMailMessage;

import com.zVelto.cursospring.domain.Cliente;
import com.zVelto.cursospring.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}