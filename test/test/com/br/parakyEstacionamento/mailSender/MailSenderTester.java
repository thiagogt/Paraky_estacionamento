package test.com.br.parakyEstacionamento.mailSender;

import static org.junit.Assert.*;

import mailService.MailService;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

public class MailSenderTester {

	@Test
	public void shouldSendMailToThimakgt() {
		try {
			MailService.sendMail("It Works", "Hi", "thimakgt@gmail.com");
			assertTrue(true);
		} catch (EmailException e) {
			fail("Nao foi mandado o email: "+e.getMessage());
			System.out.println(e);
		}
	}

}
