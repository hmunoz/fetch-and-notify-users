package com.fetch.users.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

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
	
	private LocalDate now = null;
	
	private final Function<LocalDate,Integer> getDateDifference = (date) -> Period.between(date, now).getDays();

	private final DateTimeFormatter dateformat =  DateTimeFormatter.ofPattern("dd-MM-uuuu");
	
	private StringBuffer message = new StringBuffer();
	
	private final String weeksDelimiter = "\n" + "#########################################################" + "\n";
	
	private final String usersDelimiter = "\n" + "*********************************************************" + "\n";
	
	private Func1<? super User, Boolean> userToBeFollowedUp = (user) -> {
		LocalDate scannedOrMetDay = user.isScanTaken() ? LocalDate.parse(user.getScannedDate(),dateformat) : LocalDate.parse(user.getMeetingDay(),dateformat);
		return getDateDifference.apply(scannedOrMetDay) % 7 == 0;

	};

	public void fetchNotifyUsers() throws InterruptedException, ExecutionException {

		List<User> usersFromFB = new ArrayList<>();
		now = LocalDate.now();
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

	}

	private void composeEmailMessage(
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
										+ Boolean.toString(u.isScanTaken())
										+ "\n"
										+ "Scan Taken Or Met Date:"
										+ (u.isScanTaken() ? u.getScannedDate()
												: u.getMeetingDay()) + "\n"
										+ "Comments:" + u.getComments()
										+ usersDelimiter);
							});
							message.append(weeksDelimiter);
						});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendEmail(Session session) {
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

	}
	 

}
