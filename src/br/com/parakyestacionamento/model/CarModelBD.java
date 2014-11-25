package br.com.parakyestacionamento.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;
import br.com.parakyestacionamento.modeloBD.BDModel;

public class CarModelBD implements BDModel{


	
	@Override
	public Car bdToObject(ResultSet rs) throws SQLException {
		
		Car car = new Car();
		car.setIdCar(rs.getInt(1));
		car.setIdClientCar(rs.getInt(2));
		car.setYearManufacture(rs.getInt(3));
		car.setColor(rs.getString(4));
		car.setModel(rs.getString(5));
		car.setCarPlate(rs.getString(6));
		car.setCarBrand(rs.getString(7));
		return car;
	}

	@Override
	public Object select(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Object data) throws SQLException {
		Car car = (Car)data;
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO car (id_car ,id_client_car," +
				"year_manufacture ,color,model,car_plate,car_brand) values (car_sequence.nextval,?,?,?,?,?,?)");
		stmt.setInt(1, car.getIdClientCar());
		stmt.setInt(2, car.getYearManufacture());
		stmt.setString(3, car.getColor());
		stmt.setString(4, car.getModel());
		stmt.setString(5, car.getCarPlate());
		stmt.setString(6, car.getCarBrand());
		stmt.execute();
		
		connection.close();

		
		return 0;
	}

	@Override
	public int delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object data) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Car> selectAll() throws SQLException {
		
		Connection connection = null;
		List<Car> carList =null;
		
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PreparedStatement stmt = connection.prepareStatement("select * from car");
		ResultSet rs = stmt.executeQuery();
		carList = resultSetListToObjectList(rs);
		connection.close();
		
		return carList;
	}

	@Override
	public List<Car> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		List<Car> records= new ArrayList<Car>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
	}

	
	public boolean verifyIfCarPlateExists(String plate) throws SQLException{
		
		Connection connection = null;
		List<Car> carList =null;
		
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PreparedStatement stmt = connection.prepareStatement("select * from car where car_plate='"+plate+"'");
		ResultSet rs = stmt.executeQuery();
		carList = resultSetListToObjectList(rs);
		connection.close();
		
		if(carList.size()>0)
			return true;
		
		return false;
	}
}
