package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Local;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;

import java.util.List;

@Local
public interface ObiskDAO {
    void shrani(Obisk obisk);
    void izbrisi(Long id);
    void posodobi(Obisk obisk);
    Obisk pridobiObisk(Pacient p, Long id);
    List<Obisk> pridobiObiskeByPacient(Pacient p);

    List<Obisk> pridobiVse();
}
