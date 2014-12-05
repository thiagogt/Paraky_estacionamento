package br.com.parakyestacionamento.dominio;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.parakyestacionamento.modeloBD.ClientModelBD;

public class ParkingSpace {

	private int idParkingSpace ;
	private int idClientOwner ;
	private int payDay ;
	private String typeParkingSpace ;
	private Date contractDate ;
	private double parkingSpaceCost;
	
	private String contractDateString;
	private String clientName;
	
	public ParkingSpace(int newIdClientOwner){
		this.idClientOwner = newIdClientOwner;
	}


	public int getIdParkingSpace() {
		return idParkingSpace;
	}


	public void setIdParkingSpace(int idParkingSpace) {
		this.idParkingSpace = idParkingSpace;
	}


	public int getIdClientOwner() {
		return idClientOwner;
	}


	public void setIdClientOwner(int idClientOwner) {
		this.idClientOwner = idClientOwner;
	}


	public int getPayDay() {
		return payDay;
	}


	public void setPayDay(int payDay) {
		this.payDay = payDay;
	}


	public String getTypeParkingSpace() {
		return typeParkingSpace;
	}


	public void setTypeParkingSpace(String typeParkingSpace) {
		this.typeParkingSpace = typeParkingSpace;
	}


	public Date getContractDate() {
		return contractDate;
	}


	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}


	public double getParkingSpaceCost() {
		return parkingSpaceCost;
	}


	public void setParkingSpaceCost(double parkingSpaceCost) {
		this.parkingSpaceCost = parkingSpaceCost;
	}


	public String getContractDateString() {
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		contractDateString = stf.format(contractDate);
		return contractDateString;
	}


	public void setContractDateString(String contractDateString) {
		this.contractDateString = contractDateString;
	}


	public String getClientName() {
		//Shame of this piece of code.I ll improve this later.....swear!
		if(clientName == null)
			if(idClientOwner != 0){
				ClientModelBD model = new ClientModelBD();
				Client client;
				try {
					client = model.select(idClientOwner);
					clientName = client.getName()+" "+client.getLastName();
				} catch (SQLException e) {
					System.out.println("Erro ao buscar pelo client da vaga: "+e.getMessage());
					System.out.println(e);
				}
				
				
				
			}
			
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
	
}
