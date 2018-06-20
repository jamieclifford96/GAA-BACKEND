package com.gaa.gaaservice.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Properties;

import com.gaa.gaaservice.dto.Fixture;
import com.gaa.gaaservice.dto.User;

public class UserRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public UserRepository() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	
	public User getUserByEmail(String emailAddress) {
		
		String sqlQuery = "SELECT `username`,`password` FROM `gaa_club`.`users` WHERE `username` = '"+ emailAddress +"';";
		User user = null;
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while(rs.next()) {
				user = new User(rs.getString("username"), rs.getString("password"));
			}
			
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
