package com.api.cassandra.bean;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "team")

public class Team {

	
    @Column(value = "first_name")

	private String firstName;

    @Column(value = "first_name")

    private String lastName;
	
    @Column(value = "ph_nb")

    private String phoneNumber;

    @PrimaryKeyColumn(name = "email",ordinal = 1,type = PrimaryKeyType.PARTITIONED)

    private String emailId;
    @Column(value = "location")

    private String location;
	
	public Team() {
		
	}
	
	public Team(String id, String firstName, String lastName,
			String phoneNumber, String emailId, String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.location = location;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
