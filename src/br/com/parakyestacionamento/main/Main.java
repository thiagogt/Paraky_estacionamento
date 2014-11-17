package br.com.parakyestacionamento.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			try {				
				//ConnectionDBFactory.createDataBaseServer();
				//ConnectionDBFactory.startDataBaseServer();
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
