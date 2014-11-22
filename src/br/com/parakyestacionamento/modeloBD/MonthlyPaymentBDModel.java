package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class MonthlyPaymentBDModel implements BDModel{

	
	@Override
	public List<MonthlyPayment> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
			
		List<MonthlyPayment> records= new ArrayList<MonthlyPayment>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
	}
	
		
	public MonthlyPayment bdToObject(ResultSet rs) throws SQLException{
		
		int idParkingSpace = rs.getInt(2);
		
		MonthlyPayment monthlyPayment = new MonthlyPayment(idParkingSpace);
		monthlyPayment.setIdMonthlyPayment(rs.getInt(1));
		monthlyPayment.setPaymentStatus(rs.getString(3));
		java.util.Date newDate = rs.getTimestamp(4);
		monthlyPayment.setPaymentDate(newDate);
		
		
		return monthlyPayment;
	}

	@Override
	public String objectToBd() {
		return null;
	}

	@Override
	public List<MonthlyPayment> selectAll() throws SQLException{
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from monthly_payment").executeQuery();
		List<MonthlyPayment> monthlyList = resultSetListToObjectList(rs);
	    
		connection.close();
		
		return monthlyList;
	}
	
	@Override
	public MonthlyPayment select(int id) throws SQLException {
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from monthly_payment where id_monthly_payment ="+id).executeQuery();
		rs.next();
		MonthlyPayment monthlyList = bdToObject(rs);
	    
		connection.close();
		
		return monthlyList;
	}
	
	@Override
	public int insert(Object data) throws SQLException{
		//TODO
		MonthlyPayment monthlyPayment = (MonthlyPayment)data;
		return 0;
	}

	@Override
	public int delete(int data) throws SQLException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object data) throws SQLException{
		//TODO
		MonthlyPayment monthlyPayment = (MonthlyPayment)data;
		return 0;
	}

	
	


	
}
