package com.dashboard.model;

public class District {
	
	private String id;
	private String name;
	private int confirmed;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	
	public District(String id, String name, int confirmed) {
		super();
		this.id = id;
		this.name = name;
		this.confirmed = confirmed;
	}
	public District() {
		// TODO Auto-generated constructor stub
	}
	
	

}
