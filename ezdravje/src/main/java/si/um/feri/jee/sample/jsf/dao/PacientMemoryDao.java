package si.um.feri.jee.sample.jsf.dao;


import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class PacientMemoryDao implements PacientDAO{

    @PersistenceContext(unitName = "sample_pu")
    private EntityManager em;
    //private List<Pacient> pacienti;
    /*
    private PacientMemoryDao(){
        pacienti = Collections.synchronizedList(new ArrayList<>());
    }

     */
    /*
    private static PacientMemoryDao instance = null;

    public synchronized  static PacientMemoryDao getInstance(){
        if(instance == null)
            return new PacientMemoryDao();
        return instance;
    }
     */

    @Override
    public void dodajPacienta(Pacient pacient) {
        em.persist(pacient);
    }

    // -1,5h, ker sem pozabla {} :D
    @Override
    public Pacient pridobiPacienta(String email) {
        /*
        for(Pacient p: pacienti){
            if(p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
         */
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
       // return pacienti;
        try{
             return em.createQuery("select p from Pacient p").getResultList();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public void izbrisiPacienta(String email) {
        /*
        Pacient izb = pridobiPacienta(email);
        for(Pacient p: pacienti){
            if(p == izb){
                Zdravnik ose = p.getOsebniZdravnik();
                if(ose != null)
                    ose.getIzbraniPacienti().remove(p);
                pacienti.remove(p);
                break;
            }
        }
         */
        Query poizvedba = em.createQuery("delete from Pacient p where p.email = :e");
        poizvedba.setParameter("e", email).executeUpdate();
        //executeUpdate vrne št izbrisanih vrstic, glej spodaj
    }
    public List<Pacient> vrniNeopredeljenePaciente(){
        /*
        List<Pacient> neopredeljeni = new ArrayList<>();
        pacienti.forEach(pac -> {
            if(pac.getOsebniZdravnik() == null)
                neopredeljeni.add(pac);
        });
        return neopredeljeni;
         */
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
    public void posodobiEmail(String stari, String novi) {
        /*
        System.out.println("stari mail" + stari);
        Pacient izbran = pridobiPacienta(stari);
        izbran.setEmail(novi);
         */
        Query poizvedba = em.createQuery("update Pacient p set p.email = :n where p.email = :e");
        poizvedba.setParameter("e", stari).setParameter("n", novi);
        int updPoizvedba = poizvedba.executeUpdate();

    }


    @Override
    public boolean preveriEmail(String email) {
        /*
        for(Pacient p: pacienti){
            if(p.getEmail().equals(email))
                return true;
        }
        return false;
    }
         */
        try {
            Query poizvedba = em.createQuery("select p from Pacient p where p.email = :e");
            poizvedba.setParameter("e", email).getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
