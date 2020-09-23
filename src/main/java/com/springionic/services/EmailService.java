package com.springionic.services;

import org.springframework.mail.SimpleMailMessage;

import com.springionic.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
