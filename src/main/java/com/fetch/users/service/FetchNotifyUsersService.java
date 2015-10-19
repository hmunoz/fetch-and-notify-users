package com.fetch.users.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.users.domain.FranchiseOwner;
import com.fetch.users.domain.UserOld;
import com.fetch.users.repository.FranchiseRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import com.fetch.users.domain.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

@PropertySource("classpath:mail.properties")
public class FetchNotifyUsersService {

	private static final Logger log = LoggerFactory
			.getLogger(FetchNotifyUsersService.class);
	
	@Autowired
	Session session;
	
	@Value("${emailId}")
	private String emailId;
	
	@Value("${password}")
	private String password;
	
	@Value("${mail.receipients}")
	private String receipients;
	
	@Autowired
	Firebase ref;

	@Value("${firebase.ref.url}")
	private String firebaseRefUrl;

    /*@Autowired
    Client client;*/

    @Autowired
    ObjectMapper mapper;

	@Autowired
	FranchiseRepository repo;

	/*@Autowired
	RestTemplate restTemplate;*/

    private LocalDate now = null;
	
	private final Function<LocalDate,Integer> getDateDifference = (date) -> Period.between(date, now).getDays();

	private final DateTimeFormatter dateformat =  DateTimeFormatter.ofPattern("dd-MM-uuuu");
	
	private StringBuffer message = new StringBuffer();
	
	private final String weeksDelimiter = "\n" + "#########################################################" + "\n";
	
	private final String usersDelimiter = "\n" + "*********************************************************" + "\n";

	private String url = "http://localhost:9200/franchisees/anurag/customers/";

	Map<String, String> vars = new HashMap<String, String>();
	
	/*private Func1<? super User, Boolean> userToBeFollowedUp = (user) -> {
		LocalDate scannedOrMetDay = user.getScantakenyes() ? LocalDate.parse(user.getGeniusdos(),dateformat) : LocalDate.parse(user.getMetdate(),dateformat);
		return getDateDifference.apply(scannedOrMetDay) % 7 == 0;

	};*/

	public void scrubData(){
		Map<String,UserOld> usersFromFB = new HashMap<>();
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot postSnapshot : snapshot.getChildren()) {
					usersFromFB.put(postSnapshot.getKey(), postSnapshot.getValue(UserOld.class));
				}
				scrubData(usersFromFB);
			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {
				log.error("The read failed: "
						+ firebaseError.getMessage());
			}
		});

	}

	private void scrubData(Map<String,UserOld> usersFromFB){
		usersFromFB.keySet().stream().forEach(key->{
			String userUrl = firebaseRefUrl.concat(key);
			Firebase userRef = new Firebase(userUrl);
			userRef.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot snapshot) {
					for (DataSnapshot postSnapshot : snapshot.getChildren()) {
						UserOld oldUser = postSnapshot.getValue(UserOld.class);
						System.out.println("name--->"+oldUser.getGeniusname());

					}
				}

				@Override
				public void onCancelled(FirebaseError firebaseError) {
					log.error("The read failed: "
							+ firebaseError.getMessage());
				}
			});
		});

	}

	public void postDataToELK(){
        List<User> usersFromFB = new ArrayList<>();
        now = LocalDate.now();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot postSnapshot : snapshot.getChildren()) {
					usersFromFB.add(postSnapshot.getValue(User.class));
				}
				postDataToELK(usersFromFB);
			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {
				log.error("The read failed: "
						+ firebaseError.getMessage());
			}
		});
		
	}

    private void postDataToELK(List<User> usersFromFB) {
       /* usersFromFB.keySet().stream().forEach(key->{
			*//*url = url.concat(key);
			restTemplate.postForEntity(url,usersFromFB.get(key),User.class);*//*
            try {
                IndexResponse response = client.prepareIndex("franchises", "anurag",key)
                        .setSource(mapper.writeValueAsBytes(usersFromFB.get(key))).execute()
                        .actionGet();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
*/
//		System.out.println("USers>>"+usersFromFB);
		FranchiseOwner owner = new FranchiseOwner();
		owner.setId(1);
		owner.setName("Anurag");
		owner.setUsers(usersFromFB);
		System.out.println(repo.findOne(1));
		try {
			repo.save(owner);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
    }

    /*public void fetchNotifyUsers() throws InterruptedException, ExecutionException {
		List<User> usersFromFB = new ArrayList<>();
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot postSnapshot : snapshot.getChildren()) {
					usersFromFB.add(postSnapshot.getValue(User.class));
				}
				getUsersAndSendEmail(usersFromFB);
			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {
				log.error("The read failed: "
						+ firebaseError.getMessage());
			}
		});
		
	}*/

	/*private void getUsersAndSendEmail(List<User> users) {
		ConcurrentHashMap<Integer, CopyOnWriteArrayList<User>> userWeeklyFollowUpMap = new ConcurrentHashMap<>();
		Observable
				.from(users)
				.filter(userToBeFollowedUp)
				.scan(userWeeklyFollowUpMap, ((map, user) -> {
					try{
						LocalDate scannedOrMetDay = Boolean.valueOf(user.getScantakenyes()) ? LocalDate.parse(user.getGeniusdos(),dateformat) : LocalDate.parse(user.getMetdate(),dateformat);
						AtomicInteger weekNumber = new AtomicInteger(getDateDifference.apply(scannedOrMetDay)/7);
						map.computeIfAbsent(weekNumber.get(), list-> new CopyOnWriteArrayList<User>()).add(user);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					return map;
				}))
				.takeLast(1)
				.subscribeOn(Schedulers.computation())
				.subscribe(
						m -> {
							composeEmailMessage(m);
							sendEmail(session);
							log.info("Logging out the user......");
							ref.unauth();
						});

	}*/

	/*private void composeEmailMessage(
			ConcurrentHashMap<Integer, CopyOnWriteArrayList<User>> userWeeklyFollowUpMap) {
		try {
			if (userWeeklyFollowUpMap.isEmpty())
				message.append("A happy day today.. No follow ups!!");
			else {
				userWeeklyFollowUpMap.forEach(
						4,
						(weekNumber, user) -> {
							message = message.append("Week " + weekNumber
									+ " follow ups " + "\n");
							user.forEach(u -> {
								message.append("Name:"
										+ u.getGeniusname()
										+ "\n"
										+ "Scan Taken:"
										+ u.getScantakenyes()
										+ "\n"
										+ "Scan Taken Or Met Date:"
										+ (Boolean.valueOf(u.getScantakenyes()) ? u.getGeniusdos() : u.getMetdate()) + "\n"
										+ "Comments:" + u.getGeniuspreviousremarks()
										+ usersDelimiter);
							});
							message.append(weeksDelimiter);
						});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*private void sendEmail(Session session) {
		try {
			Message emailMessage = new MimeMessage(session);
			emailMessage.setFrom(new InternetAddress(emailId));
			emailMessage
					.setRecipients(
							Message.RecipientType.TO,
							InternetAddress
									.parse(receipients));
			emailMessage.setSubject("Test- Genius Weekly Info!");
			emailMessage.setText(message.toString());
			Transport transport = session.getTransport("smtp");
			transport.connect(emailId, password);
			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
			log.info("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch(Exception e){
			e.printStackTrace();
		}

	}*/
	 

}
