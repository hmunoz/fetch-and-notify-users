package com.fetch.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fetch.users.service.FetchNotifyUsersService;
import com.firebase.client.Firebase;

@Configuration
@PropertySource("classpath:firebase.properties")
public class Config {
	
	@Value("${firebase.ref.url}")
	private String firebaseRefUrl;
	
	@Bean
	Firebase firebase(){
		return new Firebase(firebaseRefUrl);
	}
	
	@Bean
	FetchNotifyUsersService fetchNotifyUsersService(){
		return new FetchNotifyUsersService();
	}
	
	
}
