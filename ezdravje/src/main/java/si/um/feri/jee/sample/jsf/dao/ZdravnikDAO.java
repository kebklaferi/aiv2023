package si.um.feri.jee.sample.jsf.dao;

import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;

public interface ZdravnikDAO {
    List<Zdravnik> pridobiVseZdravnike();
    boolean preveriEmail(String email);
    void izbrisiZdravnika(Zdravnik zdravnik);
    Zdravnik pridobiZdravnika(String email);
    void posodobiZdravnika();
    void dodajZdravnika(Zdravnik zdravnik);
    void dodajPacienta(Pacient pacient, String email);

}
