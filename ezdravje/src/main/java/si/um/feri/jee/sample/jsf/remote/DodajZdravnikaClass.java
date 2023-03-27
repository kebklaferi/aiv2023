package si.um.feri.jee.sample.jsf.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.opazovalec.PacientOpazovalec;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;
import si.um.feri.jee.sample.jsf.vmesni.PosljiSporociloFasada;
import javax.naming.NamingException;

@Stateless
public class DodajZdravnikaClass implements DodajZdravnika{
    @EJB ZdravnikDAO zdrDao;
    @EJB PacientDAO pacDao;

    @Override
    public void posljiInDodaj(String pac, String zdr, boolean moznost) throws NamingException, MessagingException {
        PosljiSporociloFasada mail = new PosljiSporociloFasada();
        Pacient pacTemp = pacDao.pridobiPacienta(pac);
        Zdravnik zdrTemp = zdrDao.pridobiZdravnika(zdr);
        mail.sendMail(moznost, zdrTemp, pacTemp);
        System.out.println("MOZNOST DODAJANJA" + moznost);
        if(moznost){
            if(pacTemp.getOsebniZdravnik() != null)
                pacTemp.getOpazovalci().add(new PacientOpazovalec());
                pacTemp.notifyVseOpazovalce(zdrTemp.getEmail());
            pacTemp.setOsebniZdravnik(zdrTemp);
        }
        pacDao.posodobiPacienta(pacTemp, pacTemp.getId());
    }
}
