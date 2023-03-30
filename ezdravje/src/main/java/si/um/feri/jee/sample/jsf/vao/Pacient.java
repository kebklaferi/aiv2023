package si.um.feri.jee.sample.jsf.vao;
import jakarta.mail.MessagingException;
import jakarta.persistence.*;
import jdk.jfr.Name;
import si.um.feri.jee.sample.jsf.opazovalec.Opazovalec;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pacient{

    private String ime;
    private String priimek;
    private String email;
    private Date rojstniDatum;
    private String posebnosti;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pacient")
    private List<Obisk> obiski;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "zdravnik_id")
    private Zdravnik osebniZdravnik;
    @Transient
    private List<Opazovalec> opazovalci = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Pacient(){}

    public void notifyVseOpazovalce(String stari) throws MessagingException, NamingException {
        System.out.println("V NOTIFY OPAZOVALCA");
        for(Opazovalec e : opazovalci)
            e.posljiSporocilo(this.email, stari);
    }

    public List<Opazovalec> getOpazovalci() {
        return opazovalci;
    }
    public void setOpazovalci(List<Opazovalec> opazovalci) {
        this.opazovalci = opazovalci;
    }
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRojstniDatum() {
        return rojstniDatum;
    }

    public void setRojstniDatum(Date rojstniDatum) {
        this.rojstniDatum = rojstniDatum;
    }

    public String getPosebnosti() {
        return posebnosti;
    }

    public void setPosebnosti(String posebnosti) {
        this.posebnosti = posebnosti;
    }
    public Zdravnik getOsebniZdravnik() {
        return osebniZdravnik;
    }
    public void setOsebniZdravnik(Zdravnik osebniZdravnik) {
        System.out.println("V PACIENT VAO OSEBNI ZDR: " + osebniZdravnik);
        this.osebniZdravnik = osebniZdravnik;
    }
    public Long getId() {
        return id;
    }
    public List<Obisk> getObiski() {
        return obiski;
    }
    public void setObiski(List<Obisk> obiski) {
        this.obiski = obiski;
    }
}
