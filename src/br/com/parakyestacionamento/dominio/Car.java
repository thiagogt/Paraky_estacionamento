package br.com.parakyestacionamento.dominio;

public class Car {
	private int idCar ;
	private int idClientCar ;
	private int yearManufacture ;
	private String color ;
	private String model ;
	private String carPlate ; 
	private String carBrand ;
	
	public Car(String newCarPlate){
		this.carPlate = newCarPlate;
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public int getIdClientCar() {
		return idClientCar;
	}

	public void setIdClientCar(int idClientCar) {
		this.idClientCar = idClientCar;
	}

	public int getYearManufacture() {
		return yearManufacture;
	}

	public void setYearManufacture(int yearManufacture) {
		this.yearManufacture = yearManufacture;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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
	
	
}
