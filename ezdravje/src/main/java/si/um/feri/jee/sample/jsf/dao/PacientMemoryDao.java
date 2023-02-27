package si.um.feri.jee.sample.jsf.dao;

import si.um.feri.jee.sample.jsf.vao.Pacient;

import java.util.ArrayList;
import java.util.List;

public class PacientMemoryDao implements PacientDAO{

    private ArrayList<Pacient> pacienti = new ArrayList<>();

    @Override
    public void dodajPacienta(Pacient pacient) {
        pacienti.add(pacient);
    }

    @Override
    public void izpisiPacienta() {

    }

    @Override
    public List<Pacient> pridobiVsePaciente() {
        return pacienti;
    }

    @Override
    public void izbrisiPacienta(Pacient pacient) {
        for(Pacient p: pacienti){
            if(p.getEmail().equals(pacient.getEmail())){
                pacienti.remove(p);
            }
            else if(p.getEmail() == null || pacient.getEmail() == null){
                return;
            }
        }
    }
}
