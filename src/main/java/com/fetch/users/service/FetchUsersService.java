package com.fetch.users.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;




import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;




import com.fetch.users.domain.User;

@Service
@EnableScheduling
public class FetchUsersService {

	private static final Logger log = LoggerFactory
			.getLogger(FetchUsersService.class);

	@Autowired
	RestTemplate restTemplate;
	
	@Scheduled(fixedDelay = 3000)
	public void fetchUsers() throws InterruptedException, ExecutionException {
		
		Observable.OnSubscribe<User[]> subscribeFunction = (s) -> {
			Subscriber subscriber = (Subscriber) s;

			User[] users = restTemplate.getForObject(
					"https://amber-torch-3832.firebaseio.com/admins1.json",
					User[].class);

			log.info("User fetched inside create-->" + users);

			try {
				if (!subscriber.isUnsubscribed()) 
					subscriber.onNext(users);

				if (!subscriber.isUnsubscribed()) 
					subscriber.onCompleted();
			}
			catch(Exception e){
				subscriber.onError(e);
			}
		};
		
		Observable
				.create(subscribeFunction)
				.subscribeOn(Schedulers.io())
				.subscribe(
						(users) -> {
							System.out.println("incomingValue " + users);
							getUsersAndSendEmail(users);
						},
						(error) -> System.out.println("Something went wrong"
								+ ((Throwable) error).getMessage()),
						() -> System.out.println("This observable is finished"));

	}
	

	private void getUsersAndSendEmail(User[] user) {
		ConcurrentHashMap<String, CopyOnWriteArrayList<String>> emptyMap = new ConcurrentHashMap<String, CopyOnWriteArrayList<String>>();
		Observable.from(user).scan(emptyMap, ((map, u) -> {
			log.info("Inside getUsersHashMap");
			if (map.containsKey(u.getName()))
				map.get(u.getName()).add(u.getName());
			else {
				List<String> users = new CopyOnWriteArrayList<String>();
				users.add(u.getName());
				map.put(u.getName(), (CopyOnWriteArrayList<String>) users);
			}
			return map;
		})).takeLast(1).subscribeOn(Schedulers.computation()).subscribe(m -> {
			log.info("Final map--->"+m+" on thread--->"+Thread.currentThread());
			// TODO Add logic for sending e-mail..
		});
	}
	
	/*private void sendEmail(ConcurrentHashMap<String, CopyOnWriteArrayList<String>>){
		//TODO add email sending logic..
	}*/

}
