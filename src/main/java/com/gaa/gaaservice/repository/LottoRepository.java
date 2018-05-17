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
import com.gaa.gaaservice.dto.Lotto;
import com.gaa.gaaservice.dto.Result;

public class LottoRepository {
	private Properties propObj = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gaa_club";
	
	public LottoRepository() {	
		
		propObj = new Properties();
		propObj.setProperty("user", "gaaWorker");
		propObj.setProperty("password", "password");
	}
	
	public List<Lotto> getLottos(){		
		
		
		List<Lotto> sqlLottos = new ArrayList<>();
		
		String sqlQuery = "SELECT `lotto`.`draw1`,`lotto`.`draw2`,`lotto`.`draw3`,`lotto`.`draw4`,`lotto`.`message`,`lotto`.`drawdate` FROM `gaa_club`.`lotto`;";
		
		try (Connection connection = DriverManager.getConnection(DB_URL,propObj);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sqlQuery);) {
			
			while (rs.next()) {
				Lotto result = new Lotto();
				int[] draw = new int[] {
						rs.getInt("draw1"),
						rs.getInt("draw2"),
						rs.getInt("draw3"),
						rs.getInt("draw4")
				};
				result.setDraw(draw);
				result.setMessage(rs.getString("message"));
				result.setDrawDate(rs.getTimestamp("drawdate").toLocalDateTime().toLocalDate());
				
				sqlLottos.add(result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return sqlLottos;
	}
}
