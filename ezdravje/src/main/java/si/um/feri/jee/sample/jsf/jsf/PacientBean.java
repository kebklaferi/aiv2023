package si.um.feri.jee.sample.jsf.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.dao.ObiskDAO;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.PacientMemoryDao;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.opazovalec.Opazovalec;
import si.um.feri.jee.sample.jsf.opazovalec.PacientOpazovalec;
import si.um.feri.jee.sample.jsf.remote.DodajZdravnika;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;
import si.um.feri.jee.sample.jsf.vmesni.PosljiSporociloFasada;


import javax.naming.NamingException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("pacienti")
@SessionScoped
public class PacientBean implements Serializable {
    @EJB private PacientDAO pacDao;
    @EJB private ZdravnikDAO zdrDao;
    @EJB private ObiskDAO obiskDao;
    @EJB private DodajZdravnika remoteDodaj;
    private Pacient izbranPacient = new Pacient();
    private Pacient posamezenPacient = new Pacient();
    private Obisk obisk = null;
    private Long zdrId;

    public List<Pacient> getVsePaciente(){
        return pacDao.pridobiVsePaciente();
    }
    public String ustvariPacienta(){
        if (preveriEmail(izbranPacient.getEmail()))
            return "/ezdravje/pacienti.xhtml";
        pacDao.dodajPacienta(izbranPacient);
        izbranPacient = new Pacient();
        return "/ezdravje/pacienti.xhtml";
    }
    public void potrdiIzbris(){
        pacDao.izbrisiPacienta(posamezenPacient.getEmail());
    }
    public void potrdiUrejanje() throws NamingException, MessagingException {
        Zdravnik temp = zdrDao.pridobiZdravnika(zdrId);
        boolean odg = moznostDodajanaPacientov(temp);
        if(temp != null)
            remoteDodaj.posljiInDodaj(posamezenPacient.getEmail(), temp.getEmail(), odg);
        else{
            pacDao.posodobiPacienta(posamezenPacient, posamezenPacient.getId());
        }
    }
    private boolean moznostDodajanaPacientov(Zdravnik z){
        if(z == null)
            return false;
        System.out.println(z.getKvotaPacientov() + "KVOTA");
        if(z.getKvotaPacientov() > pacDao.getPacientiByZdravnik(z)){
            return true;
        }
        return false;
    }

    public List<Obisk> pridobiObiske(){
        List<Obisk> temp = pacDao.pridobiObiske(posamezenPacient.getId());
        if (temp == null)
            return new ArrayList<>();
        else return temp;
    }
    public String moznostObiska(){
        if(posamezenPacient.getOsebniZdravnik() != null){
            obisk = new Obisk();
            return "/obisk/dodajObisk";
        }
        return "";
    }
    /*
    public String dodajObisk(){
        //shranit se mora tudi k posameznemu pacientu
        System.out.println("jes");

        obisk.setZdravnik(posamezenPacient.getOsebniZdravnik());
        obisk.setPacient(posamezenPacient);
        obiskDao.shrani(obisk);
        return "/obisk/obiski";


        return "";
    }*/

    public void probaj(){
        System.out.println("V METODI");
    }

    public List<Pacient> getNeopredeljenePaciente(){
        return pacDao.vrniNeopredeljenePaciente();
    }
    public List<Pacient> getOpredeljenePaciente(){
        return pacDao.vrniOpredeljenePaciente();
    }
    public void pripraviPodrobnostiPacienta(String email){
        posamezenPacient = new Pacient();
        posamezenPacient = pacDao.pridobiPacienta(email);
    }
    public Pacient getIzbranPacient() {
        return izbranPacient;
    }
    public void setIzbranPacient(Pacient izbranPacient) {
        this.izbranPacient = izbranPacient;
    }
    public boolean preveriEmail(String email){
        return pacDao.preveriEmail(email);
    }
    public Pacient getPosamezenPacient() {
        return posamezenPacient;
    }
    public void setPosamezenPacient(Pacient posamezenPacient) {
        this.posamezenPacient = posamezenPacient;
    }
    public Long getZdrId() {
        return zdrId;
    }
    public void setZdrId(Long zdrId) {
        this.zdrId = zdrId;
    }
    public Obisk getObisk() {
        return obisk;
    }
    public void setObisk(Obisk obisk) {
        this.obisk = obisk;
    }
}
