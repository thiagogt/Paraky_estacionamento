package br.com.parakyestacionamento.modeloBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.parakyestacionamento.dominio.MonthlyPayment;


public interface BDModel {

	
	public Object bdToObject(ResultSet rs) throws SQLException;
	public String objectToBd() throws SQLException;
	public Object select(int id ) throws SQLException;
	public int insert(Object data ) throws SQLException;
	public int delete(int id ) throws SQLException;
	public int update(Object data ) throws SQLException;
	public List<? extends Object> selectAll() throws SQLException;
	public List<? extends Object> resultSetListToObjectList(ResultSet rs)	throws SQLException; 
}
