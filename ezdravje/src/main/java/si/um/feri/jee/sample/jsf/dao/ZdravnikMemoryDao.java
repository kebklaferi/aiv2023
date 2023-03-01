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
    public void izbrisiZdravnika(Zdravnik zdravnik) {
        for(Zdravnik z: zdravniki){
            if(z.getEmail().equals(zdravnik.getEmail()))
                zdravniki.remove(z);
            else if(z.getEmail() == null || zdravnik.getEmail() == null)
                return;
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
    public void dodajPacienta(Pacient pacient, Zdravnik zdravnik) {
        for(Zdravnik z: zdravniki){
            if(z.getEmail().equals(zdravnik.getEmail())){
                z.getIzbraniPacienti().add(pacient);
            }
            else return;
        }
    }
}
