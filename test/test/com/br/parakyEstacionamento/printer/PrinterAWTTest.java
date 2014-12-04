package test.com.br.parakyEstacionamento.printer;

import static org.junit.Assert.fail;

import java.awt.print.PrinterException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.DailyPayment;
import br.com.parakyestacionamento.printer.PrinterAWT;

public class PrinterAWTTest {
	public PrinterAWT printer;
	public Car car;
	public DailyPayment daily;
	
	@Before
	public void init(){
		
		printer = new PrinterAWT();
		
		car = new Car();
		car.setCarPlate("LCY1884");
		car.setYearManufacture(1900);
		car.setColor("verde");
		car.setModel("1.8");
		car.setCarBrand("Pegoult");
		
		Calendar cal = Calendar.getInstance();
		daily = new DailyPayment(cal.getTime());
		daily.setIdDailyPayment(123456);
		daily.setCost(4.00);
		
		
	}
	
	
	@Test
	public void shouldPrintAHourTicketForCarPlateLCY1884(){
		
		
		
		try {
			printer.printTicketValueForCheckout(car,daily);
			assert(true);
		} catch (PrinterException e) {
			fail("Erro no teste shouldPrintATicket40ReaisForCarPlateLCY1884 "+e.getMessage() + e);
		}
		
	}
	
	@Test
	public void shouldPrintACompleteDailyTicketForCarPlateLCY1884(){
		
		daily.setCost(0);
		
		try {
			
			printer.printTicketValueForCheckout(car,daily);
			assert(true);
		} catch (PrinterException e) {
			fail("Erro no teste shouldPrintATicket40ReaisForCarPlateLCY1884 "+e.getMessage() + e);
		}
		
	}
	
	@Test
	public void shouldPrintANewDailyTicketForCarPlateLCY1884(){
		
		daily.setCost(0);
		
		try {
			
			printer.printNewDailyTicketForCheckin(car,daily);
			assert(true);
		} catch (PrinterException e) {
			fail("Erro no teste shouldPrintATicket40ReaisForCarPlateLCY1884 "+e.getMessage() + e);
		}
		
	}
}
