package com.music.job.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Admin extends User {
	
	@Column(name="MOBILENUMBER")
	private String mobileNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
	private Set<Events> events;

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}
	
	
}
