package com.fetch.users.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import com.fetch.users.domain.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

@Service
@EnableScheduling
public class FetchUsersService {

	private static final Logger log = LoggerFactory
			.getLogger(FetchUsersService.class);

	@Autowired
	Firebase ref;
	
	@Autowired
	Session session;

	private LocalDate now = null;
	
	private final Function<LocalDate,Integer> getDateDifference = (date) -> Period.between(date, now).getDays();

	private final DateTimeFormatter dateformat =  DateTimeFormatter.ofPattern("dd-MM-uuuu");
	
	private StringBuffer message = new StringBuffer();
	
	private final String weeksDelimiter = "\n" + "#########################################################" + "\n";
	
	private final String usersDelimiter = "\n" + "*********************************************************" + "\n";
	
	private Func1<? super User, Boolean> userToBeFollowedUp = (user) -> {
		LocalDate scannedOrMetDay = user.isScanTaken() ? LocalDate.parse(user.getScannedDate(),dateformat) : LocalDate.parse(user.getMeetingDay(),dateformat);
		System.out.println("Value for "+ user.getGeniusname()+ "-->"+getDateDifference.apply(scannedOrMetDay));
		return getDateDifference.apply(scannedOrMetDay) % 7 == 0;

	};

	@Scheduled(fixedDelay = 60000)
	public void fetchUsers() throws InterruptedException, ExecutionException {

		List<User> usersFromFB = new ArrayList<>();

		CountDownLatch waitForUsersLatch = new CountDownLatch(1);
		now = LocalDate.now();

		ref.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot postSnapshot : snapshot.getChildren()) {
					System.out.println("User--->"
							+ postSnapshot.getValue(User.class));
					usersFromFB.add(postSnapshot.getValue(User.class));
				}
				waitForUsersLatch.countDown();
			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {
				System.out.println("The read failed: "
						+ firebaseError.getMessage());
			}
		});

		waitForUsersLatch.await();
		getUsersAndSendEmail(usersFromFB);
	}

	private void getUsersAndSendEmail(List<User> users) {
		ConcurrentHashMap<Integer, CopyOnWriteArrayList<User>> userWeeklyFollowUpMap = new ConcurrentHashMap<>();
		Observable
				.from(users)
				.filter(userToBeFollowedUp)
				.scan(userWeeklyFollowUpMap, ((map, user) -> {
					try{
					LocalDate scannedOrMetDay = user.isScanTaken() ? LocalDate.parse(user.getScannedDate(),dateformat) : LocalDate.parse(user.getMeetingDay(),dateformat);
					
					AtomicInteger weekNumber = new AtomicInteger(getDateDifference.apply(scannedOrMetDay)/7);
					map.computeIfAbsent(weekNumber.get(), list-> new CopyOnWriteArrayList<User>())
                    .add(user);
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
						});

	}

	private void composeEmailMessage(
			ConcurrentHashMap<Integer, CopyOnWriteArrayList<User>> userWeeklyFollowUpMap) {
		try{
		userWeeklyFollowUpMap.forEach(4, (weekNumber, user) -> {
			message = message.append("Week " + weekNumber + " follow ups " + "\n");
			user.forEach(u -> {
				message.append("Name:" + u.getGeniusname() + "\n"
						+ "Scan Taken:" + Boolean.toString(u.isScanTaken()) + "\n"
						+ "Scan Taken Or Met Date:" + (u.isScanTaken() ? u
						.getScannedDate() : u.getMeetingDay()) + "\n"
						+ "Comments:" + u.getComments()+usersDelimiter);
			});
			message.append(weeksDelimiter);
		});
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info(message.toString());
	}
	
	private void sendEmail(Session session) {
		try {
			log.info("Inside send email");
			Message emailMessage = new MimeMessage(session);
			emailMessage.setFrom(new InternetAddress(
					"sagarmeansocean@gmail.com"));
			emailMessage
					.setRecipients(
							Message.RecipientType.TO,
							InternetAddress
									.parse("anuraggupta86@gmail.com,sagarmeansocean@gmail.com"));
			emailMessage.setSubject("Test- Genius Weekly Info!");
			emailMessage.setText(message.toString());
			log.info("Reached.....");
			Transport transport = session.getTransport("smtp");
//			transport.connect();
			transport.connect("sagarmeansocean@gmail.com", "Dec#2011");
			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch(Exception e){
			e.printStackTrace();
		}

	}
	 

}
