package si.um.feri.jee.sample.jsf.strategija;

import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vmesni.PosljiSporociloFasada;

import javax.naming.NamingException;

public class ZdravilaStrat implements IStrategija{
    @Override
    public void proziStrategijo(Obisk o) throws NamingException, MessagingException {
        PosljiSporociloFasada psf = new PosljiSporociloFasada();
        psf.sendZdravilaMail(o);
    }
}
