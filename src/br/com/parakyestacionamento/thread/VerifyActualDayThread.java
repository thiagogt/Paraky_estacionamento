package br.com.parakyestacionamento.thread;

import org.joda.time.DateTime;


public class VerifyActualDayThread extends Thread{

	static int payDay = 0;
	
	@Override
	public void run() {
		
		System.out.println(" verificando a data de hoje....");
		DateTime dt = new DateTime();  // current time
		int actualDay = dt.getDayOfMonth();
			if(payDay != actualDay || payDay == 0){
				payDay = actualDay;
				createMonthlyPayment(actualDay);
			}
			
			
	}

	private void createMonthlyPayment(int actualDay) {
		
	}

	
}
