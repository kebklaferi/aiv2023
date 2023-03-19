package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Local;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;

@Local
public interface ZdravnikDAO {
    List<Zdravnik> pridobiVseZdravnike();
    boolean preveriEmail(String email);
    void izbrisiZdravnika(String email);
    Zdravnik pridobiZdravnika(String email);
    void posodobiZdravnika();
    void dodajZdravnika(Zdravnik zdravnik);
    void dodajPacienta(Pacient pacient, String email);
    void posodobiEmail(String stari, String novi);
    List<Zdravnik> vrniOpredeljenePaciente();

}
