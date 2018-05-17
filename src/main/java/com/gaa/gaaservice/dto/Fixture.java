package com.gaa.gaaservice.dto;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fixture {
	private String venue, home, away, ref, group;
	private LocalDateTime dateTime;
	
	public Fixture() {
		
	}	
	
	public Fixture(String venue, String home, String away, String ref, String group, LocalDateTime dateTime) {
		this.venue = venue;
		this.home = home;
		this.away = away;
		this.ref = ref;
		this.group = group;
		this.dateTime = dateTime;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getAway() {
		return away;
	}
	public void setAway(String away) {
		this.away = away;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Fixture [venue=" + venue + ", home=" + home + ", away=" + away + ", ref=" + ref + ", group=" + group
				+ ", dateTime=" + dateTime + "]";
	}
	
}
