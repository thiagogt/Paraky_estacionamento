package br.com.parakyestacionamento.dominio;

import java.util.Date;

public class MonthlyPaymentPerClient {

	private int idMonthlyPayment ;
	private int idParkingSpace ;
	private String paymentStatus ;
	private Date paymentDate ;
	private String name;
	private String lastName ;
	private String tel_1 ;
	private String tel_2 ;
	private String email ;
	private double parkingSpaceCost;
	private int daysOfDebt;
	
	
	public int getIdMonthlyPayment() {
		return idMonthlyPayment;
	}
	public void setIdMonthlyPayment(int idMonthlyPayment) {
		this.idMonthlyPayment = idMonthlyPayment;
	}
	public int getIdParkingSpace() {
		return idParkingSpace;
	}
	public void setIdParkingSpace(int idParkingSpace) {
		this.idParkingSpace = idParkingSpace;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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
	public double getParkingSpaceCost() {
		return parkingSpaceCost;
	}
	public void setParkingSpaceCost(double parkingSpaceCost) {
		this.parkingSpaceCost = parkingSpaceCost;
	}
	public int getDaysOfDebt() {
		return daysOfDebt;
	}
	public void setDaysOfDebt(int daysOfDebt) {
		this.daysOfDebt = daysOfDebt;
	}
	
	
	
	
	
}
