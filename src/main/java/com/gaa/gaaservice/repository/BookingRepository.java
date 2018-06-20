package com.gaa.gaaservice.repository;

import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
		
		String sqlQuery = "SELECT `bookings`.`id`,`bookings`.`team`,`bookings`.`datetime`,`bookings`.`pitch`,FROM `gaa_club`.`bookings`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getString("id"));
				booking.setTeam(rs.getString("team"));
				booking.setDate(rs.getDate("date").toLocalDate());
				booking.setPitch(rs.getString("pitch"));
				sqlBookings.add(booking);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlBookings;
	}
	
	public Booking addBooking(Booking booking){
		
		System.out.println(booking.toString());
		String sqlQuery = "INSERT INTO `gaa_club`.`bookings` (`id`,`datetime`, `team`, `pitch`) VALUES (?,?,?,?)";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				PreparedStatement ps = connection.prepareStatement(sqlQuery);) {
			
			
			//java.sql.Date sqlDate = java.sql.Date.valueOf(booking.getdate());
			
			/*if(sqlDate == null)
			{
				System.out.println("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			}*/
			
			ps.setString(1, booking.getId());
			ps.setObject(2, booking.getDate());
			ps.setString(3, booking.getTeam());
			ps.setString(4, booking.getPitch());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return booking;
	}

	public Fixture getFicture(int id) {
		// TODO Auto-generated method stub
		return new Fixture("St Annes Park", "Raheny", "Trinity Gaels", "Dave Moore", "Adult Hurling League Division Seven "+id, LocalDateTime.of(2018, 5, 4, 7, 00, 00));
	}

}
