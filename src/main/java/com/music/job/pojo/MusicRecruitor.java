package com.music.job.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "MUSICRECRUITOR")
@PrimaryKeyJoinColumn(name="USER_ID")
public class MusicRecruitor extends User {
	
	@Column(name="genre")
	private String genre;
	 
	@Column(name="ACHIEVEMENTS")
	private String achievements;
	
	@Column(name="MOBILENUMBER")
	private String mobileNumber;
	
	@OneToOne(mappedBy = "musicRecruitor", cascade = CascadeType.ALL)
	private MessageToAdmin message;
	
	@ManyToMany
    @JoinTable (
       name="musician_recruitor_table",
       joinColumns = {@JoinColumn(name="recruitorId", nullable = false, updatable = false)},
       inverseJoinColumns = {@JoinColumn(name="musicianId" )}
    )
	private Set<Musician> mConnections;
	
	public Set<Musician> getmConnections() {
		return mConnections;
	}

	public void setmConnections(Set<Musician> mConnections) {
		this.mConnections = mConnections;
	}

	public MessageToAdmin getMessage() {
		return message;
	}

	public void setMessage(MessageToAdmin message) {
		this.message = message;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
