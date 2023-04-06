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
    public void izbrisi(Long id) {
        em.createQuery("delete from Obisk o where o.id = :i").setParameter("i", id).executeUpdate();
    }
    @Override
    public void posodobi(Obisk obisk) {
        em.createQuery("update Obisk o set o.posebnosti = :pos, o.termin = :ter, o.zdravila = :zrd, o.zakljucen = :za where o.id = :id")
                .setParameter("pos", obisk.getPosebnosti()).setParameter("ter", obisk.getTermin())
                .setParameter("zrd", obisk.getZdravila()).setParameter("id", obisk.getId())
                .setParameter("za", obisk.isZakljucen()).executeUpdate();
    }

    @Override
    public Obisk pridobiObisk(Pacient p, Long id) {
        try{
            return (Obisk) em.createQuery("select o from Obisk o where o.pacient = :pac and o.id = :i")
                    .setParameter("pac", p).setParameter("i", id).getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obisk> pridobiObiskeByPacient(Pacient p) {
        try{
            return em.createQuery("select o from Obisk o where o.pacient = :id").setParameter("id", p).getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Obisk> pridobiVse() {
        try{
            return em.createQuery("select o from Obisk o").getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
