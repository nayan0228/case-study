package com.nayan.booking.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Userdetails")
public class UserDetail {
	private String flightId;
	private String name;
	private String username;
	
	private String password;
	private int noOfPassengers;
	@Id
	private String bookingid;
	
	public UserDetail(String flightId, String name,String username, String password, int noOfPassengers, boolean b) {
		super();
		this.flightId = flightId;
		this.name = name;
		this.username=username;
		this.password = password;
		this.noOfPassengers = noOfPassengers;
		
	
	
	
	}
	public String getFlightId() {
		return flightId;
	}
	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	
	@Override
	public String toString() {
		return "UserDetils [flightId=" + flightId + ", name=" + name + " ,username ="+username+ ", password=" + password
				+ ", noOfPassengers=" + noOfPassengers + "]";
	}
	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
