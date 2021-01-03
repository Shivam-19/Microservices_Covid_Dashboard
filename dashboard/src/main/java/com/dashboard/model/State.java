package com.dashboard.model;

public class State {
	
	private String stateCode;
	private String name;
	private int confirmed;
	
	public State(String stateCode, String name, int confirmed) {
		this.stateCode = stateCode;
		this.name = name;
		this.confirmed = confirmed;
	}

	public String getStateCode() {
		return stateCode;
	}
	
	public String getName() {
		return name;
	}
	
	public int getConfirmed() {
		return confirmed;
	}
	
	
	

}
