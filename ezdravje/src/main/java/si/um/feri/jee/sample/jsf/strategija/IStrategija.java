package si.um.feri.jee.sample.jsf.strategija;

import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.vao.Obisk;

import javax.naming.NamingException;

public interface IStrategija {
    public void proziStrategijo(Obisk o) throws NamingException, MessagingException;
}
