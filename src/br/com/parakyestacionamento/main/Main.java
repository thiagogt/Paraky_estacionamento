package br.com.parakyestacionamento.main;

import java.io.IOException;
import java.sql.SQLException;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class Main {

	public static void main(String[] args) {
		
			try {				
				ConnectionDBFactory.createDataBase();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
	}

}
