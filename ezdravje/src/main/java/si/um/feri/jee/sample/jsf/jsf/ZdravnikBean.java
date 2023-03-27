package si.um.feri.jee.sample.jsf.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("zdravniki")
@SessionScoped
public class ZdravnikBean implements Serializable {
    @EJB private ZdravnikDAO zdrDao;
    private Zdravnik izbranZdravnik = new Zdravnik();
    private Zdravnik podrobnostiZdravnik = new Zdravnik();

    public List<Zdravnik> getVseZdravnike(){
        return zdrDao.pridobiVseZdravnike();
    }
    public String ustvariZdravnika(){
        if(preveriEmail(izbranZdravnik.getEmail()))
            return "";
        zdrDao.dodajZdravnika(izbranZdravnik);
        izbranZdravnik = new Zdravnik();
        return "/ezdravje/zdravniki.xhtml";
    }
    public void pripraviPodrobnostiZdravnika(String email){
        podrobnostiZdravnik = new Zdravnik();
        podrobnostiZdravnik = zdrDao.pridobiZdravnika(email);
    }
    public List<Pacient> getVsePacienteByZdravnik(){
        List<Pacient> temp = zdrDao.getPacientiByZdravnik(podrobnostiZdravnik);
        if(temp == null)
            return new ArrayList<>();
        return temp;
    }
    public void potrdiUrejanje(){
        zdrDao.posodobiZdravnika(podrobnostiZdravnik, podrobnostiZdravnik.getId());
    }
    public void potrdiIzbris(){
        zdrDao.izbrisiZdravnika(podrobnostiZdravnik.getEmail());
    }
    public boolean preveriEmail(String email){
        return zdrDao.preveriEmail(email);
    }
    public void setPodrobnostiZdravnik(Zdravnik podrobnostiZdravnik) {
        this.podrobnostiZdravnik = podrobnostiZdravnik;
    }
    public Zdravnik getPodrobnostiZdravnik(){
        return podrobnostiZdravnik;
    }
    public Zdravnik getIzbranZdravnik() {
        return izbranZdravnik;
    }
    public void setIzbranZdravnik(Zdravnik izbranZdravnik) {
        this.izbranZdravnik = izbranZdravnik;
    }
}
