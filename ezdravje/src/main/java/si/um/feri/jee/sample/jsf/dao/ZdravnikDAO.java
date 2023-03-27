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
    void posodobiZdravnika(Zdravnik zdravnik, Long id);
    void dodajZdravnika(Zdravnik zdravnik);
    List<Pacient> getPacientiByZdravnik(Zdravnik zdr);
    Zdravnik pridobiZdravnika(Long id);

}
