package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Local;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;

import java.util.List;

@Local
public interface ObiskDAO {
    void shrani(Obisk obisk);
    void izbrisi(Obisk obisk);
    void posodobi(Obisk obisk);

}
