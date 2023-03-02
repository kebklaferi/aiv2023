package si.um.feri.jee.sample.jsf.dao;

import si.um.feri.jee.sample.jsf.vao.Pacient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacientMemoryDao implements PacientDAO{

    private List<Pacient> pacienti;
    private PacientMemoryDao(){
        pacienti = Collections.synchronizedList(new ArrayList<>());
    }
    private static PacientMemoryDao instance = new PacientMemoryDao();

    public static PacientMemoryDao getInstance(){
        return instance;
    }

    @Override
    public void dodajPacienta(Pacient pacient) {
        pacienti.add(pacient);
    }

    // -1,5h, ker sem pozabla {} :D
    @Override
    public Pacient pridobiPacienta(String email) {
        for(Pacient p: pacienti){
            if(p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Pacient> pridobiVsePaciente() {
        return pacienti;
    }

    @Override
    public void izbrisiPacienta(Pacient pacient) {
        for(Pacient p: pacienti){
            if(p.getEmail().equals(pacient.getEmail()))
                pacienti.remove(p);
            else if(p.getEmail() == null || pacient.getEmail() == null)
                return;
        }
    }

    @Override
    public boolean preveriEmail(String email) {
        for(Pacient p: pacienti){
            if(p.getEmail().equals(email))
                return true;
        }
        return false;
    }
}
