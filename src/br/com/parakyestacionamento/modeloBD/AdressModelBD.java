package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.Adress;
import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class AdressModelBD implements BDModel{

	@Override
	public Adress bdToObject(ResultSet rs) throws SQLException {
		Adress adress = new Adress(rs.getInt(2));
		
		adress.setIdAdress(rs.getInt(1));
		adress.setStreet(rs.getString(3));
		adress.setNumber(rs.getString(4));
		adress.setComplement(rs.getString(5));
		adress.setNeighborhood(rs.getString(6));
		adress.setCity(rs.getString(7));
		adress.setCountry(rs.getString(8));
		
		return adress;
		
	}

	@Override
	public Object select(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Object data) throws SQLException {
		Adress adress = (Adress)data;
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("" +
				"INSERT INTO adress(id_adress,id_client_adress,street,number,complement,neighborhood,city,country) values" +
				"(adress_sequence.nextval,?,?,?,?,?,?,?)");
		stmt.setInt(1, adress.getIdClientAdress());
		stmt.setString(2, adress.getStreet());
		stmt.setString(3, adress.getNumber());
		stmt.setString(4, adress.getComplement());
		stmt.setString(5, adress.getNeighborhood());
		stmt.setString(6, adress.getCity());
		stmt.setString(7, adress.getCountry());
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
Adress adress = (Adress)data;
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("" +
				"update adress set id_client_adress=?,street=?,number=?,complement=?" +
				",neighborhood=?,city=?,country=? where id_adress=?");
		stmt.setInt(1, adress.getIdClientAdress());
		stmt.setString(2, adress.getStreet());
		stmt.setString(3, adress.getNumber());
		stmt.setString(4, adress.getComplement());
		stmt.setString(5, adress.getNeighborhood());
		stmt.setString(6, adress.getCity());
		stmt.setString(7, adress.getCountry());
		stmt.setInt(8, adress.getIdAdress());
		stmt.executeUpdate();
		
		connection.close();

		
		return 0;

	}

	@Override
	public List<Adress> selectAll() throws SQLException {

		List<Adress> adressList = null;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ADRESS");
		ResultSet rs = stmt.executeQuery();
		
		adressList = resultSetListToObjectList(rs);
		connection.close();

		
		return adressList;

	}

	@Override
	public List<Adress> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		List<Adress> records= new ArrayList<Adress>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
	}

	public List<Adress> selectAllAdressForThisClient(int idClientSelected) throws SQLException {

		List<Adress> adressList = null;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ADRESS WHERE ID_CLIENT_ADRESS="+idClientSelected);
		ResultSet rs = stmt.executeQuery();
		
		adressList = resultSetListToObjectList(rs);
		connection.close();

		
		return adressList;
	}

}
