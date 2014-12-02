package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class ClientModelBD implements BDModel{

	
	
	
	@Override
	public Client bdToObject(ResultSet rs) throws SQLException {
		Client client = new Client();
		client.setCpf(rs.getString(4));
		client.setIdOwnerParkingSpace(rs.getInt(9));
		client.setIdClient(rs.getInt(1));
		client.setName(rs.getString(2));
		client.setLastName(rs.getString(3));
		
		java.util.Date newDate = rs.getTimestamp(5);
		client.setBirthdayDate(newDate);
		
		client.setTel_1(rs.getString(6));
		client.setTel_2(rs.getString(7));
		client.setEmail(rs.getString(8));
		return client;
	}

	@Override
	public Object select(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Object data) throws SQLException {
		
		Client client = (Client)data;
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO client (id_client," +
				"name,last_name,cpf,birthday_date,tel_1 ,tel_2 ,email,id_owner_parking_space ) values (client_sequence.nextval,?,?,?,?,?,?,?,?)");
		stmt.setString(1, client.getName());
		stmt.setString(2, client.getLastName());
		stmt.setString(3, client.getCpf());
		if(client.getBirthdayDate()!=null)
			stmt.setDate(4, new java.sql.Date(client.getBirthdayDate().getTime()));
		else
			stmt.setDate(4,null);
		stmt.setString(5, client.getTel_1());
		stmt.setString(6, client.getTel_2());
		stmt.setString(7, client.getEmail());
		if(isClientOwnerParkingSpace(client))
			stmt.setObject(8, null);
		else
			stmt.setInt(8, client.getIdOwnerParkingSpace());
		stmt.execute();
		
		
		connection.close();

		
		return 0;

	}

	private boolean isClientOwnerParkingSpace(Client client) {
		return (client.getIdOwnerParkingSpace() == 0);
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
	

		Client client = (Client)data;
		PreparedStatement stmt = connection.prepareStatement("update client set name=?, " +
				"last_name =?,cpf=?,birthday_date=?,tel_1=?,tel_2=?,email=? where id_client = ?");
		stmt.setString(1, client.getName());
		stmt.setString(2, client.getLastName());
		stmt.setString(3, client.getCpf());
		stmt.setDate(4, new java.sql.Date(client.getBirthdayDate().getTime()));
		stmt.setString(5, client.getTel_1());
		stmt.setString(6, client.getTel_2());
		stmt.setString(7, client.getEmail());
		stmt.setInt(8, client.getIdClient());
		stmt.executeUpdate();
		
		connection.close();

		return 0;
	}

	@Override
	public List<Client> selectAll() throws SQLException {
		List<Client> clientList;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from client").executeQuery();
		 clientList= resultSetListToObjectList(rs);
	    
		connection.close();
		
		return clientList;
	}
	
	public List<Client> selectAllOwners() throws SQLException {
		List<Client> clientList;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from client where id_owner_parking_space is null").executeQuery();
		 clientList= resultSetListToObjectList(rs);
	    
		connection.close();
		
		return clientList;
	}

	@Override
	public List<Client> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		List<Client> records= new ArrayList<Client>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
		
	}

	public boolean verifyIfClientCPFExists(String cpf) throws SQLException{
		
		Connection connection = null;
		List<Client> clientList =null;
		
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PreparedStatement stmt = connection.prepareStatement("select * from client where cpf='"+cpf+"'");
		ResultSet rs = stmt.executeQuery();
		clientList = resultSetListToObjectList(rs);
		connection.close();
		
		if(clientList.size()>0)
			return true;
		
		return false;

	}

	public List<Client> selectAllParent() throws SQLException{
		List<Client> clientList;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from client where id_owner_parking_space is not null").executeQuery();
		 clientList= resultSetListToObjectList(rs);
	    
		connection.close();
		
		return clientList;
	}

	public List<Client> selectAllParentFromThisClient(
			int idClientSelectedForFilter) throws SQLException {
		List<Client> clientList;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from client where id_owner_parking_space="+idClientSelectedForFilter).executeQuery();
		 clientList= resultSetListToObjectList(rs);
	    
		connection.close();
		
		return clientList;
	}

}
