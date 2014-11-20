package br.com.parakyestacionamento.modeloBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface BDModel {

	public Object bdToObject(ResultSet rs) throws SQLException;
	public String objectToBd() throws SQLException;
	public List<Object> selectAll( ) throws SQLException;
	public Object select(int id ) throws SQLException;
	public int insert(String data ) throws SQLException;
	public int delete(String data ) throws SQLException;
	public int update(String data ) throws SQLException;
	
}
