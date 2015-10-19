package com.fetch.users.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fetch.users.service.FetchNotifyUsersService;
import com.firebase.client.Firebase;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

	/*@Bean
	Client client(){
		final Settings settings = ImmutableSettings.settingsBuilder()
				.put("node.name", "My beautiful node")
				.build();
		final Node node = new NodeBuilder()
				.settings(settings)
				.clusterName("my-cluster")
				.local(true)
				.client(true)
				.build().start();
		return node.client();
	}*/

	@Bean
	ObjectMapper objectMapper(){
		ObjectMapper mapper =  new ObjectMapper();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		mapper.setDateFormat(format);
		return mapper;
	}

	/*@Bean
	RestTemplate restTemplate(){
		RestTemplate rt = new RestTemplate();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		rt.getMessageConverters().add(new StringHttpMessageConverter());
		return rt;
	}*/
	
}
