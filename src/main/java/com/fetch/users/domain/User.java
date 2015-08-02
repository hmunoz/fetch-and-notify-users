package com.fetch.users.domain;

import lombok.Data;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	/*private String birthday;
	private String name;
	private int age;*/
	
	private String geniusid;
	private String geniusname;
	private boolean scanTaken;
	private String scannedDate;
	private String meetingDay;
	private String comments;

}
