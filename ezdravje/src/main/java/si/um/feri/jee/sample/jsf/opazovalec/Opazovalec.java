package si.um.feri.jee.sample.jsf.opazovalec;

import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.NamingException;
import java.io.Serializable;

public interface Opazovalec extends Serializable {
    void posljiSporocilo(Pacient pac, Zdravnik stari) throws NamingException, MessagingException;
}
