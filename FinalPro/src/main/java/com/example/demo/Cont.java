package com.example.demo;

import java.lang.annotation.Target;
import java.util.List;

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
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public static ModelAndView method() {
	    return new ModelAndView("redirect:index");
	}
	@RequestMapping("/login")
	public static String login() {
		return "login.html";
	}
	@RequestMapping("/registrazione")
	public static String regist() {
		return "registrazione.html";
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
		System.out.println(valoreJS);
		if(FinalProApplication.check) 
			System.out.println("Login succesful");
		else
			System.out.println("Check Username & Pass");
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
