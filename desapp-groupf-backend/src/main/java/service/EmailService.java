package service;

import java.io.Serializable;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import model.User;


public class EmailService implements Serializable {

	private static final long serialVersionUID = -862069650891231879L;
	
	private JavaMailSender sender;
	
	public void welcomeMsg(User user) throws MessagingException{
		MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true); 
        msgHelper.setTo(user.getEmail());
        msgHelper.setText("Bienvenido a CarPnD " + user.getName());
        msgHelper.setSubject("Mensaje de bienvenida - CarPnD");
        sender.send(msg);
	}

	public JavaMailSender getSender() {
		return sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}
	
	

}
