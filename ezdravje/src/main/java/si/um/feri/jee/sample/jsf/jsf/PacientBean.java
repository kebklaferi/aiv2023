package si.um.feri.jee.sample.jsf.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.PacientMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("pacienti")
@SessionScoped
public class PacientBean implements Serializable {
    private PacientDAO pacDao = PacientMemoryDao.getInstance();
    private Pacient izbranPacient = new Pacient();
    private Pacient posamezenPacient = new Pacient();
    private String izbranEmail;
    private boolean urejanje = false;

    public List<Pacient> getVsePaciente(){
        return pacDao.pridobiVsePaciente();
    }

    public String ustvariPacienta(){
        if (preveriEmail(izbranPacient.getEmail()))
            return "/ezdravje/pacienti.xhtml";
        pacDao.dodajPacienta(izbranPacient);
        izbranPacient = new Pacient();
        return "/ezdravje/pacienti.xhtml";
    }

    public Pacient getPridobiPacienta(){
        if (!this.stariIzbranMail()){
            posamezenPacient = new Pacient();
        }
        posamezenPacient = pacDao.pridobiPacienta(izbranEmail);
        if(posamezenPacient == null){
            posamezenPacient = new Pacient();
            return null;
        }
        return posamezenPacient;
    }

    private boolean stariIzbranMail(){
        if(posamezenPacient.getEmail().equals(izbranEmail)){
            return true;
        }
        return false;
    }
    public void potrdiIzbris(){
        System.out.println("brisemo");
       pacDao.izbrisiPacienta(izbranEmail);
    }
    public void potrdiUrejanje(){
        System.out.println("urejam");

    }
    public List<Pacient> getNeopredeljenePaciente(){
        return pacDao.vrniNeopredeljenePaciente();
    }

    private Zdravnik preveriObstojZdravnika(){
        Zdravnik osebni = posamezenPacient.getOsebniZdravnik();
        System.out.println("preverjam ce osebni zdravnik obstaja");
        if(osebni == null)
            return new Zdravnik();
        return osebni;
    }

    public boolean isUrejanje() {
        return urejanje;
    }

    public void setUrejanje(boolean urejanje) {
        this.urejanje = urejanje;
    }

    public Pacient getIzbranPacient() {
        return izbranPacient;
    }

    public void setIzbranPacient(Pacient izbranPacient) {
        this.izbranPacient = izbranPacient;
    }

    public boolean preveriEmail(String email){
        return pacDao.preveriEmail(email);
    }

    public String getIzbranEmail() {
        return izbranEmail;
    }

    public void setIzbranEmail(String email) {
        if(this.izbranEmail != null && !this.izbranEmail.equals(email)){
            if(urejanje){
                pacDao.posodobiEmail(izbranEmail, email);
                this.izbranEmail = email;
                urejanje = false;
                System.out.println("V SET IZBRAN MAIL");
            }
        }
        this.izbranEmail = email;
    }
}
