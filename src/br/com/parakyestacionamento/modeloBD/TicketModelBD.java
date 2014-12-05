package br.com.parakyestacionamento.modeloBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.parakyestacionamento.domain.TicketParaky;
import br.com.parakyestacionamento.dominio.MonthlyPaymentPerClient;
import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class TicketModelBD implements BDModel{

	@Override
	public TicketParaky bdToObject(ResultSet rs) throws SQLException {
		
		TicketParaky ticket = new TicketParaky();
		ticket.setIdDailyParking(rs.getInt(1));
		
		Date checkinDate = rs.getTimestamp(2);
		Date checkoutDate = rs.getTimestamp(3);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if(checkinDate != null)
			ticket.setCheckin(sdf.format(checkinDate));
		else
			ticket.setCheckin("--");
		if(checkoutDate != null)
			ticket.setCheckout(sdf.format(checkoutDate));
		else
			ticket.setCheckout("--");
		ticket.setCost(rs.getDouble(4));
		ticket.setCarPlate(rs.getString(5));
		ticket.setCarBrand(rs.getString(6));
		ticket.setModel(rs.getString(7));
		
		return ticket;
	}

	@Override
	public TicketParaky select(int id) throws SQLException {
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
	public List<TicketParaky> selectAll() throws SQLException {
		Connection connection = null;
		try {
			connection =	ConnectionDBFactory.getDataBaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		/*
CREATE TABLE car( 
id_car INTEGER IDENTITY,
id_client_car INTEGER,
year_manufacture INTEGER,
color VARCHAR(256) not null,
model VARCHAR(256), 
car_plate VARCHAR(256) UNIQUE not null, 
car_brand VARCHAR(256));

CREATE TABLE daily_payment( 
id_daily_payment INTEGER IDENTITY,
id_car_charged INTEGER NOT NULL,
cost double,
checkin TIMESTAMP,
checkout TIMESTAMP);*/
		rs = connection.prepareStatement("select daily_payment.id_daily_payment,daily_payment.checkin,daily_payment.checkout,daily_payment.cost,car.car_plate,car.car_brand,car.model from daily_payment  join car on daily_payment.id_car_charged = car.id_car").executeQuery();
		List<TicketParaky> ticketList = resultSetListToObjectList(rs);
	    
		connection.close();
		
		return ticketList;
	}

	@Override
	public List<TicketParaky> resultSetListToObjectList(ResultSet rs)
			throws SQLException {
		List<TicketParaky> records= new ArrayList<TicketParaky>();
		while(rs.next()){
		    
		    records.add(bdToObject(rs));
		}
		return records;
	}

}
