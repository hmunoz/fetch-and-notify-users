package com.fetch.users.service;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Firebase.AuthResultHandler;
import com.firebase.client.FirebaseError;

@Service
@EnableScheduling
public class LoginService {
	
	@Autowired
	Firebase ref;

	@Autowired
	FetchNotifyUsersService fetchUsersNotfyService;
	
	@Value("${firebase.user.email}")
	private String email;
	
	@Value("${firebase.user.password}")
	private String fbPassword;
	
	private static final Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Scheduled(cron="0 20 18 * * *")
	public void loginService(){
		ref.authWithPassword(email, fbPassword, new AuthResultHandler() {
			
			@Override
			public void onAuthenticationError(FirebaseError arg0) {
				log.error("Invalid Credentials.... You aren't allowed to do this!");
			}
			
			@Override
			public void onAuthenticated(AuthData authData) {
				log.info("user logged.. FetchService should have been called");
				try {
					fetchUsersNotfyService.fetchNotifyUsers();
					log.info("Logging out the user......");
					ref.unauth();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
