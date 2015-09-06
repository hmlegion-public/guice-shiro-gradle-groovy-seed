package com.ljstudio.utils;


import com.google.inject.servlet.RequestScoped;
import com.ljstudio.domain.User;

@RequestScoped
public class RequestItem {
	
	private long startTime;
	private String requestName;
	private String requestId;
	
	private User user;

	private String generateUniqueName () {
		return ( "" + Math.random() ).substring ( 2 );
	}

	public RequestItem() {
		requestId = generateUniqueName();
		this.requestName = "[" + requestId + "] " + "::";
		startTime = System.currentTimeMillis();	
	}

	public long getStartTime() {
		return startTime;
	}

	public String getRequestName() {
		return requestName;
	}

	public String getRequestId() {
		return requestId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
