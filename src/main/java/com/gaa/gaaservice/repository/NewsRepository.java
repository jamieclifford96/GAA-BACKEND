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
import com.gaa.gaaservice.dto.News;
import com.gaa.gaaservice.dto.Result;

public class NewsRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public NewsRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<News> getNews(){		
		
		
		List<News> sqlNews = new ArrayList<>();
		
		String sqlQuery = "SELECT `news`.`title`,`news`.`newsMessage`,`news`.`dateTime` FROM `gaa_club`.`news`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				News result = new News();
				result.setTitle(rs.getString("title"));
				result.setNewsMessage(rs.getString("newsMessage"));
				result.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
				
				sqlNews.add(result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlNews;
	}
	
	


}
