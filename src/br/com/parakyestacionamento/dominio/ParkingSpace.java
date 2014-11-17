package br.com.parakyestacionamento.dominio;

import java.util.Date;

public class ParkingSpace {

	private int idParkingSpace ;
	private int idClientOwner ;
	private int payDay ;
	private String typeParkingSpace ;
	private Date contractDate ;
	private double parkingSpaceCost;
	
	
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
	
	
	
}
