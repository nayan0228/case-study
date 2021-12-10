package com.nayan.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Avaliability")
public class SeatAvaliability {
	@Id
	public String flightId;
	public int avaliable;
	public SeatAvaliability(String flightId, int avaliable) {
		super();
		this.flightId = flightId;
		this.avaliable = avaliable;
	}
	public String getFlightId() {
		return flightId;
	}
	public int getAvaliable() {
		return avaliable;
	}
	@Override
	public String toString() {
		return "SeatAvaliability [flightId=" + flightId + ", avaliable=" + avaliable + "]";
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public void setAvaliable(int avaliable) {
		this.avaliable = avaliable;
	}
	

}
