package com.gaa.gaaservice.dto;

import java.time.LocalDate;

public class Lotto {
	private String message;
	private int[] draw = new int[4];	
	private LocalDate drawDate;

	public LocalDate getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(LocalDate drawDate) {
		this.drawDate = drawDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int[] getDraw() {
		return draw;
	}
	

	public void setDraw(int[] draw) {
		this.draw = draw;
	}

}
