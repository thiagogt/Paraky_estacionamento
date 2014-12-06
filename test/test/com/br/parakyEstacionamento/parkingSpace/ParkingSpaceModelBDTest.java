package test.com.br.parakyEstacionamento.parkingSpace;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.parakyestacionamento.domain.TicketParaky;
import br.com.parakyestacionamento.dominio.ParkingSpace;
import br.com.parakyestacionamento.modeloBD.ParkingSpaceModelBD;

public class ParkingSpaceModelBDTest {

	public ParkingSpaceModelBD parkingModel;
	
	@Before
	public void init(){
		parkingModel = new ParkingSpaceModelBD();
	}
	
	@Test
	public void shouldSelectAllParkingSpaces() {
		try {
			List<ParkingSpace> parkingList = parkingModel.selectAll();
			System.out.println("============== SELECT TICKET LIST ====================");
			for (ParkingSpace parkingSpace : parkingList) {
				System.out.println("Id vaga : "+parkingSpace.getIdParkingSpace()
						+" Nome do cliente: "+parkingSpace.getClientName()
						+" Custo : "+parkingSpace.getParkingSpaceCost());
			}
			assertTrue(true);
		} catch (SQLException e) {
			fail("Erro no teste shouldBringAllTicketList "+e.getMessage() + e);
		}
	}

}
