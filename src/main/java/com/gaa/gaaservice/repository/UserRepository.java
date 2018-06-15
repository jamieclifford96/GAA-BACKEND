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
import com.gaa.gaaservice.dto.User;

public class UserRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public UserRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<User> getUsers(){		
		
		
		List<User> sqlUsers = new ArrayList<>();
		
		String sqlQuery = "SELECT username,password FROM users;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
				while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				sqlUsers.add(user); 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlUsers;
	}
		public User addUser(User user){
		
		String sqlQuery = "INSERT INTO `gaa_club`.`users` (`username`,`password`) VALUES (?,?)";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				PreparedStatement ps = connection.prepareStatement(sqlQuery);) {
			
			
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}
	

}
