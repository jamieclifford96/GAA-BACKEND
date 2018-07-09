package com.gaa.gaaservice.dto;

public class Player {
	
	private String firstname;
	private String lastname;
	private String team;
	
	
	public Player(String firstname, String lastname, String team)
	{
		this.firstname= firstname;
		this.lastname = lastname;
		this.team = team;
	}


	public Player() {
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		this.team = team;
	}
	
	public void FullName(String fullname) {
		String[] parts = fullname.split(" ");
		String firstname = parts[0];
		String lastname = parts[1];
		
		this.firstname =firstname;
		this.lastname = lastname;
	}
	
	

}