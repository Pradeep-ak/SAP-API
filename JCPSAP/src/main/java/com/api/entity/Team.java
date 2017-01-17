package com.api.entity;

import java.util.Date;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(value = "team")
public class Team {

	@PrimaryKeyColumn(name = "id",ordinal = 1,type = PrimaryKeyType.PARTITIONED)
	private String id;
	
	@Column(value = "first_name")
	private String firstName;

    @Column(value = "last_name")
    private String lastName;
	
    @Column(value = "phone_nb")
    private String phoneNumber;

    @Column(value = "email")
    private String emailId;

    @Column(value = "location")
    private String location;
    
    @JsonFormat(pattern="MM/dd/yyyy")
    @Column(value = "change_date")
    private Date changeDate;
	
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

	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String print() {
		return String.format("Team Object id=%s, firstName=%s, lastName=%s, phoneNumber=%s, emailId=%s, location=%s",
				getId(),
				getFirstName(),
				getLastName(),
				getPhoneNumber(),
				getEmailId(),
				getLocation());

	}
}
