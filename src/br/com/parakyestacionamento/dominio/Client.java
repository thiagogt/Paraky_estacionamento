package br.com.parakyestacionamento.dominio;

import java.util.Date;

public class Client {
	private int idClient ;
	private String name;
	private String lastName ;
	private String cpf ;
	private Date birthdayDate ;
	private String tel_1 ;
	private String tel_2 ;
	private String email ;
	private int idOwnerParkingSpace;
	
	public Client(String newCpf,int newIdTitular){
		this.cpf = newCpf;
		this.idOwnerParkingSpace = newIdTitular;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getTel_1() {
		return tel_1;
	}

	public void setTel_1(String tel_1) {
		this.tel_1 = tel_1;
	}

	public String getTel_2() {
		return tel_2;
	}

	public void setTel_2(String tel_2) {
		this.tel_2 = tel_2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdOwnerParkingSpace() {
		return idOwnerParkingSpace;
	}

	public void setIdOwnerParkingSpace(int idOwnerParkingSpace) {
		this.idOwnerParkingSpace = idOwnerParkingSpace;
	}

		
}
