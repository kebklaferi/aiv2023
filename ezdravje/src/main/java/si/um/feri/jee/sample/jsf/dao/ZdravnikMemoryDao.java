package si.um.feri.jee.sample.jsf.dao;

import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.List;

public class ZdravnikMemoryDao implements ZdravnikDAO{

    private List<Zdravnik> zdravniki = new ArrayList<>();
    private ZdravnikMemoryDao(){}
    private static ZdravnikMemoryDao instance = new ZdravnikMemoryDao();
    public static ZdravnikMemoryDao getInstance() {
        return instance;
    }
    @Override
    public List<Zdravnik> pridobiVseZdravnike() {
        return zdravniki;
    }

    @Override
    public boolean preveriEmail(String email) {
        for(Zdravnik z: zdravniki){
            if(z.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public void izbrisiZdravnika(String email) {
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
    }


    @Override
    public Zdravnik pridobiZdravnika(String email) {
        for(Zdravnik z: zdravniki){
            if(z.getEmail().equals(email))
                return z;
        }
        return null;
    }

    @Override
    public void posodobiZdravnika() {

    }

    @Override
    public void dodajZdravnika(Zdravnik zdravnik) {
        zdravniki.add(zdravnik);
    }

    @Override
    public void dodajPacienta(Pacient pacient, String email) {
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
    }

    @Override
    public void posodobiEmail(String stari, String novi) {
        Zdravnik izbran = pridobiZdravnika(stari);
        izbran.setEmail(novi);
    }
}
