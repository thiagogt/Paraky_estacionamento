package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.ParkingSpace;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class ParkingSpaceModelBD implements BDModel{

	@Override
	public ParkingSpace bdToObject(ResultSet rs) throws SQLException {
		ParkingSpace parking = new ParkingSpace(rs.getInt(1));
		parking.setIdClientOwner(rs.getInt(2));
		parking.setPayDay(rs.getInt(3));
		parking.setTypeParkingSpace(rs.getString(4));

		java.util.Date newDate = rs.getTimestamp(5);
		parking.setContractDate(newDate);
		
		parking.setParkingSpaceCost(rs.getDouble(6));
		return parking;
	}

	@Override
	public Object select(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Object data) throws SQLException {
		
		ParkingSpace parkingSpace = (ParkingSpace) data;		

		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("insert into parking_space (id_parking_space ,id_client_owner,pay_day,type_parking_space,contract_date,parking_space_cost) values(" +
				"parking_space_sequence.nextval,?,?,?,?,?)");
		
		stmt.setInt(1, parkingSpace.getIdClientOwner());
		stmt.setInt(2, parkingSpace.getPayDay());
		stmt.setString(3, parkingSpace.getTypeParkingSpace());
		
		stmt.setDate(4, new java.sql.Date(parkingSpace.getContractDate().getTime()));
		stmt.setDouble(5, parkingSpace.getParkingSpaceCost());
		
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
	public List<ParkingSpace> selectAll() throws SQLException {
		List<ParkingSpace> parkingSpaceList;		

		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("select * from parking_space");
		
		ResultSet rs = stmt.executeQuery();
		
		parkingSpaceList = resultSetListToObjectList(rs);
		
		connection.close();

		return parkingSpaceList;
	}

	@Override
	public List<ParkingSpace> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		List<ParkingSpace> records= new ArrayList<ParkingSpace>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
	}

}
