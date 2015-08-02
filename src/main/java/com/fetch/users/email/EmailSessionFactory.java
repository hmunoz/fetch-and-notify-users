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
	
	@Value("${mail.smtp.starttls.enable}")
	private String smtpStarttlsEnable;
	
	@Value("${mail.smtp.auth}")
	private boolean smtpAuth;
	
	@Value("${mail.smtp.port}")
	private int smtpPort;
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Bean
	Session emailSession() {
		
		Properties props = new Properties();
		/*props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.debug", "true");*/
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "false");

		Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		session.setDebug(true);
		return session;

	}
	

}
