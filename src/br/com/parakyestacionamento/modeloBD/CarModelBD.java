package br.com.parakyestacionamento.modeloBD;

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
	public Car select(int id) throws SQLException {
		Connection connection = null;
		List<Car> carList =null;
		Car car = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PreparedStatement stmt = connection.prepareStatement("select * from car where id_car="+id);
		ResultSet rs = stmt.executeQuery();
		carList = resultSetListToObjectList(rs);
		if(carList.size()>0)
			car = carList.get(0);
		connection.close();
		
		return car;
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
		if(isDailyCar(car))
			stmt.setObject(1, null);
		else{
			stmt.setInt(1, car.getIdClientCar());
		}
		stmt.setInt(2, car.getYearManufacture());
		stmt.setString(3, car.getColor());
		stmt.setString(4, car.getModel());
		stmt.setString(5, car.getCarPlate());
		stmt.setString(6, car.getCarBrand());
		stmt.execute();
		String lastIdInsertedQuery = "SELECT TOP 1 id_car FROM CAR ORDER BY id_car DESC";
		stmt = connection.prepareStatement(lastIdInsertedQuery);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int idCar = rs.getInt(1);
		
		connection.close();

		
		return idCar;
	}

	private boolean isDailyCar(Car car) {
		return (car.getIdClientCar()==0);
	}

	@Override
	public int delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object data) throws SQLException {
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		Car car = (Car)data;
		PreparedStatement stmt = connection.prepareStatement("update car set model=?, " +
				"year_manufacture =?,color=?,car_plate=?,car_brand=? where id_car = ?");
		stmt.setString(1, car.getModel());
		stmt.setInt(2, car.getYearManufacture());
		stmt.setString(3, car.getColor());
		stmt.setString(4, car.getCarPlate());
		stmt.setString(5, car.getCarBrand());
		stmt.setInt(6, car.getIdCar());
		stmt.executeUpdate();
		
		connection.close();

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

	
	public int verifyIfCarPlateExists(String plate) throws SQLException{
		
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
		
		if(carList.size()>0){
			for (Car car : carList) {
				return car.getIdCar();
			}
		}
		return 0;
	}
}
