package com.nayan.booking.model;

public class AuthenticationResponse {
	
	private String jwt;
	private String userid;
	private String name;

	public AuthenticationResponse(String jwt, String userid, String name) {
		super();
		this.jwt = jwt;
		this.userid = userid;
		this.name = name;
		
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJwt() {
		return jwt;
	}

	public String getUserid() {
		return userid;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [jwt=" + jwt + ", userid=" + userid + ", name=" + name + "]";
	}



}
