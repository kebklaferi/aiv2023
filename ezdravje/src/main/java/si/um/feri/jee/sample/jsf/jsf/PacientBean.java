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
    private String pacEmail;

    public List<Pacient> getVsePaciente(){
        return pacDao.pridobiVsePaciente();
    }

    public void ustvariPacienta(){
        System.out.println("TUKAJ");
        pacDao.dodajPacienta(izbranPacient);
        izbranPacient = new Pacient();
    }

    public Pacient getIzbranPacient() {
        return izbranPacient;
    }

    public void setIzbranPacient(Pacient izbranPacient) {
        this.izbranPacient = izbranPacient;
    }

    public String getPacEmail() {
        return pacEmail;
    }

    public void setPacEmail(String email) {
        pacEmail = email;
        izbranPacient = pacDao.pridobiPacienta(email);
        if(izbranPacient == null){
            System.out.println("NULL JE");
            izbranPacient = new Pacient();
        }

    }
}
