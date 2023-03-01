package si.um.feri.jee.sample.jsf.dao;

import si.um.feri.jee.sample.jsf.vao.Pacient;
import java.util.List;

public interface PacientDAO {
    void dodajPacienta(Pacient pacient);
    Pacient pridobiPacienta(String email);
    List<Pacient> pridobiVsePaciente();
    void izbrisiPacienta(Pacient pacient);
}
