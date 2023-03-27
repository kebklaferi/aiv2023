package si.um.feri.jee.sample.jsf.vmesni;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PosljiSporociloFasada {

    private Session session;

    public PosljiSporociloFasada() throws NamingException {
        session = (Session) new InitialContext().lookup("java:jboss/mail/gmail");
    }

    public void sendForUpdatedDoctor(String stari, String p) throws MessagingException {
        Message sporocilo = new MimeMessage(session);
        sporocilo.setSubject("Opozorilo o spremembi zdravnika");
        sporocilo.setFrom(new InternetAddress("itslionessmc@gmail.com"));
        sporocilo.addRecipient(Message.RecipientType.TO,new InternetAddress("itslionessmc@gmail.com"));
        sporocilo.setText("Odjavili ste se od " + stari + ".");
        Transport.send(sporocilo);
    }

    public void sendMail(boolean jePrazno, Zdravnik z, Pacient p) throws MessagingException {
        Message sporocilo = new MimeMessage(session);
        sporocilo.setSubject("Izbira zdravnika");
        sporocilo.setFrom(new InternetAddress("itslionessmc@gmail.com"));
        if(jePrazno == true){
            sporocilo.addRecipient(Message.RecipientType.TO, new InternetAddress("itslionessmc@gmail.com"));
            sporocilo.setText("Pozdravljeni, \npacient " + p.getEmail() + " je izbral zdravnika " + z.getEmail() + ".");
        }
        else{
            sporocilo.addRecipient(Message.RecipientType.TO, new InternetAddress(p.getEmail()));
            sporocilo.setText("Pozdravljeni, \nizbira zdravnika zal trenutno ni mozna.");
        }
        Transport.send(sporocilo);
    }
}
