package test.com.br.parakyEstacionamento.dailyPayment;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sun.security.krb5.internal.Ticket;
import br.com.parakyestacionamento.domain.TicketParaky;
import br.com.parakyestacionamento.modeloBD.TicketModelBD;

public class TicketModelBDTest {

	public TicketModelBD model;
	
	@Before
	public void init(){
		model = new TicketModelBD();
	}
	
	@Test
	public void shouldBringAllTicketList() {

		try {
			List<TicketParaky> ticketList = model.selectAll();
			System.out.println("============== SELECT TICKET LIST ====================");
			for (TicketParaky ticketParaky : ticketList) {
				System.out.println("Num. ticket : "+ticketParaky.getIdDailyParking()
						+" Checkin : "+ticketParaky.getCheckin()
						+" Checkout : "+ticketParaky.getCheckout()
						+" Custo : "+ticketParaky.getCost()
						+" Placa : "+ticketParaky.getCarPlate()
						+" Marca : "+ticketParaky.getCarBrand()
						+" Modelo : "+ticketParaky.getModel());
			}
			assertTrue(true);
		} catch (SQLException e) {
			fail("Erro no teste shouldBringAllTicketList "+e.getMessage() + e);
		}
	}

}
