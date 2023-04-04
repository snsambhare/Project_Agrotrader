package com.cdac.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject,String message,String to) {
		
		boolean  f=false;
		
		String from = "agrotraders.info@gmail.com";
		
		//Variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties "+properties);		
		
		//host set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object
		Session session = Session.getInstance(properties,new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("agrotraders.info@gmail.com","rxxqexpnzlydlopt");
			}
		});
		session.setDebug(true);
		
		//Step 2:Compose the message[text,image]
		MimeMessage m =  new MimeMessage(session);
		
		try {
			m.setFrom(to);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText("Dear Customer,\nThank you for registrating with us.\n\n"
					+message
					+ "\nFor further details/queries kindly contact us at agrotraders.info@gmail.com"
					+ "\n\nTeam,\nAgroTraders");
			
			
			//Step 3:send the message using transport class
			Transport.send(m);
			System.out.println("Sent successfully....");
			f=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}






