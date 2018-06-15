package com.gaa.gaaservice.dto;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class Result {
	
	//@SerializedName("empID")
	private String division, home, away, homeScore, awayScore;	
	
	
	private LocalDateTime dateTime;
		
	
	
	public Result(String division, String home, String away, String homeScore, String awayScore
			,LocalDateTime dateTime) {
		this.division = division;
		this.home = home;
		this.away = away;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.dateTime = dateTime;
	}


	public Result() {
		// TODO Auto-generated constructor stub
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
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


	public String getHomeScore() {
		return homeScore;
	}


	public void setHomeScore(String homeScore) {
		this.homeScore = homeScore;
	}


	public String getAwayScore() {
		return awayScore;
	}


	public void setAwayScore(String awayScore) {
		this.awayScore = awayScore;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	@Override
	public String toString() {
		return "RSSResult [division=" + division + ", home=" + home + ", away=" + away + ", homeScore=" + homeScore
				+ ", awayScore=" + awayScore + ", dateTime=" + dateTime + "]";
	}
}
