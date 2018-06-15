package com.gaa.gaaservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.gaa.gaaservice.dto.Fixture;

public class FixtureRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public FixtureRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<Fixture> getFictures(){		
		
		
		List<Fixture> sqlFictures = new ArrayList<>();
		
		String sqlQuery = "SELECT `fixtures`.`venue`,`fixtures`.`home`,`fixtures`.`away`,`fixtures`.`ref`,`fixtures`.`group`,`fixtures`.`dateTime` FROM `gaa_club`.`fixtures`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Fixture fixture = new Fixture();
				fixture.setVenue(rs.getString("venue"));
				fixture.setHome(rs.getString("home"));
				fixture.setAway(rs.getString("away"));
				fixture.setRef(rs.getString("ref"));
				fixture.setGroup(rs.getString("group"));
				//fixture.setDateTime(rs.getDate("dateTime"));
				fixture.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
				
				sqlFictures.add(fixture);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlFictures;
	}
	
	public Fixture addFicture(Fixture fixture){
		
		String sqlQuery = "INSERT INTO `gaa_club`.`fixtures` (`venue`, `home`, `away`, `ref`, `group`, `dateTime`) VALUES (?,?,?,?,?,?)";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				PreparedStatement ps = connection.prepareStatement(sqlQuery);) {
			
			
			
			ps.setString(1, fixture.getVenue());
			ps.setString(2, fixture.getHome());
			ps.setString(3, fixture.getAway());
			ps.setString(4, fixture.getRef());
			ps.setString(5, fixture.getGroup());
			//ps.setDate(6, fixture.getDateTime().);
			
			ps.setTimestamp(6, Timestamp.valueOf(fixture.getDateTime()));
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return fixture;
	}

	public Fixture getFicture(int id) {
		// TODO Auto-generated method stub
		return new Fixture("St Annes Park", "Raheny", "Trinity Gaels", "Dave Moore", "Adult Hurling League Division Seven "+id, LocalDateTime.of(2018, 5, 4, 7, 00, 00));
	}

}
