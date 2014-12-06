package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.parakyestacionamento.dominio.Client;
import br.com.parakyestacionamento.dominio.DailyPayment;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class DailyPaymentModelBD implements BDModel{

	@Override
	public DailyPayment bdToObject(ResultSet rs) throws SQLException {
		
		java.util.Date newDate = rs.getTimestamp(4);
		
		DailyPayment daily = new DailyPayment(newDate);
		daily.setIdDailyPayment(rs.getInt(1));
		daily.setIdCarCharged(rs.getInt(2));
		daily.setCost(rs.getDouble(3));
		java.util.Date newDateCheckout = rs.getTimestamp(5);
		daily.setCheckout(newDateCheckout);
		daily.setChargedPerHour(rs.getBoolean(6));
		
		return daily;
	}

	@Override
	public DailyPayment select(int id) throws SQLException {
		
		DailyPayment daily =null;
		List<DailyPayment> dailyList;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from daily_payment where id_daily_payment ="+id).executeQuery();
		dailyList= resultSetListToObjectList(rs);
	    if(dailyList.size() > 0)
	    	daily = dailyList.get(0);
		connection.close();
		
		return daily;
	}

	@Override
	public int insert(Object data) throws SQLException {
		DailyPayment daily = (DailyPayment)data;
		
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO daily_payment (id_daily_payment," +
				"id_car_charged,cost,checkin,checkout,is_charged_per_hour ) values (daily_sequence.nextval,?,?,?,?,?)");
		stmt.setInt(1, daily.getIdCarCharged());
		stmt.setDouble(2, daily.getCost());
		if(daily.getCheckin()!=null)
			stmt.setDate(3, new java.sql.Date(daily.getCheckin().getTime()));
		else
			stmt.setDate(3,null);
		if(daily.getCheckout()!=null)
			stmt.setDate(4, new java.sql.Date(daily.getCheckout().getTime()));
		else
			stmt.setDate(4,null);

		stmt.setBoolean(5, daily.isChargedPerHour());
		stmt.execute();
		
		String lastIdInsertedQuery = "SELECT TOP 1 id_daily_payment FROM daily_payment ORDER BY id_daily_payment DESC";
		stmt = connection.prepareStatement(lastIdInsertedQuery);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int id_daily_payment = rs.getInt(1);
		
		connection.close();

		return id_daily_payment;
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
	
	public int updateCheckout(Object data) throws SQLException {
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		DailyPayment dailyPayment = (DailyPayment)data;
		PreparedStatement stmt = connection.prepareStatement("update daily_payment set checkout=?, cost=? where id_daily_payment = ?");
		stmt.setDate(1, new java.sql.Date(dailyPayment.getCheckout().getTime()));
		stmt.setDouble(2, dailyPayment.getCost());
		stmt.setInt(3, dailyPayment.getIdDailyPayment());
		stmt.executeUpdate();
		
		connection.close();

		return 0;
	}


	@Override
	public List<DailyPayment> selectAll() throws SQLException {
		List<DailyPayment> dailyList;
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		
		rs = connection.prepareStatement("select * from daily_payment").executeQuery();
		 dailyList = resultSetListToObjectList(rs);
	    
		connection.close();
		
		return dailyList;
	}

	@Override
	public List<DailyPayment> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		List<DailyPayment> records= new ArrayList<DailyPayment>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
	}

}
