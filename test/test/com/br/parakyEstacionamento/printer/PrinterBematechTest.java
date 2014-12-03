package test.com.br.parakyEstacionamento.printer;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.parakyestacionamento.printer.PrinterBematech;

public class PrinterBematechTest {

	@Test
	public void shoulReturnSomething() {
		PrinterBematech.connectToBematech();
	}

}
