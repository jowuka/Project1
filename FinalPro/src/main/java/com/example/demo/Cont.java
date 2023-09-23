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

	static String Categoria = "asciugatrice";
	static String ProductID;
	static int userID;
	static String UtilizzoOra;
	static String TableQuery = "SELECT * from "+Categoria+" Order by Marca;";
	static String usernamejs;
	private static String passjs;
	private static String email;
	private static String tel;
	static boolean x = false;
	static boolean table = false;
	static boolean passLogin2 = true;
	static double [] Result =  new double[8];
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
				return "tabella.html";
	}
	
    @GetMapping("/Logch")
    public String mostraDati(Model model) throws Exception{
    	Categoria = "asciugatrice";
        // Aggiungi i dati al modello
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "anil1996";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		stmt.execute("USE progettofinale;");
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
				 userID = rset.getInt("id");
			}
			InsertConsume();
			model.addAttribute("prod1", Result[0]);
			model.addAttribute("prod2", Result[1]);
			model.addAttribute("prod3", Result[2]);
			model.addAttribute("prod4", Result[3]);
			model.addAttribute("prod5", Result[4]);
			model.addAttribute("prod6", Result[5]);
			model.addAttribute("prod7", Result[6]);
			model.addAttribute("prod8", Result[7]);
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
		stmt.execute("USE progettofinale;");
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
			TableQuery = "SELECT * from "+Categoria+" Order by Marca;";
			System.out.println(Categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping("/IdProoo")
	public void IdPro(@RequestBody JSONObject valoreJS) {
		JsonID(valoreJS);
	}
	private static void JsonID(JSONObject Product) {
		try {
			ProductID = (String) Product.get("IDPRO");
			UtilizzoOra = (String) Product.get("Orario");
			System.out.println(ProductID);
			System.out.println(UtilizzoOra);
			System.out.println(Categoria);
			System.out.println("Abbiamo Cliccato bottone");
			CaricaProdotto(ProductID,UtilizzoOra);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void CaricaProdotto(String prodotto, String UtilizzoOra) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "anil1996";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		stmt.execute("USE progettofinale;");
		
		stmt.executeQuery("SELECT * FROM users where username = '" + usernamejs + "';");
		ResultSet rset = stmt.getResultSet();

		String userN = "";
			while(rset.next()) {
				userN = rset.getString("id");
		}
			
		switch (Categoria) {
		case"asciugatrice":
			PreparedStatement stm = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm.setInt(1, Integer.parseInt(ProductID));
			stm.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm.executeUpdate();
			PreparedStatement stmOre = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre.executeUpdate();
			break;
		case"boiler_elettrico":
			PreparedStatement stm1 = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm1.setInt(1, Integer.parseInt(ProductID));
			stm1.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm1.executeUpdate();
			PreparedStatement stmOre1 = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre1.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre1.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre1.executeUpdate();
			break;
		case"condizionatore":
			System.out.println("siamo qui");
			PreparedStatement stm2 = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm2.setInt(1, Integer.parseInt(ProductID));
			stm2.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm2.executeUpdate();
			PreparedStatement stmOre2 = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre2.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre2.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre2.executeUpdate();
			break;
		case"forni":
			PreparedStatement stm3 = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm3.setInt(1, Integer.parseInt(ProductID));
			stm3.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm3.executeUpdate();
			PreparedStatement stmOre3 = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre3.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre3.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre3.executeUpdate();
			break;
		case"frigorifero":
			PreparedStatement stm4 = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm4.setInt(1, Integer.parseInt(ProductID));
			stm4.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm4.executeUpdate();
			PreparedStatement stmOre4 = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre4.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre4.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre4.executeUpdate();
			break;
		case"illuminazione":
			PreparedStatement stm5 = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm5.setInt(1, Integer.parseInt(ProductID));
			stm5.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm5.executeUpdate();
			PreparedStatement stmOre5 = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre5.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre5.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre5.executeUpdate();
			break;
		case"lavastoviglie":
			PreparedStatement stm6 = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm6.setInt(1, Integer.parseInt(ProductID));
			stm6.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm6.executeUpdate();
			PreparedStatement stmOre6 = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre6.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre6.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre6.executeUpdate();
			break;
		case"lavatrice":
			PreparedStatement stm7 = con.prepareStatement("UPDATE progettofinale.profilo SET ID_"+Categoria+" = ? WHERE (id_profilo = ?);");
			stm7.setInt(1, Integer.parseInt(ProductID));
			stm7.setInt(2, Integer.parseInt(FinalProApplication.profiloID));
			stm7.executeUpdate();
			PreparedStatement stmOre7 = con.prepareStatement("UPDATE progettofinale.ore SET ore_"+Categoria+" = ? WHERE (id_consumo = ?);");
			stmOre7.setInt(1, Integer.parseInt(UtilizzoOra));
			stmOre7.setInt(2, Integer.parseInt(FinalProApplication.OreID));
			stmOre7.executeUpdate();
			break;
		}
	}
	public static void InsertConsume() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "anil1996";
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		stmt.execute("USE progettofinale;");
		stmt.executeQuery("select \r\n"
				+ "users.username as 'USERNAME UTENTE',\r\n"
				+ "users.email as 'EMAIL UTENTE',\r\n"
				+ "asciugatrice.consumo as 'CASCIUGATRICE',\r\n"
				+ "ore.ore_asciugatrice as 'OASCIUGATRICE',\r\n"
				+ "boiler_elettrico.consumo as 'Cboiler_elettrico',\r\n"
				+ "ore.ore_boiler_elettrico as 'Oboiler_elettrico',\r\n"
				+ "condizionatore.consumo as 'CCONDIZIONATORE',\r\n"
				+ "ore.ore_condizionatore as 'Ocondizionatore',\r\n"
				+ "forni.consumo as 'Cforno',\r\n"
				+ "ore.ore_forni as 'Oforno',\r\n"
				+ "frigorifero.consumo as 'Cfrigorifero',\r\n"
				+ "ore.ore_frigorifero as 'Ofrigorifero',\r\n"
				+ "illuminazione.consumo as 'Cilluminazione',\r\n"
				+ "ore.ore_illuminazione as 'Oilluminazione',\r\n"
				+ "lavastoviglie.consumo as 'Clavastoviglie',\r\n"
				+ "ore.ore_lavastoviglie as 'Olavastoviglie',\r\n"
				+ "lavatrice.consumo as 'Clavatrice',\r\n"
				+ "ore.ore_lavatrice as 'Olavatrice'\r\n"
				+ "from ore\r\n"
				+ "left join profilo\r\n"
				+ "on ore.id_profilo = profilo.id_profilo\r\n"
				+ "left join users\r\n"
				+ "on profilo.id_utente = users.id\r\n"
				+ "left join asciugatrice\r\n"
				+ "on profilo.id_asciugatrice = asciugatrice.id\r\n"
				+ "left join boiler_elettrico\r\n"
				+ "on profilo.id_boiler_elettrico = boiler_elettrico.id\r\n"
				+ "left join condizionatore\r\n"
				+ "on profilo.id_condizionatore = condizionatore.id\r\n"
				+ "left join forni\r\n"
				+ "on profilo.id_forni = forni.id\r\n"
				+ "left join frigorifero\r\n"
				+ "on profilo.id_frigorifero = frigorifero.id\r\n"
				+ "left join illuminazione\r\n"
				+ "on profilo.id_illuminazione = illuminazione.id\r\n"
				+ "left join lavastoviglie\r\n"
				+ "on profilo.id_lavastoviglie = lavastoviglie.id\r\n"
				+ "left join lavatrice\r\n"
				+ "on profilo.id_lavatrice = lavatrice.id\r\n"
				+ "where id_consumo = "+ Integer.parseInt(FinalProApplication.OreID) + ";");
		ResultSet rset = stmt.getResultSet();

		double asciugatrice = 0;
		double boiler_elettrico = 0;
		double condizionatore = 0;
		double forni = 0;
		double frigorifero = 0;
		double illuminazione = 0;
		double lavastoviglie = 0;
		double lavatrice = 0;
		int OREasciugatrice = 0;
		int OREboiler_elettrico = 0;
		int OREcondizionatore = 0;
		int OREforni = 0;
		int OREfrigorifero = 0;
		int OREilluminazione = 0;
		int ORElavastoviglie = 0;
		int ORElavatrice = 0;
		
			while(rset.next()) {
				
				asciugatrice = rset.getDouble("CASCIUGATRICE");
				boiler_elettrico = rset.getDouble("Cboiler_elettrico");
				condizionatore = rset.getDouble("CCONDIZIONATORE");
				forni = rset.getDouble("Cforno");
				frigorifero = rset.getDouble("Cfrigorifero");
				illuminazione = rset.getDouble("Cilluminazione");
				lavastoviglie = rset.getDouble("Clavastoviglie");
				lavatrice = rset.getDouble("Clavatrice");
				
				OREasciugatrice = rset.getInt("OASCIUGATRICE");
				OREboiler_elettrico = rset.getInt("Oboiler_elettrico");
				OREcondizionatore = rset.getInt("Ocondizionatore");
				OREforni = rset.getInt("Oforno");
				OREfrigorifero = rset.getInt("Ofrigorifero");
				OREilluminazione = rset.getInt("Oilluminazione");
				ORElavastoviglie = rset.getInt("Olavastoviglie");
				ORElavatrice = rset.getInt("Olavatrice");	
		}
			Result[0] = asciugatrice * OREasciugatrice;
			Result[1] = boiler_elettrico * OREboiler_elettrico;
			Result[2] = condizionatore * OREcondizionatore;
			Result[3] = forni * OREforni;
			Result[4] = frigorifero * OREfrigorifero;
			Result[5] = illuminazione * OREilluminazione;
			Result[6] = lavastoviglie * ORElavastoviglie;
			Result[7] = lavatrice * ORElavatrice;
			
	}
}