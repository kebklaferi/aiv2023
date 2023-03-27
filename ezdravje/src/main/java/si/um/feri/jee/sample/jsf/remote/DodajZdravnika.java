package si.um.feri.jee.sample.jsf.remote;

import jakarta.ejb.Remote;
import jakarta.mail.MessagingException;
import javax.naming.NamingException;
import java.io.Serializable;

@Remote
public interface DodajZdravnika extends Serializable {

   void posljiInDodaj(String pacMail, String zdrMail, boolean moznost) throws NamingException, MessagingException;
}
