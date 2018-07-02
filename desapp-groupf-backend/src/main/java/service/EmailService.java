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
        msgHelper.setText(buildHtml("Bienvenido a CarPnD " + user.getName()));
        msgHelper.setSubject("Mensaje de bienvenida - CarPnD");
        sender.send(msg);
	}
	
public static String buildHtml(String text){
		
		String html1 =
				
				"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>"
		 +		"<html xmlns='http://www.w3.org/1999/xhtml'>"
		 +    	"<head>"
		 +			"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"
		 +			"<meta name='viewport' content='width=device-width, initial-scale=1.0'/>"
		 +			"<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>"
		 +		"</head>"
		 +		"<body>"
		 + "	   		<div padding-top: 20px'> "
		 + "				<table align='center' border='1' cellpadding='0' cellspacing='0' width='600'>"
		 +"						<tr>"
		 +"						<td bgcolor='#59b6f8' style='text-align: center'>"
		 +"							<img src='https://firebasestorage.googleapis.com/v0/b/desapp-21296.appspot.com/o/assets%2FCarPnDLogo1.png?alt=media&token=482b64a6-6e95-4f68-a918-60295e1eb44f' alt='CarPnDmail' style='display: block;'>"
		 +"						</td>"
		 +"						</tr>"
		 +"						<tr>"
		 +"						<td bgcolor='#ffffff'>"
		 +							"<div>"
		 + 								text;
		

		 
		 String html2="							 </div>"
				 +"						</td>"
				 +"						</tr>"
				 +"						<tr>"
				 +"						<td bgcolor='#ee4c50'>"
				 +""
				 +"						</td>"
				 +"						</tr>"
				 +"					</table>"
				 + "				<br> "
				 + "			</div>  "
				 + "	 </body>"
				 +		"</html>";
		 
		 
		

		
		return html1 + html2;
		
	}

	public JavaMailSender getSender() {
		return sender;
	}

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}
	
	

}
