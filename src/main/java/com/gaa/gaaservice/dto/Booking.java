package com.gaa.gaaservice.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import utils.LocalDateAdapter;

@XmlRootElement
public class Booking {

	private String id;
	private String team;
	
	
	private LocalDate datetime;
	
	private String  pitch;
	
	public Booking() {
		
	}
	public Booking(String id, String team, LocalDate datetime, String pitch) {
		super();
		this.id = id;
		this.team = team;
		this.datetime = datetime;
		this.pitch = pitch;
	}
	
	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}
	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	/**
	 * @return the time and date
	 */	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDate() {
		return datetime;
	}
	/**
	 * @param  the time and date to set
	 */
	public void setDate(LocalDate date ) {
		this.datetime= date;
	}
	@Override
	public String toString() {
		return "Booking [ID"+id+" Team=" +team+ ", date=" + datetime + "]";
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the pitch
	 */
	public String getPitch() {
		return pitch;
	}
	/**
	 * @param pitch the pitch to set
	 */
	public void setPitch(String pitch) {
		this.pitch = pitch;
	}
	
	
}
