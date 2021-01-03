package com.statedata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class State {
	
	public static final String JSON_VALUE_STATE_NAME = "state_name";
	public static final String JSON_VALUE_CONFIRMED = "new_positive";
	
	private String stateCode;
	private String name;
	private int confirmed;
	
	public State() {
		// TODO Auto-generated constructor stub
	}
	
	public State(@JsonProperty(JSON_VALUE_STATE_NAME) String name,
				 @JsonProperty(JSON_VALUE_CONFIRMED) int confirmed) {

		switch(name) {
		case "Uttarakhand":
			stateCode = "IN-UT";
			break;
		case "Odisha":
			stateCode = "IN-OR";
			break;
		case "Puducherry":
			stateCode = "IN-PY";
			break;
		case "Telengana":
			stateCode= "IN-TG";
			break;
		case "Chandigarh":
			stateCode ="IN-CH";
			break;
		case "Maharashtra":
			stateCode="IN-MH";
			break;
		case "Andaman and Nicobar Islands":
			stateCode="IN-AN";
			break;
		case "Andhra Pradesh":
			stateCode="IN-AP";
			break;
		case "Arunachal Pradesh":
			stateCode="IN-AR";
			break;
		case "Assam":
			stateCode="IN-AS";
			break;

		case "Bihar":
			stateCode="IN-BR";
			break;

		case "Chhattisgarh":
			stateCode="IN-CT";
			break;

		case "Delhi":
			stateCode="IN-DL";
			break;

		case "Goa":
			stateCode="IN-GA";
			break;

		case "Gujarat":
			stateCode="IN-GJ";
			break;

		case "Haryana":
			stateCode="IN-HR";
			break;

		case "Himachal Pradesh":
			stateCode="IN-HP";
			break;

		case "Jammu and Kashmir":
			stateCode="IN-JK";
			break;

		case "Jharkhand":
			stateCode="IN-JH";
			break;

		case "Karnataka":
			stateCode="IN-KA";
			break;

		case "Kerala":
			stateCode="IN-KL";
			break;

		case "Ladakh":
			stateCode="IN-LA";
			break;

		case "Madhya Pradesh":
			stateCode="IN-MP";
			break;

		case "Manipur":
			stateCode="IN-MN";
			break;

		case "Meghalaya":
			stateCode="IN-ML";
			break;

		case "Mizoram":
			stateCode="IN-MZ";
			break;

		case "Nagaland#":
			stateCode="IN-NL";
			name="Nagaland";
			break;

		case "Punjab":
			stateCode="IN-PB";
			break;

		case "Rajasthan":
			stateCode="IN-RJ";
			break;

		case "Tamil Nadu":
			stateCode="IN-TN";
			break;

		case "Tripura":
			stateCode="IN-TR";
			break;
		case "Uttar Pradesh":
			stateCode="IN-UP";
			break;
		case "West Bengal":
			stateCode="IN-WB";
			break;

		}
		this.name = name;
		this.confirmed = confirmed;
	}
	
	
	public String getStateCode() {
		return stateCode;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("confirmed")
	public int getConfirmed() {
		return confirmed;
	}
	
	
	

}
