package com.music.job.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="JOBS")
public class Jobs {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "JOBID", unique = true, nullable = false)
	private int jobId;
	
	public int getJobId() {
		return jobId;
	}

	@Column(name="JOBTITLE")
	private String jobTitle;
	
	@Column(name="JOBDESCRIPTION")
	private String jobDescription;
	
	@Column(name="JOBREQUIREMENTS")
	private String jobRequirements;
	
	@Column(name="SKILLSREQUIRED")
	private String skillsRequired;
	
	@Column(name="HOURLYSALARY")
	private String hourlySalary;
	
	@Column(name="ADDITIONALREQUIREMENT")
	private String additionalRequirement;
	
	@Column(name="MINIMUMHOURS")
	private String minimumHours;
	
	@Column(name="DURATION")
	private int duration;
	
	@Column(name="NOOFPOSITIONS")
	private int noOfPositions;
	
	@Column(name="LOCATION")
	private String location;
	
	@OneToOne
	@JoinColumn(name="MUSICRECRUITOR_FK")
	private User user;
	
	@Column(name="COMPANYNAME")
	private String companyName;
	
	@OneToMany(mappedBy="job",fetch=FetchType.EAGER)
	private Set<Applications> applications;
	
	public Set<Applications> getApplications() {
		return applications;
	}

	public void setApplications(Set<Applications> applications) {
		this.applications = applications;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobRequirements() {
		return jobRequirements;
	}

	public void setJobRequirements(String jobRequirements) {
		this.jobRequirements = jobRequirements;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public String getHourlySalary() {
		return hourlySalary;
	}

	public void setHourlySalary(String hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	public String getAdditionalRequirement() {
		return additionalRequirement;
	}

	public void setAdditionalRequirement(String additionalRequirement) {
		this.additionalRequirement = additionalRequirement;
	}

	public String getMinimumHours() {
		return minimumHours;
	}

	public void setMinimumHours(String minimumHours) {
		this.minimumHours = minimumHours;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getNoOfPositions() {
		return noOfPositions;
	}

	public void setNoOfPositions(int noOfPositions) {
		this.noOfPositions = noOfPositions;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getPostedBy() {
		return user;
	}

	public void setPostedBy(User postedBy) {
		this.user = postedBy;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
