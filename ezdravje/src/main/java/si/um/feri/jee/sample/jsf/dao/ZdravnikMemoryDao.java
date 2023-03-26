package si.um.feri.jee.sample.jsf.dao;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ZdravnikMemoryDao implements ZdravnikDAO{

    @PersistenceContext(unitName = "sample_pu")
    private EntityManager em;
    /*
    private List<Zdravnik> zdravniki = new ArrayList<>();
    private ZdravnikMemoryDao(){}

     */
    /*
    private static ZdravnikMemoryDao instance = null;
    public synchronized static ZdravnikMemoryDao getInstance() {
        if(instance == null)
            return new ZdravnikMemoryDao();
        return instance;
    }
     */
    @Override
    public List<Zdravnik> pridobiVseZdravnike() {
        //return zdravniki;
        try{
            return em.createQuery("select z from Zdravnik z").getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public boolean preveriEmail(String email) {
        /*
        for(Zdravnik z: zdravniki){
            if(z.getEmail().equals(email))
                return true;
        }
        return false;
         */
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
        /*
       Zdravnik izb = pridobiZdravnika(email);
       for(Zdravnik z: zdravniki){
           if (z == izb){
               z.getIzbraniPacienti().forEach(paci -> {
                   paci.setOsebniZdravnik(null);
               });
               zdravniki.remove(z);
               break;
           }
       }
         */
        Query poizvedba = em.createQuery("delete from Zdravnik z where z.email = :e");
        poizvedba.setParameter("e", email).executeUpdate();
    }


    @Override
    public Zdravnik pridobiZdravnika(String email) {
        /*
        for(Zdravnik z: zdravniki){
            if(z.getEmail().equals(email))
                return z;
        }
        return null;
         */
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
    public void posodobiZdravnika(Zdravnik zdr) {
        try{
            Query poizvedba = em.createQuery("update Zdravnik z set z.email = :e, z.ime = :i, z.priimek = :p");
            poizvedba.setParameter("e", zdr.getEmail()).setParameter("i", zdr.getIme()).setParameter("p", zdr.getPriimek());
            poizvedba.executeUpdate();
        } catch (Exception e){

        }
    }

    @Override
    public void dodajZdravnika(Zdravnik zdravnik) {
        //zdravniki.add(zdravnik);
        em.persist(zdravnik);
    }


    @Override
    public void dodajPacienta(Pacient pacient, String email) {
     /*
        for(Zdravnik z: zdravniki){
            if(z.getEmail().equals(email)){
                //potrebno preverit ali ima ze nek drug zdravnik tega pacienta oz ali je pacient ze dodan temu zdravniku
                for(Pacient p: z.getIzbraniPacienti()){
                    if(p.equals(pacient))
                        return;
                }
                z.getIzbraniPacienti().add(pacient);
            }
        }

        try{
            // List<Zdravnik> zdravniki = em.createQuery("select z from Zdravnik z").getResultList();
            CriteriaQuery<Zdravnik> criteriaQuery = em.getCriteriaBuilder().createQuery(Zdravnik.class);
            criteriaQuery.select(criteriaQuery.from(Zdravnik.class));
            List<Zdravnik> zdravniki = em.createQuery(criteriaQuery).getResultList();
            for (Zdravnik z : zdravniki){
                for(Pacient p: z.getIzbraniPacienti()){
                    if(p.equals(pacient))
                        return;
                }
                //tu
                ArrayList<Pacient> pacienti = z.getIzbraniPacienti();
                pacienti.add(pacient);
                Query poizvedba = em.createQuery("update Zdravnik z set z.izbraniPacienti = :p");
                poizvedba.setParameter("p", pacienti).executeUpdate();
            }
        } catch (Exception e){

        }*/
    }


    public List<Zdravnik> vrniOpredeljenePaciente(){
        /*
        List<Zdravnik> zdrav = new ArrayList<>();
        zdravniki.forEach(z -> {
            if(z.getIzbraniPacienti().size() != 0)
                zdrav.add(z);
        });
        return zdrav;

        try{
            CriteriaQuery<Zdravnik> criteriaQuery = em.getCriteriaBuilder().createQuery(Zdravnik.class);
            criteriaQuery.select(criteriaQuery.from(Zdravnik.class));
            List<Zdravnik> zdravniki = em.createQuery(criteriaQuery).getResultList();
            List<Zdravnik> zdrav = new ArrayList<>();
            zdravniki.forEach(z -> {
                if(z.getIzbraniPacienti().size() != 0)
                    zdrav.add(z);
            });
            return zdrav;
        } catch (Exception e){
            return new ArrayList<>();
        }*/
        return null;
    }

    @Override
    public void posodobiEmail(String stari, String novi) {
        /*
        Zdravnik izbran = pridobiZdravnika(stari);
        izbran.setEmail(novi);
         */
        Query poizvedba = em.createQuery("update Zdravnik z set z.email = :n where z.email = :e");
        poizvedba.setParameter("e", stari).setParameter("n", novi);
        int updPoizvedba = poizvedba.executeUpdate();
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
}
