package si.um.feri.jee.sample.jsf.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("zdravniki")
@SessionScoped
public class ZdravnikBean implements Serializable {
    private ZdravnikDAO zdrDao = ZdravnikMemoryDao.getInstance();
    private Zdravnik izbranZdravnik = new Zdravnik();
    private Zdravnik podrobnostiZdravnik = new Zdravnik();
    private String izbranEmail;
    private List<Pacient> izbraniPacienti = new ArrayList<>();

    public List<Zdravnik> getVseZdravnike(){
        return zdrDao.pridobiVseZdravnike();
    }

    public String ustvariZdravnika(){
        if(preveriEmail(izbranZdravnik.getEmail()))
            return "";
        zdrDao.dodajZdravnika(izbranZdravnik);
        izbranZdravnik = new Zdravnik();
        return "zdravniki.xhtml";
    }

    public boolean preveriEmail(String email){
        return zdrDao.preveriEmail(email);
    }
    private boolean ponovljenMail(){
        if(podrobnostiZdravnik.getEmail().equals(izbranEmail)){
            return false;
        }
        return true;
    }
    public Zdravnik getPodrobnostiZdravnika(){
        if (this.ponovljenMail()){
            podrobnostiZdravnik = new Zdravnik();
        }
        podrobnostiZdravnik = zdrDao.pridobiZdravnika(izbranEmail);
        if(podrobnostiZdravnik == null)
            return null;
        return podrobnostiZdravnik;
    }

    public String dodajPacienteZdravniku(){
        izbraniPacienti.forEach(pac -> {
            zdrDao.dodajPacienta(pac, izbranEmail);
        });
        izbraniPacienti = new ArrayList<>();
        return "podrobnostiZdravnik.xhtml";
    }

    public Zdravnik getIzbranZdravnik() {
        return izbranZdravnik;
    }

    public void setIzbranZdravnik(Zdravnik izbranZdravnik) {
        this.izbranZdravnik = izbranZdravnik;
    }

    public String getIzbranEmail() {
        return izbranEmail;
    }

    public void setIzbranEmail(String izbranEmail) {
        this.izbranEmail = izbranEmail;
    }

    public Zdravnik getPodrobnostiZdravnik() {
        return podrobnostiZdravnik;
    }

    public void setPodrobnostiZdravnik(Zdravnik podrobnostiZdravnik) {
        this.podrobnostiZdravnik = podrobnostiZdravnik;
    }

    public List<Pacient> getIzbraniPacienti() {
        return izbraniPacienti;
    }

    public void setIzbraniPacienti(List<Pacient> izbraniPacienti) {
        this.izbraniPacienti = izbraniPacienti;
    }
}
