package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class MonthlyPaymentPerClientModelBD implements BDModel{

	@Override
	public MonthlyPaymentPerClient bdToObject(ResultSet rs) throws SQLException {
		MonthlyPaymentPerClient monthlyPayment = new MonthlyPaymentPerClient();
		monthlyPayment.setName(rs.getString(1));
		monthlyPayment.setLastName(rs.getString(2));
		monthlyPayment.setTel_1(rs.getString(3));
		monthlyPayment.setTel_2(rs.getString(4));
		monthlyPayment.setEmail(rs.getString(5));
		monthlyPayment.setIdMonthlyPayment(rs.getInt(6));
		monthlyPayment.setIdParkingSpace(rs.getInt(7));
		monthlyPayment.setPaymentStatus(rs.getBoolean(8));
		monthlyPayment.setPayDay(rs.getInt(10));
		java.util.Date newDate = rs.getTimestamp(9);
		monthlyPayment.setPaymentDate(newDate);
		monthlyPayment.setParkingSpaceCost(rs.getDouble(11));
		
		
		return monthlyPayment;
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
	public List<? extends Object> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MonthlyPaymentPerClient> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		List<MonthlyPaymentPerClient> records= new ArrayList<MonthlyPaymentPerClient>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
	}

	public List<MonthlyPaymentPerClient> selectAllDebtPerClient() throws SQLException{
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("" +
				"select client.name, " +
				"client.last_name," +
				"client.tel_1," +
				"client.tel_2," +
				"client.email," +
				"parking_debt.id_monthly_payment ," +
				"parking_debt.id_parking_space ," +
				"parking_debt.payment_status," +
				"parking_debt.payment_date, " +
				"parking_debt.pay_day,"+
				"parking_debt.parking_space_cost " +
				"from client join " +
				"(select * from monthly_payment mp " +
				"natural join parking_space ps where payment_status=false) as parking_debt " +
				"on client.id_client=parking_debt.id_client_owner and client.id_owner_parking_space is null").executeQuery();
		List<MonthlyPaymentPerClient> monthlyList = resultSetListToObjectList(rs);
	    
		connection.close();
		
		return monthlyList;
	}

	public List<MonthlyPaymentPerClient> selectAllPayersClient() throws SQLException{
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("" +
				"select client.name, " +
				"client.last_name," +
				"client.tel_1," +
				"client.tel_2," +
				"client.email," +
				"parking_debt.id_monthly_payment ," +
				"parking_debt.id_parking_space ," +
				"parking_debt.payment_status," +
				"parking_debt.payment_date, " +
				"parking_debt.pay_day,"+
				"parking_debt.parking_space_cost " +
				"from client join " +
				"(select * from monthly_payment mp " +
				"natural join parking_space ps where payment_status=true) as parking_debt " +
				"on client.id_client=parking_debt.id_client_owner and client.id_owner_parking_space is null").executeQuery();
		List<MonthlyPaymentPerClient> monthlyList = resultSetListToObjectList(rs);
	    
		connection.close();
		
		return monthlyList;
	}
	
}
