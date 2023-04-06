package si.um.feri.jee.sample.jsf.strategija;

import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.vao.Obisk;

import javax.naming.NamingException;

public class Konteks {
    private IStrategija strat;

    public void setStrategija(IStrategija strat){
        this.strat = strat;
    }

    public void proziStrat(Obisk o) throws MessagingException, NamingException {
        this.strat.proziStrategijo(o);
    }

}
