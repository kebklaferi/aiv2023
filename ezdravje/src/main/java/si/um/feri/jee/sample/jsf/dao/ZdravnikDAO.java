package si.um.feri.jee.sample.jsf.dao;

import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;

public interface ZdravnikDAO {
    List<Zdravnik> pridobiVseZdravnike();
    void izbrisiZdravnika(Zdravnik zdravnik);
    void pridobiZdravnika();
    void posodobiZdravnika();
    void dodajZdravnika(Zdravnik zdravnik);
    void dodajPacienta(Pacient pacient, Zdravnik zdravnik);

}
