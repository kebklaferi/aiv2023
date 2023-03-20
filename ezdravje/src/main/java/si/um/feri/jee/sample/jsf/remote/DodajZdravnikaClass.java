package si.um.feri.jee.sample.jsf.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;
import si.um.feri.jee.sample.jsf.vmesni.PosljiSporociloFasada;

import javax.naming.NamingException;
import java.util.ArrayList;

@Stateless
public class DodajZdravnikaClass implements DodajZdravnika{


    @EJB ZdravnikDAO zdrDao;
    @EJB PacientDAO pacDao;

    @Override
    public void posljiInDodaj(String pacMail, String zdrMail) throws NamingException, MessagingException {
        PosljiSporociloFasada mail = new PosljiSporociloFasada();
        Zdravnik zdr = zdrDao.pridobiZdravnika(zdrMail);
        Pacient pac = pacDao.pridobiPacienta(pacMail);
        boolean odg = moznostDodajanaPacientov(zdr);
        mail.sendMail(odg, zdr, pac);
        if(odg){
            if(pac.getOsebniZdravnik() != null)
                pac.notifyVseOpazovalce(pac.getOsebniZdravnik());
            pac.setOsebniZdravnik(zdr);
            ArrayList<Pacient> nov = zdr.getIzbraniPacienti();
            nov.add(pac);
            zdr.setIzbraniPacienti(nov);
        }
    }

    public boolean moznostDodajanaPacientov(Zdravnik z){
        if(z.getKvotaPacientov() > z.getIzbraniPacienti().size()){
            return true;
        }
        return false;
    }

    /*
    @Override
    public void posljiInDodaj(Pacient p, Zdravnik z) {

    }

     */


    /*
     public void potrdiUrejanje() throws NamingException, MessagingException {
        if(izbranOsebZdravnik != null){
           boolean odg = moznostDodajanaPacientov(izbranOsebZdravnik);
           PosljiSporociloFasada mail = new PosljiSporociloFasada();
           mail.sendMail(odg, izbranOsebZdravnik, posamezenPacient);
           if(odg){
               if(posamezenPacient.getOsebniZdravnik() != null)
                   notifyVseOpazovalce(posamezenPacient.getOsebniZdravnik(), posamezenPacient);
               posamezenPacient.setOsebniZdravnik(izbranOsebZdravnik);
               ArrayList<Pacient> nov = izbranOsebZdravnik.getIzbraniPacienti();
               nov.add(posamezenPacient);
               izbranOsebZdravnik.setIzbraniPacienti(nov);
           }
        }
    }
     */

}
