package test.com.br.parakyEstacionamento.parkingSpace;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.parakyestacionamento.domain.TicketParaky;
import br.com.parakyestacionamento.dominio.ParkingSpace;
import br.com.parakyestacionamento.modeloBD.ParkingSpaceModelBD;

public class ParkingSpaceModelBDTest {

	public ParkingSpaceModelBD parkingModel;
	public ParkingSpace parkingSpace;
	
	@Before
	public void init(){
		parkingModel = new ParkingSpaceModelBD();
		
		parkingSpace = new ParkingSpace(1);
		parkingSpace.setParkingSpaceCost(200.00);
		parkingSpace.setPayDay(3);
		parkingSpace.setTypeParkingSpace("carro");
		parkingSpace.setContractDate(Calendar.getInstance().getTime());
		
		
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
	

	@Test
	public void shouldInsertParkingSpace() {
		try {
			
			 parkingModel.insert(parkingSpace);
			
			
			List<ParkingSpace> parkingList = parkingModel.selectAll();
			System.out.println("============== INSERT PARKING SPACE ====================");
			for (ParkingSpace parkingSpace : parkingList) {
				System.out.println("Id vaga : "+parkingSpace.getIdParkingSpace()
						+" Nome do cliente: "+parkingSpace.getClientName()
						+" Custo : "+parkingSpace.getParkingSpaceCost());
			}
			assertTrue(true);
		} catch (SQLException e) {
			fail("Erro no teste shouldInsertParkingSpace "+e.getMessage() + e);
		}
	}

}
