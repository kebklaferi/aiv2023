package si.um.feri.jee.sample.jsf.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.PacientMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Pacient;


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

    public List<Pacient> getVsePaciente(){
        return pacDao.pridobiVsePaciente();
    }

    public String ustvariPacienta(){
        if (preveriEmail(izbranPacient.getEmail()))
            return "";
        pacDao.dodajPacienta(izbranPacient);
        izbranPacient = new Pacient();
        return "pacienti.xhtml";
    }

    public Pacient getPridobiPacienta(){
        if (this.ponovljenMail()){
            posamezenPacient = new Pacient();
        }
        posamezenPacient = pacDao.pridobiPacienta(izbranEmail);
        if(posamezenPacient == null)
            return null;
        return posamezenPacient;
    }

    private boolean ponovljenMail(){
        if(posamezenPacient.getEmail().equals(izbranEmail)){
            return false;
        }
        return true;
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
        this.izbranEmail = email;
    }
}
