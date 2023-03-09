package si.um.feri.jee.sample.jsf.dao;

import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacientMemoryDao implements PacientDAO{

    private List<Pacient> pacienti;
    private PacientMemoryDao(){
        pacienti = Collections.synchronizedList(new ArrayList<>());
    }
    private static PacientMemoryDao instance = null;

    public synchronized  static PacientMemoryDao getInstance(){
        if(instance == null)
            return new PacientMemoryDao();
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
    public void izbrisiPacienta(String email) {
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
    }
    public List<Pacient> vrniNeopredeljenePaciente(){
        List<Pacient> neopredeljeni = new ArrayList<>();
        pacienti.forEach(pac -> {
            if(pac.getOsebniZdravnik() == null)
                neopredeljeni.add(pac);
        });
        return neopredeljeni;
    }

    @Override
    public void posodobiEmail(String stari, String novi) {
        System.out.println("stari mail" + stari);
        Pacient izbran = pridobiPacienta(stari);
        izbran.setEmail(novi);
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
