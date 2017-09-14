package com.music.job.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGE")
public class MessageToAdmin {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	private String message;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="RECRUITOR_FK")
	private MusicRecruitor musicRecruitor;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MusicRecruitor getMusicRecruitor() {
		return musicRecruitor;
	}

	public void setMusicRecruitor(MusicRecruitor musicRecruitor) {
		this.musicRecruitor = musicRecruitor;
	}

	public long getId() {
		return id;
	}
	
}
