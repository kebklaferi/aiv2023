package si.um.feri.jee.sample.jsf.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.MessagingException;
import si.um.feri.jee.sample.jsf.dao.ObiskDAO;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.remote.DodajZdravnika;
import si.um.feri.jee.sample.jsf.strategija.IStrategija;
import si.um.feri.jee.sample.jsf.strategija.Konteks;
import si.um.feri.jee.sample.jsf.strategija.PosebnostiStrat;
import si.um.feri.jee.sample.jsf.strategija.ZdravilaStrat;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;


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
    private Obisk obisk = new Obisk();
    private Obisk izbranObisk = new Obisk();
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
        List<Obisk> temp = obiskDao.pridobiObiskeByPacient(posamezenPacient);
        System.out.println(temp);
        List<Obisk> temp2 = obiskDao.pridobiVse();
        System.out.println(temp2);
        if (temp == null)
            return new ArrayList<>();
        else return temp;
    }
    public String moznostObiska(){
        System.out.println("NEKAJ");
        if(posamezenPacient.getOsebniZdravnik() != null){
            obisk = new Obisk();
            return "/obisk/dodajObisk.xhtml";
        }
        return "";
    }

    public String dodajObisk(){
        obisk.setZdravnik(posamezenPacient.getOsebniZdravnik());
        obisk.setPacient(posamezenPacient);
        obisk.setZakljucen(false);
        obiskDao.shrani(obisk);
        return "/obisk/obiski";
    }

    public String urediObisk(Long o_id){
        System.out.println("UREJAM");
        izbranObisk = new Obisk();
        izbranObisk = obiskDao.pridobiObisk(posamezenPacient, o_id);
        if(izbranObisk == null)
            return "";
        else if(!izbranObisk.isZakljucen()){
            return "/obisk/urediObisk";
        }
        else
            return "";
    }
    public String potrdiUrejanjeObiska(){
        obiskDao.posodobi(izbranObisk);
        return "/obisk/obiski";
    }
    public String zakljuciObisk(Long id) throws MessagingException, NamingException {
        System.out.println("ID OBISKA" + id);
        izbranObisk = new Obisk();
        izbranObisk = obiskDao.pridobiObisk(posamezenPacient, id);
        if (izbranObisk == null)
            return "";
        if(!izbranObisk.isZakljucen()){
            izbranObisk.setZakljucen(true);
            obiskDao.posodobi(izbranObisk);
            Obisk temp = obiskDao.pridobiObisk(posamezenPacient, id);
            System.out.println("zakljucen: " + temp.isZakljucen());
            Konteks konteks = new Konteks();
            if(!izbranObisk.getPosebnosti().equals("")) {
                konteks.setStrategija(new PosebnostiStrat());
            }
            if (!izbranObisk.getZdravila().equals("")) {
                konteks.setStrategija(new ZdravilaStrat());
            }
            //lahko naredimo se ene strategijo za oboje
            konteks.proziStrat(izbranObisk);
        }
        return "/obisk/obiski";
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

    public Obisk getIzbranObisk() {
        return izbranObisk;
    }

    public void setIzbranObisk(Obisk izbranObisk) {
        this.izbranObisk = izbranObisk;
    }
}
