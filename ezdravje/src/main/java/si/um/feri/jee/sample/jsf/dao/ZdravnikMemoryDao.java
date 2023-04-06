package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ZdravnikMemoryDao implements ZdravnikDAO{

    @PersistenceContext(unitName = "sample_pu")
    private EntityManager em;

    @Override
    public List<Zdravnik> pridobiVseZdravnike() {
        try{
            return em.createQuery("select z from Zdravnik z").getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
    @Override
    public boolean preveriEmail(String email) {
        try {
            Query poizvedba = em.createQuery("select z from Zdravnik z where z.email = :e");
            poizvedba.setParameter("e", email).getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public void izbrisiZdravnika(String email) {
        Query poizvedba = em.createQuery("delete from Zdravnik z where z.email = :e");
        poizvedba.setParameter("e", email).executeUpdate();
    }
    @Override
    public Zdravnik pridobiZdravnika(String email) {
        try{
            Query poizvedba = em.createQuery("select z from Zdravnik z where z.email = :e");
            poizvedba.setParameter("e", email);
            return (Zdravnik) poizvedba.getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
    @Override
    public void posodobiZdravnika(Zdravnik zdr, Long id) {
        try{
            Query poizvedba = em.createQuery("update Zdravnik z set z.email = :e, z.ime = :i, z.priimek = :p where z.id = :id");
            poizvedba.setParameter("e", zdr.getEmail()).setParameter("i", zdr.getIme()).setParameter("p", zdr.getPriimek()).setParameter("id", id);
            poizvedba.executeUpdate();
        } catch (Exception e){

        }
    }
    @Override
    public Long dodajZdravnika(Zdravnik zdravnik) {
        em.persist(zdravnik);
        return zdravnik.getId();
    }
    @Override
    public List<Pacient> getPacientiByZdravnik(Zdravnik zdr) {
        try{
            Query poizvedba = em.createQuery("select p from Pacient p where p.osebniZdravnik = :z");
            poizvedba.setParameter("z", zdr);
            return poizvedba.getResultList();
        } catch (Exception e){
            return null;
        }
    }
    @Override
    public Zdravnik pridobiZdravnika(Long id) {
        try{
            return (Zdravnik) em.createQuery("select z from Zdravnik z where z.id = :id").setParameter("id", id).getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
}
