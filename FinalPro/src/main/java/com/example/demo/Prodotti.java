package com.example.demo;

public class Prodotti {
	String Marca, Modello, Classe, Consumo, Id;

	public Prodotti(String marca, String modello, String classe, String consumo, String id) {
		super();
		Marca = marca;
		Modello = modello;
		Classe = classe;
		Consumo = consumo;
		Id = id;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModello() {
		return Modello;
	}

	public void setModello(String modello) {
		Modello = modello;
	}

	public String getClasse() {
		return Classe;
	}

	public void setClasse(String classe) {
		Classe = classe;
	}

	public String getConsumo() {
		return Consumo;
	}

	public void setConsumo(String consumo) {
		Consumo = consumo;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	
	
}
