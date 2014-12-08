package br.com.parakyestacionamento.dominio;

import java.sql.SQLException;

import br.com.parakyestacionamento.modeloBD.ClientModelBD;

public class Adress {
	private int idAdress ;
	private int idClientAdress ;
	private String street ;
	private String number ;
	private String complement ;
	private String neighborhood;
	private String city ;
	private String country;
	private String clientName;
	
	
	
	
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
	
	public String getClientName() {
		//Shame of this piece of code.I ll improve this later.....swear!
		if(clientName == null)
			if(idClientAdress != 0){
				ClientModelBD model = new ClientModelBD();
				Client client;
				try {
					client = model.select(idClientAdress);
					clientName = client.getName()+" "+client.getLastName();
				} catch (SQLException e) {
					System.out.println("Erro ao buscar pelo cliente do endereco: "+e.getMessage());
					System.out.println(e);
				}
				
				
				
			}
			
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
}
