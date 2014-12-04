package br.com.parakyestacionamento.printer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

import br.com.parakyestacionamento.dominio.Car;
import br.com.parakyestacionamento.dominio.DailyPayment;
import br.com.parakyestacionamento.properties.AppProperties;

public class PrinterAWT implements Printable{
	
	private DailyPayment daily;
	private Car car;
	private boolean hasToGenerateCheckinTicket=true;
	
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int page)
			throws PrinterException {
		 // We have only one page, and 'page'
	    // is zero-based
	    if (page > 0) {
	         return NO_SUCH_PAGE;
	    }

	    // User (0,0) is typically outside the
	    // imageable area, so we must translate
	    // by the X and Y values in the PageFormat
	    // to avoid clipping.
	    Graphics2D g2d = (Graphics2D)graphics;
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    g2d.setFont(new Font("Bookman", Font.PLAIN, 10));
	    // Now we perform our rendering
	    if(hasToGenerateCheckinTicket)
	    	createLayoutTicketForCheckin(graphics);
	    else
	    	createLayoutForCheckout(graphics);
	    
	    // tell the caller that this page is part
	    // of the printed document
	    return PAGE_EXISTS;
	}

	
	private void createLayoutForCheckout(Graphics graphics) {
		
		Date actualDate = Calendar.getInstance().getTime();
		
		graphics.drawString("======= Estacionamento Paraky =======", 0, 20);
		 graphics.drawString("Ticket número : " +daily.getIdDailyPayment(), 40, 40);
		 SimpleDateFormat sdf  =  new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		 String data  = sdf.format(actualDate);
		 graphics.drawString(data, 45, 60);
	    graphics.drawString("Comprovante de pagamento 	", 35, 90);
	    graphics.drawString("-------------------------------------------------------------", 0, 100);
	    graphics.drawString("Placa      : "+car.getCarPlate(), 0, 120);
	    graphics.drawString("Marca     : "+car.getCarBrand(), 0, 130);
	    graphics.drawString("Modelo   : "+car.getModel(), 0, 140);
	    SimpleDateFormat onlyHour  =  new SimpleDateFormat("HH:mm:ss");
	    String checkin = onlyHour.format(daily.getCheckin());
	    graphics.drawString("Entrada : "+checkin, 0, 180);
	    String checkout = onlyHour.format(actualDate);
	    graphics.drawString("Saída    : "+checkout, 0, 190);
	    
	    long diffTimesInMiliseconds = actualDate.getTime() - daily.getCheckin().getTime();
	    long diffSeconds = diffTimesInMiliseconds / 1000 % 60;
	    long diffMinutes = diffTimesInMiliseconds / (60 * 1000) % 60;
	    long diffHours = diffTimesInMiliseconds / (60 * 60 * 1000) % 24;
	    
	    graphics.drawString("Tempo de estadia   : "+diffHours+" hrs. "+diffMinutes+" min. "+diffSeconds+" seg.", 0, 200);
	    graphics.drawString("-------------------------------------------------------------", 0, 220);
		if(daily.getCost() != 0 )
			graphics.drawString("Custo por hora : R$ "+daily.getCost(), 70, 240);
		else{
	   		String completeDailyCost = getCostPerDay();
	   		graphics.drawString("Custo da diária : R$ "+completeDailyCost, 70, 240);
	   	}
	    graphics.drawString("TOTAL : R$ "+daily.getCost()*diffHours, 104, 260);
	    graphics.drawString("Este ticket não serve ", 48, 300);
	    graphics.drawString("como comprovante fiscal.", 43, 310);
	}


	private void createLayoutTicketForCheckin(Graphics graphics) {
		
		Date actualDate = Calendar.getInstance().getTime();
		
		graphics.drawString("======= Estacionamento Paraky =======", 0, 20);
		 graphics.drawString("Ticket número : " +daily.getIdDailyPayment(), 40, 40);
		 SimpleDateFormat sdf  =  new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		 String data  = sdf.format(actualDate);
		 graphics.drawString(data, 45, 60);
	    graphics.drawString("APRESENTE ESSE TICKET PARA", 20, 80);
	    graphics.drawString("RETIRAR SEU VEÍCULO", 40, 90);
	    graphics.drawString("-------------------------------------------------------------", 0, 100);
	    graphics.drawString("Placa      : "+car.getCarPlate(), 0, 120);
	    graphics.drawString("Marca     : "+car.getCarBrand(), 0, 130);
	    graphics.drawString("Modelo   : "+car.getModel(), 0, 140);
	    SimpleDateFormat onlyHour  =  new SimpleDateFormat("HH:mm:ss");
	    String checkin = onlyHour.format(daily.getCheckin());
	   	if(daily.getCost() != 0 )    
	   		graphics.drawString("Custo por hora : R$ "+daily.getCost(), 0, 170);
	   	else{
	   		String completeDailyCost = getCostPerDay();
	   		graphics.drawString("Custo da diária : R$ "+completeDailyCost, 0, 170);
	   	}
	    graphics.drawString("-------------------------------------------------------------", 0, 190);
	    graphics.drawString("Muito obrigado pela ", 48, 230);
	    graphics.drawString("sua preferência!", 53, 240);

		
	}


	private String getCostPerDay() {
		return AppProperties.defaultProps.getProperty("custo.diaria");
	}


	public void printTicketValueForCheckout(Car newCar, DailyPayment daily) throws PrinterException{
		 PrinterJob job = PrinterJob.getPrinterJob();
		 job.setPrintable(this);
		 
		 car = newCar;
		 this.daily = daily;
		 hasToGenerateCheckinTicket = false;		 
         boolean ok = job.printDialog();
         if (ok) {
        	 job.print();
         }
	}
	
	public void printNewDailyTicketForCheckin(Car newCar, DailyPayment daily) throws PrinterException{
		 PrinterJob job = PrinterJob.getPrinterJob();
		 job.setPrintable(this);
		 
		 car = newCar;
		 this.daily = daily;
		 hasToGenerateCheckinTicket = true;		 
        boolean ok = job.printDialog();
        if (ok) {
       	 job.print();
        }
	}

	

	public DailyPayment getDaily() {
		return daily;
	}


	public void setDaily(DailyPayment daily) {
		this.daily = daily;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public boolean isHasToGenerateCheckinTicket() {
		return hasToGenerateCheckinTicket;
	}


	public void setHasToGenerateCheckinTicket(boolean hasToGenerateCheckinTicket) {
		this.hasToGenerateCheckinTicket = hasToGenerateCheckinTicket;
	}
	
}
