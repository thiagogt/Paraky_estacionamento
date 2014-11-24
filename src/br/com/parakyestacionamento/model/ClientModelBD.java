package br.com.parakyestacionamento.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;
import br.com.parakyestacionamento.modeloBD.BDModel;

public class ClientModelBD implements BDModel{

	
	
	
	@Override
	public Client bdToObject(ResultSet rs) throws SQLException {
		String newCpf = rs.getString(4);
		int newIdTitular = rs.getInt(9);
		Client client = new Client(newCpf, newIdTitular);
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
		// TODO Auto-generated method stub
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
		rs.next();
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

}
