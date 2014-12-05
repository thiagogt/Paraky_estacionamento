package br.com.parakyestacionamento.domain;

import java.text.SimpleDateFormat;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.DailyPayment;

public class TicketParaky {
	private int idDailyParking;
	private String checkin;
	private String checkout;
	private String carPlate;
	private String carBrand;
	private String model;
	private double cost;
	
	public TicketParaky(){
		
	}
	public TicketParaky(Car car,DailyPayment daily){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		idDailyParking = daily.getIdDailyPayment();
		checkin = sdf.format(daily.getCheckin());
		checkout = sdf.format(daily.getCheckout());
		cost = daily.getCost();
		carPlate = car.getCarPlate();
		carBrand = car.getCarBrand();
		model = car.getModel();
	}
	
	public int getIdDailyParking() {
		return idDailyParking;
	}
	public void setIdDailyParking(int idDailyParking) {
		this.idDailyParking = idDailyParking;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	
}
