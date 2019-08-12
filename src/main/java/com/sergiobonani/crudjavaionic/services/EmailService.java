package com.sergiobonani.crudjavaionic.services;

import com.sergiobonani.crudjavaionic.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {
	void sendOrderConfirmation(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage msg);
}
