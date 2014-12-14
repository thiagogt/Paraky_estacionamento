package br.com.parakyestacionamento.main;


import br.com.parakyestacionamento.hsqldb.ConnectionDBFactory;
import br.com.parakyestacionamento.properties.AppProperties;
import br.com.parakyestacionamento.thread.VerifyActualDayThread;

public class Main {

	public static void main(String[] args) {
		
			try {				
				ConnectionDBFactory.createDataBase();
				
				VerifyActualDayThread payDayThread = new VerifyActualDayThread();
				payDayThread.start();
				
			} catch (Exception e) {
				System.out.println("Erro ao executar a main :"+e.getMessage());
				System.out.println(e);
			} 
		
	}

}
