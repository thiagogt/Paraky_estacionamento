package mailService;

import org.apache.commons.mail.EmailException;  
import org.apache.commons.mail.SimpleEmail;  
  
public class MailService {  
    private final static String userAuthentication = "paraky.estacionamento@gmail.com";  
    private final static String passwordUserAuthentication = "joaobatistatoledo";  
    private final static String sender = "paraky.estacionamento@gmail.com";  
    private final static String smtp = "smtp.gmail.com";  
    private final static boolean authentication = true;  
  
    public static void sendMail(String message, String subject, String receiver)  
            throws EmailException {  
  
        SimpleEmail email = new SimpleEmail();  
        email.setHostName(smtp);  
        email.setAuthentication(userAuthentication, passwordUserAuthentication);  
        email.setSSL(authentication);  
        email.addTo(receiver);  
        email.setFrom(sender);  
        email.setSubject(subject);  
        email.setMsg(message);  
        email.setCharset("UTF-8");
        email.send();  
        email = null;  
    }  
}  