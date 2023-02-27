package si.um.feri.jee.sample.jsf.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.io.Serializable;

@Named("zdravnik")
@SessionScoped
public class ZdravnikBean implements Serializable {

    private ZdravnikDAO zdrDao = ZdravnikMemoryDao.getInstance();
    private Zdravnik zdr = new Zdravnik();
}
