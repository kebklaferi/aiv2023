package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PacientMemoryDao implements PacientDAO{

    @PersistenceContext(unitName = "sample_pu")
    private EntityManager em;

    @Override
    public void dodajPacienta(Pacient pacient) {
        em.persist(pacient);
    }
    @Override
    public Pacient pridobiPacienta(String email) {
        try{
            Query poizvedba = em.createQuery("select p from Pacient p where p.email = :e");
            poizvedba.setParameter("e", email);
            return (Pacient) poizvedba.getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
    @Override
    public List<Pacient> pridobiVsePaciente() {
        try{
             return em.createQuery("select p from Pacient p").getResultList();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }
    @Override
    public void izbrisiPacienta(String email) {
        Query poizvedba = em.createQuery("delete from Pacient p where p.email = :e");
        poizvedba.setParameter("e", email).executeUpdate();
    }
    public List<Pacient> vrniNeopredeljenePaciente(){
        try{
            Query poizvedba = em.createQuery("select p from Pacient p where p.osebniZdravnik IS NULL");
            return poizvedba.getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
    @Override
    public List<Pacient> vrniOpredeljenePaciente() {
        try{
            return em.createQuery("select p from Pacient p where p.osebniZdravnik IS NOT NULL").getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
    @Override
    public int getPacientiByZdravnik(Zdravnik zdr) {
        try{
            Query poizvedba = em.createQuery("select p from Pacient p where p.osebniZdravnik = :z");
            poizvedba.setParameter("z", zdr);
            return poizvedba.getResultList().size();
        } catch (Exception e){
            return 0;
        }
    }
    @Override
    public void posodobiPacienta(Pacient pac, Long id) {
        try{
            Query poizvedba = em.createQuery("update Pacient p set p.priimek = :pri, p.ime = :i, " +
                    "p.email = :e, p.osebniZdravnik = :oz, p.posebnosti = :pos, p.rojstniDatum = :rd where p.id = :id");
            poizvedba.setParameter("pri", pac.getPriimek()).setParameter("i", pac.getIme()).setParameter("e", pac.getEmail())
                    .setParameter("oz", pac.getOsebniZdravnik()).setParameter("rd", pac.getRojstniDatum()).setParameter("pos", pac.getPosebnosti())
                    .setParameter("id", id);
            poizvedba.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Obisk> pridobiObiske(Long id) {
        try{
            return em.createQuery("select p.obiski from Pacient p where p.id = ?1").getResultList();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean preveriEmail(String email) {
        try {
            Query poizvedba = em.createQuery("select p from Pacient p where p.email = :e");
            poizvedba.setParameter("e", email).getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
