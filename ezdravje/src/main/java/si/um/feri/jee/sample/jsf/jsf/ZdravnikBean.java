package si.um.feri.jee.sample.jsf.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("zdravniki")
@SessionScoped
public class ZdravnikBean implements Serializable {
    //private ZdravnikDAO zdrDao = ZdravnikMemoryDao.getInstance();
    @EJB private ZdravnikDAO zdrDao;
    private Zdravnik izbranZdravnik = new Zdravnik();
    private Zdravnik podrobnostiZdravnik = new Zdravnik();
    private String izbranEmail;
    private List<Pacient> izbraniPacienti = new ArrayList<>();
    private List<Zdravnik> mozniZdravniki = new ArrayList<>();
    private boolean urejanje = false;

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
    private boolean ponovljenMail(){
        if(podrobnostiZdravnik.getEmail().equals(izbranEmail)){
            return false;
        }
        return true;
    }
    public Zdravnik getPodrobnostiZdravnika(){
        System.out.println("TUKAJ");
        if (this.ponovljenMail()){
            podrobnostiZdravnik = new Zdravnik();
        }
        podrobnostiZdravnik = zdrDao.pridobiZdravnika(izbranEmail);
        if(podrobnostiZdravnik == null){
            podrobnostiZdravnik = new Zdravnik();
            return null;
        }
        return podrobnostiZdravnik;
    }

    public String dodajPacienteZdravniku(){
        if(!moznostDodajanaPacientov())
            return "";
        izbraniPacienti.forEach(pac -> {
            zdrDao.dodajPacienta(pac, izbranEmail);
            pac.setOsebniZdravnik(podrobnostiZdravnik);
        });
        izbraniPacienti = new ArrayList<>();
        return "podrobnostiZdravnik.xhtml";
    }

    public List<Zdravnik> getOpredeljenePaciente(){
        return zdrDao.vrniOpredeljenePaciente();
    }

    public void potrdiIzbris(){
        System.out.println("brisemo");
        zdrDao.izbrisiZdravnika(izbranEmail);
    }

    public boolean moznostDodajanaPacientov(){
        int kvota = podrobnostiZdravnik.getKvotaPacientov();
        int stPacientov = podrobnostiZdravnik.getIzbraniPacienti().size();
        if(kvota > stPacientov){
            if (izbraniPacienti.size() + stPacientov <= kvota)
                return true;
        }
        return false;
    }
    public boolean moznostDodajanaPacientov(Zdravnik z){
        if(z.getKvotaPacientov() > z.getIzbraniPacienti().size()){
            return true;
        }
        return false;
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

    public void setIzbranEmail(String email) {
        System.out.println(urejanje);
        if(urejanje){
                System.out.println(izbranEmail);
                System.out.println(email);
                zdrDao.posodobiEmail(izbranEmail, email);
                this.izbranEmail = email;
                urejanje = false;
                System.out.println("V SET IZBRAN MAIL");
        }
        this.izbranEmail = email;
    }
    public void potrdiUrejanje(){
        System.out.println("urejam");
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

    public boolean isUrejanje() {
        return urejanje;
    }

    public void setUrejanje(boolean urejanje) {
        this.urejanje = urejanje;
    }

    public void setMozniZdravniki(List<Zdravnik> mozniZdravniki) {
        this.mozniZdravniki = mozniZdravniki;
    }

    public List<Zdravnik> getMozniZdravniki() {
        List<Zdravnik> nov = new ArrayList<>();
        zdrDao.pridobiVseZdravnike().forEach(zdr -> {
            if(moznostDodajanaPacientov(zdr))
                nov.add(zdr);
        });
        mozniZdravniki = nov;
        return mozniZdravniki;
    }
}
