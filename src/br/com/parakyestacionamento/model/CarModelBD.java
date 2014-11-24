package br.com.parakyestacionamento.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;
import br.com.parakyestacionamento.modeloBD.BDModel;

public class CarModelBD implements BDModel{


	
	@Override
	public Object bdToObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
				"year_manufacture ,color,model,car_plate,car_brand) values (NEXT VALUE FOR car_sequence,?,?,?,?,?,?)");
		stmt.setInt(1, car.getIdCar());
		stmt.setInt(2, car.getIdClientCar());
		stmt.setInt(3, car.getYearManufacture());
		stmt.setString(4, car.getColor());
		stmt.setString(5, car.getModel());
		stmt.setString(6, car.getCarPlate());
		stmt.setString(7, car.getCarBrand());
		stmt.executeQuery();
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
