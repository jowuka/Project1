package com.example.demo;

import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Cont {
	private static String usernamejs;
	private static String passjs;
	private static String email;
	private static String tel;
	static List<Utente> arr;
	@RequestMapping("/index")
	public static String index() {
		return "index.html";
		}
	
	@RequestMapping("/login")
	public static String login() {
		return "login.html";
	}
	@RequestMapping("/registrazione")
	public static String regist() {
		return "registrazione.html";
	}
	
	@RequestMapping("/Logch")
	public static String Logch() throws Exception {
		boolean x = FinalProApplication.check;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "anil1996";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		stmt.execute("USE logincheck;");
		String userN = "";
		String userPass = "";
			PreparedStatement stm = con.prepareStatement("select users.username, users.password from users where users.username = ? and users.password = ?");
			stm.setString(1, usernamejs);
			stm.setString(2, passjs);
			stm.executeQuery();
			ResultSet rset = stm.getResultSet();
			while(rset.next()) {
				 userN = rset.getString("username");
				 userPass = rset.getString("password");
			}
			if (usernamejs.equals(userN) && passjs.equals(userPass)) 
				x = true;
			else 
				x = false;
		System.out.println(x);
		if(x)
			return "AccountPage.html";
		else {
			return "login2.html";
		}
	}


	@PostMapping("/register")
	public void riceviValore(@RequestBody JSONObject valoreJS) {
		JsonTransfer(valoreJS);
		System.out.println(valoreJS);
		if(FinalProApplication.check) 
			System.out.println("Login succesful");
		else
			System.out.println("Check Username & Pass");
	}
	
	
	private static void JsonTransfer(JSONObject employee) {
		try {
		usernamejs = (String)employee.get("Username");
		passjs = (String)employee.get("Password");
		email = (String)employee.get("Email");
		tel = (String)employee.get("Tel");
		 Utente user = new Utente(usernamejs, passjs, email, tel);
		 FinalProApplication.ConnectionDB(user, 1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/logininfo")
	public void riceviLogin(@RequestBody JSONObject valoreJS) {
		JsonLogin(valoreJS);
	}
	
	
	private static void JsonLogin(JSONObject employee) {
		try {
		usernamejs = (String)employee.get("Username");
		passjs = (String)employee.get("Password");
		Utente user = new Utente(usernamejs, passjs, "","");
		FinalProApplication.ConnectionDB(user, 2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
