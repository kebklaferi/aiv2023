package si.um.feri.jee.sample.jsf.opazovalec;

import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;
import si.um.feri.jee.sample.jsf.vmesni.PosljiSporociloFasada;

import javax.naming.NamingException;

public class PacientOpazovalec implements Opazovalec {

    @Override
    public void posljiSporocilo(String pac, String stari) throws NamingException, MessagingException {
        PosljiSporociloFasada spo = new PosljiSporociloFasada();
        spo.sendForUpdatedDoctor(stari, pac);
    }
}
