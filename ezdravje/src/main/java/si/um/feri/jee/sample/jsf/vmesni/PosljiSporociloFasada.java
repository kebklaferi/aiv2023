package si.um.feri.jee.sample.jsf.vmesni;

import jakarta.annotation.Resource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;

public class PosljiSporociloFasada {

    private Session session;

    public PosljiSporociloFasada() throws NamingException {
        session = (Session) new InitialContext().lookup("java:jboss/mail/gmail");
    }

    public void sendMail(boolean jePrazno, Zdravnik z, Pacient p) throws MessagingException {
        Message sporocilo = new MimeMessage(session);
        //sporocilo.setFrom(new InternetAddress("itslionessmc@gmail.com"));
        sporocilo.setSubject("Izbira zdravnika");
        if(jePrazno == true){
            Address[] arr = {new InternetAddress(z.getEmail()), new InternetAddress(p.getEmail())};
            sporocilo.addRecipients(Message.RecipientType.TO, arr);
            sporocilo.setText("Pozdravljeni, \npacient " + p.getEmail() + " je izbral zdravnika" + z.getEmail() + ".");
        }
        else{
            sporocilo.addRecipient(Message.RecipientType.TO, new InternetAddress(p.getEmail()));
            sporocilo.setText("Pozdravljeni, \nizbira zdravnika zal trenutno ni mozna.");
        }
        Transport.send(sporocilo);
    }
}
