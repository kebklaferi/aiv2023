package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;

import java.util.List;

@Stateless
public class ObiskMemoryDao implements ObiskDAO{
    @PersistenceContext(unitName = "sample_pu")
    private EntityManager em;
    @Override
    public void shrani(Obisk obisk) {
        em.persist(obisk);
    }
    @Override
    public void izbrisi(Obisk obisk) {
        em.createQuery("delete from Obisk o where o.id = :id")
                .setParameter("id", obisk.getId()).executeUpdate();
    }
    @Override
    public void posodobi(Obisk obisk) {
        em.createQuery("update Obisk o set o.posebnosti = :pos, o.termin = :ter, o.zdravila = :zrd where o.id = :id")
                .setParameter("pos", obisk.getPosebnosti()).setParameter("ter", obisk.getTermin())
                .setParameter("zrd", obisk.getZdravila()).executeUpdate();
    }
}
