package si.um.feri.jee.sample.jsf.remote;

import jakarta.ejb.Remote;
import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.NamingException;
import java.io.Serializable;

@Remote
public interface DodajZdravnika extends Serializable {

    void posljiInDodaj(String pacMail, String zdrMail) throws NamingException, MessagingException;
    //void posljiInDodaj(Pacient p, Zdravnik z);

}
