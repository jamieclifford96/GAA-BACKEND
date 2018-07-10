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

public class PlayerRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public PlayerRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<Player> getPlayer(){		
		
		
		List<Player> sqlHolidays = new ArrayList<>();
		
		String sqlQuery = "SELECT `players`.`firstName`,`players`.`lastName`,`players`.`team` FROM `gaa_club`.`players`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Player player = new Player();
				player.setFirstname(rs.getString("firstName"));
				player.setLastname(rs.getString("lastName"));
				player.setTeam(rs.getString("team"));
				sqlHolidays.add(player);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlHolidays;
	}
	
public Player getPlayerByFullName(String fullname){		
	
	JSONObject obj = new JSONObject(fullname);
	
	String fullname1 = obj.getString("fullname");
	
	String[] parts = fullname1.split(" ");
	String first = parts[0];
	String last = parts[1];
	
	Player player = new Player();

		
		String sqlQuery = "SELECT `players`.`firstName`,`players`.`lastName`,`players`.`team` FROM `gaa_club`.`players` WHERE `players`.`firstName` =" + "\"" + first + "\""+  " AND `players`.`lastName` =" + "\"" + last+ "\"" + ";";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				
				player.setFirstname(rs.getString("firstName"));
				player.setLastname(rs.getString("lastName"));
				player.setTeam(rs.getString("team"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return player;
	}
public List<Player> getPlayerByTeam(String team){		

	JSONObject obj = new JSONObject(team);
	
	String team1 = obj.getString("team");
	
	
	List<Player> sqlPlayers = new ArrayList<>();
		
		String sqlQuery = "SELECT `players`.`firstName`,`players`.`lastName`,`players`.`team` FROM `gaa_club`.`players` WHERE `players`.`team` =" + "\"" + team1 + "\"" +";";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Player player = new Player();
				player.setFirstname(rs.getString("firstName"));
				player.setLastname(rs.getString("lastName"));
				player.setTeam(rs.getString("team"));
				sqlPlayers.add(player);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlPlayers;
	}
	
	public Player addPlayer(String player){
		
		System.out.println(player.toString());
		String sqlQuery = "INSERT INTO `gaa_club`.`players` (`firstName`,`lastName`, `team`) VALUES (?,?,?)";
		Player completePlayer = new Player();
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				PreparedStatement ps = connection.prepareStatement(sqlQuery);) {
			
			JSONObject obj = new JSONObject(player);
			
			String firstName = obj.getString("firstname");
			String lastName = obj.getString("lastname");
			String team = obj.getString("team");
			
			completePlayer.setFirstname(firstName);
			completePlayer.setLastname(lastName);
			completePlayer.setTeam(team);
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, team);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 		
		return completePlayer;
	}

}
