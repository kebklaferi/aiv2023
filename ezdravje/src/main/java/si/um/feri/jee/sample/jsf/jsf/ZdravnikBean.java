package si.um.feri.jee.sample.jsf.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.io.Serializable;
import java.util.List;

@Named("zdravnik")
@SessionScoped
public class ZdravnikBean implements Serializable {
    private ZdravnikDAO zdrDao = ZdravnikMemoryDao.getInstance();
    private Zdravnik zdr = new Zdravnik();
    private String email;

    public List<Zdravnik> pridobiVseZdravnike(){
        return zdrDao.pridobiVseZdravnike();
    }

    public void ustvariZdravnika(){
        zdrDao.dodajZdravnika(zdr);
    }

    public Zdravnik getZdr() {
        return zdr;
    }

    public void setZdr(Zdravnik zdr) {
        this.zdr = zdr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        zdr = zdrDao.pridobiZdravnika(email);
        if(zdr != null)
            this.email = email;
        else
            zdr = new Zdravnik();
    }
}
