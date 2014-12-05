package br.com.parakyestacionamento.dominio;

import java.util.Date;

public class DailyPayment {

	private int idDailyPayment;
	private int idCarCharged ;
	private double cost ;
	private Date checkin ;
	private Date checkout ;
	private boolean chargedPerHour;
	
	
	
	public boolean isChargedPerHour() {
		return chargedPerHour;
	}

	public String getChargedPerHourOutput() {
		if(chargedPerHour)
			return "Sim";
		return "Nao";
	}
	
	public void setChargedPerHour(boolean isChargedPerHour) {
		this.chargedPerHour = isChargedPerHour;
	}

	public DailyPayment(Date newCheckin){
		
		this.checkin = newCheckin;
	}

	public int getIdDailyPayment() {
		return idDailyPayment;
	}

	public void setIdDailyPayment(int idDailyPayment) {
		this.idDailyPayment = idDailyPayment;
	}

	public int getIdCarCharged() {
		return idCarCharged;
	}

	public void setIdCarCharged(int idCarCharged) {
		this.idCarCharged = idCarCharged;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	
	
	
}
