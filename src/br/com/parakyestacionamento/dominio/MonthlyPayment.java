package br.com.parakyestacionamento.dominio;

import java.util.Date;

public class MonthlyPayment {
	private int idMonthlyPayment ;
	private int idParkingSpace ;
	private String paymentStatus ;
	private Date paymentDate ;
	
	public MonthlyPayment(int newIdParkingSpace){
		this.idParkingSpace = newIdParkingSpace;
	}

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
	
	
	
}
