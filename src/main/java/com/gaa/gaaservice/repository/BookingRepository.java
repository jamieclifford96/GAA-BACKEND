package com.gaa.gaaservice.repository;

import org.json.*;
import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.gaa.gaaservice.dto.Booking;
import com.gaa.gaaservice.dto.Fixture;

public class BookingRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public BookingRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<Booking> getBooking(){		
		
		
		List<Booking> sqlBookings = new ArrayList<>();
		
		String sqlQuery = "SELECT `bookings`.`id`,`bookings`.`team`,`bookings`.`datetime`,`bookings`.`pitch`,`bookings`.`time`  FROM `gaa_club`.`bookings`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getString("id"));
				booking.setTeam(rs.getString("team"));
				booking.setDatetime(rs.getString("datetime"));
				booking.setPitch(rs.getString("pitch"));
				booking.setTime(rs.getString("time"));
				sqlBookings.add(booking);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlBookings;
	}
	public List<Booking> getBookingsByDate(String json){
		List<Booking> sqlBookings = new ArrayList<>();
		

		JSONObject obj = new JSONObject(json);
		
		String sqldate = obj.getString("date");
		String pitch = obj.getString("pitch");
		String pitch1 = "\"" + pitch + "\""; 
		String sqldate1 = "\""+ sqldate +"\"";
		String sqlQuery = "SELECT `bookings`.`id`,`bookings`.`team`,`bookings`.`datetime`,`bookings`.`pitch`, `bookings`.`time` FROM `gaa_club`.`bookings` WHERE `bookings`.`datetime` ="+ sqldate1+ "&& `bookings`.`pitch` ="+ pitch1 +";";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getString("id"));
				booking.setTeam(rs.getString("team"));
				booking.setDatetime(rs.getString("datetime"));
				booking.setPitch(rs.getString("pitch"));
				booking.setTime(rs.getString("time"));
				sqlBookings.add(booking);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlBookings;
	}
	
	public Booking addBooking(String booking){
		
		System.out.println(booking.toString());
		String sqlQuery = "INSERT INTO `gaa_club`.`bookings` (`id`,`datetime`, `team`, `pitch`, `time`) VALUES (?,?,?,?,?)";
		Booking completeBooking = new Booking();
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				PreparedStatement ps = connection.prepareStatement(sqlQuery);) {
			
			JSONObject obj = new JSONObject(booking);
			
			String id = obj.getString("id");
			String datetime = obj.getString("datetime");
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String team = obj.getString("team");
			String pitch = obj.getString("pitch");
			String time = obj.getString("time");
			
			
			//java.sql.Date sqlDate = java.sql.Date.valueOf(datetime);
			
			/*if(sqlDate == null)
			{
				System.out.println("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			}*/
			completeBooking.setDatetime(datetime);
			completeBooking.setId(id);
			completeBooking.setTeam(team);
			completeBooking.setPitch(pitch);
			completeBooking.setTime(time);
			
			ps.setString(1, id);
			ps.setString(2, datetime);
			ps.setString(3, team);
			ps.setString(4, pitch);
			ps.setString(5, time);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 		
		return completeBooking;
	}

}
