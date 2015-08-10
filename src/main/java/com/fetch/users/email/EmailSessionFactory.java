package com.fetch.users.email;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mail.properties")
public class EmailSessionFactory {

	@Value("${mail.smtp.host}")
	private String smtpHost;
	
	@Value("${mail.smtp.socketFactory.port}")
	private String smtpSocketFactoryPort;
	
	@Value("${mail.smtp.socketFactory.class}")
	private String smtpSocketFactoryClass;
	
	@Value("${mail.smtps.auth}")
	private String smtpsAuth;
	
	@Value("${mail.smtp.port}")
	private String smtpPort;
	
	@Value("${mail.debug}")
	private String setDebug;
	
	@Value("${emailId}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Bean
	Session emailSession() {
		
		Properties props = new Properties();
		
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.socketFactory.port", smtpSocketFactoryPort);
		props.put("mail.smtp.socketFactory.class",smtpSocketFactoryClass);
		props.put("mail.smtps.auth", smtpsAuth);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.debug", setDebug);

		Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		session.setDebug(true);
		return session;

	}
	

}
