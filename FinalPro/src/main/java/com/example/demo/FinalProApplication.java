package com.example.demo;

import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class FinalProApplication {
	static public boolean check = false;

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
			stmt.execute("USE logincheck;");

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
			} else if (value == 2) {
				PreparedStatement stm = con.prepareStatement("select users.username, users.password from users where users.username = ? and users.password = ?");
				stm.setString(1, user.username);
				stm.setString(2, user.password);
				stm.executeQuery();
				ResultSet rset = stm.getResultSet();
				while(rset.next()) {
					 userN = rset.getString("username");
					 userPass = rset.getString("password");
				}
				if (user.username.equals(userN) && user.password.equals(userPass)) {
					check = true;
				}
			}	
			// stmt.executeUpdate("INSERT INTO register(username, password, email, data,
			// indirizzo, cap, city) VALUES ('"+user.username+"', '"+user.password+"','" +
			// user.email + "','" + user.datanascita + "','" + user.indirizzo+"','"
			// +user.cap + "','" + user.city + "');");
			/*
			 * con.close(); stmt.close(); rset.close();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
