package com.gaa.gaaservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.gaa.gaaservice.dto.Fixture;
import com.gaa.gaaservice.dto.Result;

public class ResultsRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public ResultsRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<Result> getResults(){		
		
		
		List<Result> sqlResults = new ArrayList<>();
		
		String sqlQuery = "SELECT `results`.`home`,`results`.`away`,`results`.`homeScore`,`results`.`awayScore`,`results`.`division`,`results`.`dateTime` FROM `gaa_club`.`results`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Result result = new Result();
				result.setHome(rs.getString("home"));
				result.setAway(rs.getString("away"));
				result.setHomeScore(rs.getString("homeScore"));
				result.setAwayScore(rs.getString("awayScore"));
				result.setDivision(rs.getString("division"));
				result.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
				
				sqlResults.add(result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlResults;
	}
	
	


}
