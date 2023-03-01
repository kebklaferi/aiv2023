package si.um.feri.jee.sample.jsf.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.PacientMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Pacient;


import java.io.Serializable;
import java.util.List;

@Named("pacienti")
@SessionScoped
public class PacientBean implements Serializable {
    private PacientDAO pacDao = PacientMemoryDao.getInstance();
    private Pacient pac = new Pacient();
    private String email;

    public List<Pacient> pridobiVsePaciente(){
        return pacDao.pridobiVsePaciente();
    }

    public void ustvariPacienta(){
        System.out.println("TUKAJ");
        pacDao.dodajPacienta(pac);
    }

    public Pacient getPac() {
        return pac;
    }

    public void setPac(Pacient pac) {
        this.pac = pac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        pac = pacDao.pridobiPacienta(email);
        if(pac != null)
            this.email = email;
        else
            pac = new Pacient();
    }
}
