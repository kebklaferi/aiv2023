package si.um.feri.jee.sample.jsf.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("zdravniki")
@SessionScoped
public class ZdravnikBean implements Serializable {
    @EJB private ZdravnikDAO zdrDao;
    private Zdravnik izbranZdravnik = new Zdravnik();
    private Zdravnik podrobnostiZdravnik = new Zdravnik();
    private String izbranEmail;

    public List<Zdravnik> getVseZdravnike(){
        return zdrDao.pridobiVseZdravnike();
    }

    public String ustvariZdravnika(){
        if(preveriEmail(izbranZdravnik.getEmail()))
            return "";
        zdrDao.dodajZdravnika(izbranZdravnik);
        izbranZdravnik = new Zdravnik();
        return "/ezdravje/zdravniki.xhtml";
    }

    public boolean preveriEmail(String email){
        return zdrDao.preveriEmail(email);
    }

    public void pripraviPodrobnostiZdravnika(String email){
        System.out.println("TUKAJ");
        izbranEmail = email;

        podrobnostiZdravnik = new Zdravnik();
        podrobnostiZdravnik = zdrDao.pridobiZdravnika(izbranEmail);

    }
    public Zdravnik getPodrobnostiZdravnik(){
        return podrobnostiZdravnik;
    }
    public List<Pacient> getVsePacienteByZdravnik(){
        List<Pacient> temp = zdrDao.getPacientiByZdravnik(podrobnostiZdravnik);
        if(temp == null)
            return new ArrayList<>();
        return temp;
    }

    public void potrdiIzbris(){
        System.out.println("brisemo");
        zdrDao.izbrisiZdravnika(podrobnostiZdravnik.getEmail());
    }

    public boolean moznostDodajanaPacientov(){
        /*
        int kvota = podrobnostiZdravnik.getKvotaPacientov();
        int stPacientov = podrobnostiZdravnik.getIzbraniPacienti().size();
        if(kvota > stPacientov){
            if (izbraniPacienti.size() + stPacientov <= kvota)
                return true;
        }
        return false;
         */
        return false;
    }
    public boolean moznostDodajanaPacientov(Zdravnik z){
        /*
        if(z.getKvotaPacientov() > z.getIzbraniPacienti().size()){
            return true;
        }
         */
        return false;
    }

    public Zdravnik getIzbranZdravnik() {
        return izbranZdravnik;
    }

    public void setIzbranZdravnik(Zdravnik izbranZdravnik) {
        this.izbranZdravnik = izbranZdravnik;
    }

    public void potrdiUrejanje(){
        zdrDao.posodobiZdravnika(podrobnostiZdravnik);
        System.out.println("urejam");
    }

    public void setPodrobnostiZdravnik(Zdravnik podrobnostiZdravnik) {
        this.podrobnostiZdravnik = podrobnostiZdravnik;
    }
}
