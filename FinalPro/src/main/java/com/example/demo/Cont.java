package com.example.demo;

import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class Cont {

	static String Categoria = "boiler_elettrico";
	static String ProductID;
	static String TableQuery = "SELECT * from "+Categoria+";";
	private static String usernamejs;
	private static String passjs;
	private static String email;
	private static String tel;
	static boolean x = false;
	static boolean table = false;
	static boolean passLogin2 = true;
	static List<Utente> arr;
	@RequestMapping("/index")
	public static String index() {
		if (x)
			return "index2.html";
		else
			return "index.html";
	}
	
	@RequestMapping("/login")
	public static String login() {
		return "login.html";
	}
	@RequestMapping("/ForgotPW")
	public static String forgotPW() {
		return "ForgotPW.html";
	}
	@RequestMapping("/registrazione")
	public static String regist() {
		return "registrazione.html";
	}
	@RequestMapping("/indexx")
	public static String logoutIndex() {
		return "index.html";
	}
	
	/*@RequestMapping("/Logch")
	public static String Logch() throws Exception {
		 x = FinalProApplication.check;
		
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
	}*/
	@RequestMapping("/tabella")
	public static String index(Model model) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "anil1996";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		stmt.execute("use progettofinale;");
			ResultSet rset = stmt.executeQuery(TableQuery);
			String Marca;
			String Modello;
			String Classe;
			String Consumo;
			String Productid;
			List<Prodotti> arr = new ArrayList<>();
			while (rset.next()) {
				Marca = rset.getString("Marca");
				Modello = rset.getString("Modello");
				Classe = rset.getString("Classe");
				Consumo = rset.getString("Consumo");
				Productid = rset.getString("id");
				arr.add(new Prodotti(Marca,Modello,Classe,Consumo, Productid));
			} 
			model.addAttribute("Prodotto", arr);
			/*
	        List<Forno> listaForni = new ArrayList<>();
	        listaForni.add(new Forno("Forno 1", 200));
	        listaForni.add(new Forno("Forno 2", 250));
	        listaForni.add(new Forno("Forno 3", 180));

	        // Aggiungi la lista di forni al modello
	        model.addAttribute("forni", listaForni);
			 */
			

				return "tabella.html";
	}
	
	
    @GetMapping("/Logch")
    public String mostraDati(Model model) throws Exception{
        // Aggiungi i dati al modello

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "anil1996";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		stmt.execute("USE logincheck;");
		String userN = "";
		String userPass = "";
		String userTel = "";
		String userEmail = "";
		if (passLogin2) {
			PreparedStatement stm = con.prepareStatement("select * from users where users.username = ? and users.password = ?");
			stm.setString(1, usernamejs);
			stm.setString(2, passjs);
			stm.executeQuery();
			ResultSet rset = stm.getResultSet();
			while(rset.next()) {
				 userN = rset.getString("username");
				 userPass = rset.getString("password");
				 userTel = rset.getString("tel");
				 userEmail = rset.getString("email");
			}
			model.addAttribute("userName1", userN);
			model.addAttribute("userEmail1", userEmail);
			model.addAttribute("userTel1", userTel);
		    x = FinalProApplication.check;
			if (usernamejs.equals(userN) && passjs.equals(userPass) ) 
				x = true;
			else 
				x = false;
		}
		else {
				PreparedStatement stm = con.prepareStatement("select * from users where users.username = ?");
				stm.setString(1, usernamejs);
				stm.executeQuery();
				ResultSet rset = stm.getResultSet();
				while(rset.next()) {
					 userN = rset.getString("username");
					 userTel = rset.getString("tel");
					 userEmail = rset.getString("email");
				}
				model.addAttribute("userName1", userN);
				model.addAttribute("userEmail1", userEmail);
				model.addAttribute("userTel1", userTel);
			x = true;
		}
		if(x)
			return "AccountPage.html";
		else {
			return "login2.html";
		}
    }
    @GetMapping("/Logch2")
    public String Account2(Model model) throws Exception{
			return "AccountPage2.html";
    }
    
    @GetMapping("/Logchx")
    public String Logc(Model model) throws Exception{
        // Aggiungi i dati al modello

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "anil1996";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		stmt.execute("USE logincheck;");
		String userN = "";
		String userTel = "";
		String userEmail = "";
			PreparedStatement stm = con.prepareStatement("select * from users where users.username = ?");
			stm.setString(1, usernamejs);
			stm.executeQuery();
			ResultSet rset = stm.getResultSet();
			while(rset.next()) {
				 userN = rset.getString("username");
				 userTel = rset.getString("tel");
				 userEmail = rset.getString("email");
			}
			model.addAttribute("userName1", userN);
			model.addAttribute("userEmail1", userEmail);
			model.addAttribute("userTel1", userTel);
			System.out.println(userN + "   " + userTel +"   " + userEmail +" \n  "  + usernamejs +"   " + email +"   " + tel + "   ");
		    x = FinalProApplication.check;
			if (usernamejs.equals(userN) && email.equals(userEmail) )  {
				x = true;
				passLogin2 = false;
			}
			else 
				x = false;
		if(x)
			return "AccountPage.html";
		else {
			return "login.html";
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
		email = (String)employee.get("email");
		tel = (String)employee.get("tel");
		Utente user = new Utente(usernamejs, passjs, email,tel);
		FinalProApplication.ConnectionDB(user, 2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/jsForgot")
	public void jsForgot(@RequestBody JSONObject valoreJS) {
		JsonjsForgot(valoreJS);
		System.out.println(valoreJS);
		if(FinalProApplication.check) 
			System.out.println("Login succesful");
		else
			System.out.println("Check Username & Pass");
	}

	private static void JsonjsForgot(JSONObject employee) {
		try {
		usernamejs = (String)employee.get("Username");
		email = (String)employee.get("Email");
		Utente user = new Utente(usernamejs, "", email, "");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/Product")
	public void ProductEnt(@RequestBody JSONObject valoreJS) {
		JsonProduct(valoreJS);
	}

	private static void JsonProduct(JSONObject Product) {
		try {
			
			Categoria = (String) Product.get("Cat");
			TableQuery = "SELECT * from "+Categoria+";";
			System.out.println(Categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/IdProo")
	public void IdPro(@RequestBody JSONObject valoreJS) {
		JsonID(valoreJS);
	}

	private static void JsonID(JSONObject Product) {
		try {
			ProductID = (String) Product.get("IDPRO");
			System.out.println(ProductID);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}