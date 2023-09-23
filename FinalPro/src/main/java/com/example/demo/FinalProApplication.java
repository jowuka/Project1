package com.example.demo;

import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class FinalProApplication {
	static public boolean check = false;
	static String profiloID = "";
	static String OreID = "";
	public static void main(String[] args) {
		SpringApplication.run(FinalProApplication.class, args);
	}

	public static void ConnectionDB(Utente user, int value) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/";
			String username = "root";
			String password = "anil1996";
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();
			stmt.execute("USE progettofinale;");

			String userN = "";
			String userPass = "";
			if (value == 1) {
				PreparedStatement stm = con
						.prepareStatement("INSERT INTO users(username, password, email, tel) VALUES(?,?,?,?);");
				stm.setString(1, user.username);
				stm.setString(2, user.email);
				stm.setString(3, user.password);
				stm.setString(4, user.tel);
				stm.executeUpdate();
				
				stmt.executeQuery("SELECT * FROM users where username = '" + user.username + "';");
				ResultSet rset = stmt.getResultSet();

				String userID = "";
				while(rset.next()) {
					userID = rset.getString("id");
				}

				PreparedStatement stm1 = con.prepareStatement("INSERT INTO profilo(id_utente) VALUES(?);");
				stm1.setInt(1, Integer.parseInt(userID));
				stm1.executeUpdate();
				
				
				Statement stmt5 = con.createStatement();
				stmt5.executeQuery("SELECT * FROM profilo where id_utente = " + userID + ";");
				ResultSet rset1 = stmt5.getResultSet();
				
				String userIDOre = "";
				while(rset1.next()) {
					userIDOre = rset1.getString("id_profilo");
				}

				 stm1 = con.prepareStatement("INSERT INTO ore(id_profilo) VALUES(?);");
				stm1.setInt(1, Integer.parseInt(userIDOre));
				stm1.executeUpdate();
				
				
				check = true;
			} else if (value == 2) {
				PreparedStatement stm = con.prepareStatement("select * from users where users.username = ? and users.password = ?");
				stm.setString(1, user.username);
				stm.setString(2, user.password);
				stm.executeQuery();
				String userID = "";
				ResultSet rset = stm.getResultSet();
				while(rset.next()) {
					 userN = rset.getString("username");
					 userPass = rset.getString("password");
					 userID = rset.getString("id");
				}
				Statement stmt5 = con.createStatement();
				stmt5.executeQuery("SELECT * FROM profilo where id_utente = " + userID + ";");
				ResultSet rset1 = stmt5.getResultSet();

				while(rset1.next()) {
					profiloID = rset1.getString("id_profilo");
				}
				
				Statement stmt6 = con.createStatement();
				stmt5.executeQuery("SELECT * FROM ore where id_profilo = " + profiloID + ";");
				ResultSet rset2 = stmt5.getResultSet();

				while(rset2.next()) {
					OreID = rset2.getString("id_consumo");
				}
				
				System.out.println(profiloID);
				if (user.username.equals(userN) && user.password.equals(userPass)) {
					check = true;
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
