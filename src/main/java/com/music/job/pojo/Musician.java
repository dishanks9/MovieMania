package com.music.job.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "JOBSEEKER")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Musician extends User {
	
	@Column(name="CONTACTNUMBER")
	private String contactNumber;
	
	@Column(name="RESUME")
	private String resume;
	
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	private Set<Applications> applications;
	
	@Column(name="YEARSOFEXPERIENCE")
	private String pastYearsExperience;
	
	@Column(name="INSTRUMENTSPECIALITY")
	private String instrumentSpeciality;

	@Column(name="PASTBAND")
	private String pastBand;
	
	@ManyToMany(mappedBy="musicians")
	private Set<Events> event;
	
	@ManyToMany
    @JoinTable (
       name="musician_recruitor_table",
       joinColumns = {@JoinColumn(name="musicianId", nullable = false, updatable = false)},
       inverseJoinColumns = {@JoinColumn(name="recruitorId" )}
    )
	private Set<MusicRecruitor> mrConnections;

	public Set<MusicRecruitor> getMrConnections() {
		return mrConnections;
	}

	public void setMrConnections(Set<MusicRecruitor> mrConnections) {
		this.mrConnections = mrConnections;
	}

	public Set<Events> getEvent() {
		return event;
	}

	public void setEvent(Set<Events> event) {
		this.event = event;
	}

	public String getPastBand() {
		return pastBand;
	}

	public void setPastBand(String pastBand) {
		this.pastBand = pastBand;
	}

	public String getInstrumentSpeciality() {
		return instrumentSpeciality;
	}

	public void setInstrumentSpeciality(String instrumentSpeciality) {
		this.instrumentSpeciality = instrumentSpeciality;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Set<Applications> getApplications() {
		return applications;
	}

	public void setApplications(Set<Applications> applications) {
		this.applications = applications;
	}

	public String getPastYearsExperience() {
		return pastYearsExperience;
	}

	public void setPastYearsExperience(String pastYearsExperience) {
		this.pastYearsExperience = pastYearsExperience;
	}

	
}
