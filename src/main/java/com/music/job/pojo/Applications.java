package com.music.job.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "APPLICATION")
public class Applications {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	public int getId() {
		return id;
	}

	@Column(name="JOBSTATUS")
	private String jobStatus;
	
	@ManyToOne
	@JoinColumn(name="USER_FK")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "JOB_ID", nullable = false)
	private Jobs job;
	
	@Column(name="DATEAPPLIED")
	private String dateApplied;

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Jobs getJob() {
		return job;
	}

	public void setJob(Jobs job) {
		this.job = job;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}
	
	
}
