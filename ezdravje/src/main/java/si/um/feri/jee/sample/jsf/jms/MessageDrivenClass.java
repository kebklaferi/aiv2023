package si.um.feri.jee.sample.jsf.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import si.um.feri.jee.sample.jsf.remote.DodajZdravnika;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageDrivenClass implements MessageListener {

    @EJB DodajZdravnika dodajZdravnika;
    @Override
    public void onMessage(Message message) {
        if (message instanceof MapMessage temp) {
            try {
                String zdrMail = temp.getString("1");
                String pacMail = temp.getString("2");
                System.out.println(zdrMail + " " + pacMail);
                dodajZdravnika.posljiInDodaj(pacMail, zdrMail, true);

            } catch (Exception e) {
                System.out.println(message);
            }
        } else {
            System.out.println(message);
        }
    }
}
