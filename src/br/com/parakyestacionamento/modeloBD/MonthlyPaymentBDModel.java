package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.MonthlyPayment;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class MonthlyPaymentBDModel implements BDModel{

	

	public List<Object> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
			
		List<Object> records= new ArrayList<Object>();
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
	public List<Object> selectAll() throws SQLException{
		
		Connection connection = null;
			try {
				connection =	ConnectionDBFactory.getDataBaseConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResultSet rs = null;
			
			rs = connection.prepareStatement("select * from monthly_payment").executeQuery();
			List<Object> monthlyList = resultSetListToObjectList(rs);
		    
			connection.close();
		return monthlyList;
	}

	@Override
	public MonthlyPayment select(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int insert(String data) throws SQLException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String data) throws SQLException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String data) throws SQLException{
		// TODO Auto-generated method stub
		return 0;
	}


	


	
}
