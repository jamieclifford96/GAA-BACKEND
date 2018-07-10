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
import com.gaa.gaaservice.dto.Holiday;
import com.gaa.gaaservice.dto.Player;

public class HolidayRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public HolidayRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<Holiday> getHoliday(){		
		
		
		List<Holiday> sqlHolidays = new ArrayList<>();
		
		String sqlQuery = "SELECT `holidays`.`playerName`,`holidays`.`team`,`holidays`.`startDate`,`holidays`.`endDate` FROM `gaa_club`.`holidays`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Player player = new Player();
				Holiday holiday = new Holiday();
				player.FullName(rs.getString("playerName"));
				player.setTeam(rs.getString("team"));
				holiday.setPlayer(player);
				holiday.setStartDate(rs.getString("startDate"));
				holiday.setEndDate(rs.getString("endDate"));
				sqlHolidays.add(holiday);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlHolidays;
	}
public Holiday addHoliday(String holiday){
		
		System.out.println(holiday.toString());
		JSONObject obj = new JSONObject(holiday);
		JSONObject jsonChildObject = (JSONObject)obj.get("player");
	 
		
		String first = jsonChildObject.getString("firstname");
		String last =  jsonChildObject.getString("lastname");
		String team = jsonChildObject.getString("team");
		String startDate = obj.getString("startDate");
		String endDate = obj.getString("endDate");
				
		Player player = new Player();
		
		String playerName = first + " " + last;
		
		String playerQuery = "SELECT `players`.`firstName`,`players`.`lastName`,`players`.`team` FROM `gaa_club`.`players` WHERE `players`.`firstName` =" + first +" && `players`.`lastName` =" + last + ";";
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(playerQuery);) {
			
			while (rs.next()) {
				
				player.setFirstname(rs.getString("firstName"));
				player.setLastname(rs.getString("lastName"));
				player.setTeam(rs.getString("team"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		String sqlQuery = "INSERT INTO `gaa_club`.`holidays` (`playerName`,`team`, `startDate`, `endDate`) VALUES (?,?,?,?)";
		Holiday completeHoliday = new Holiday();
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				PreparedStatement ps = connection.prepareStatement(sqlQuery);) {
			
			completeHoliday.setPlayer(player);
			completeHoliday.setStartDate(startDate);
			completeHoliday.setEndDate(endDate);
			
			
			ps.setString(1, playerName);
			ps.setString(2, team);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 		
		return completeHoliday;
	}

}
