package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Local;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;
@Local
public interface PacientDAO {
    void dodajPacienta(Pacient pacient);
    Pacient pridobiPacienta(String email);
    List<Pacient> pridobiVsePaciente();
    void izbrisiPacienta(String email);
    boolean preveriEmail(String email);
    List<Pacient> vrniNeopredeljenePaciente();
    List<Pacient> vrniOpredeljenePaciente();
    int getPacientiByZdravnik(Zdravnik z);
    void posodobiPacienta(Pacient posamezenPacient, Long id);
    List<Obisk> pridobiObiske(Long id);
}
