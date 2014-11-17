package br.com.parakyestacionamento.dominio;

public class Adress {
	private int idAdress ;
	private int idClientAdress ;
	private String street ;
	private String number ;
	private String complement ;
	private String neighborhood;
	private String city ;
	private String country;
	
	
	public Adress(int newIdClient){
		this.idClientAdress = newIdClient;
	}


	public int getIdAdress() {
		return idAdress;
	}


	public void setIdAdress(int idAdress) {
		this.idAdress = idAdress;
	}


	public int getIdClientAdress() {
		return idClientAdress;
	}


	public void setIdClientAdress(int idClientAdress) {
		this.idClientAdress = idClientAdress;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getComplement() {
		return complement;
	}


	public void setComplement(String complement) {
		this.complement = complement;
	}


	public String getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
