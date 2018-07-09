package com.gaa.gaaservice.dto;

import com.gaa.gaaservice.dto.Player;

public class Holiday {

	private Player player;
	private String startDate;
	private String endDate;
	
	public Holiday(Player player, String startDate, String endDate)
	{
		this.player = player;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Holiday() {
		// TODO Auto-generated constructor stub
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}