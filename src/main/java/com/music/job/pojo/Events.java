package com.music.job.pojo;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EVENTS")
public class Events {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "EVENTID", unique = true, nullable = false)
	private int eventId;
	
	public int getEventId() {
		return eventId;
	}

	@Column(name="EVENTNAME")
	private String eventName;
	
	@Column(name="EVENTDETAILS")
	private String eventDetails;
	
	@Column(name="LOCATION")
	private String location;
	
	@ManyToMany
    @JoinTable (
       name="musician_event_table",
       joinColumns = {@JoinColumn(name="eventId", nullable = false, updatable = false)},
       inverseJoinColumns = {@JoinColumn(name="userId" )}
    )
	private Set<User> musicians;
	
	@ManyToOne
	@JoinColumn(name="ADMIN_FK")
	private User user;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<User> getMusicianList() {
		return musicians;
	}

	public void setMusicianList(Set<User> musicianList) {
		this.musicians = musicianList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
