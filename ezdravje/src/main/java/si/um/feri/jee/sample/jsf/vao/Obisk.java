package si.um.feri.jee.sample.jsf.vao;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Obisk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Zdravnik zdravnik;
    @ManyToOne
    private Pacient pacient;
    private Date termin;
    private String zdravila;
    private String posebnosti;

    public Obisk(){}

    public Zdravnik getZdravnik() {
        return zdravnik;
    }

    public void setZdravnik(Zdravnik zdravnik) {
        this.zdravnik = zdravnik;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Date getTermin() {
        return termin;
    }

    public String getPosebnosti() {
        return posebnosti;
    }

    public void setPosebnosti(String posebnosti) {
        this.posebnosti = posebnosti;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
    }

    public String getZdravila() {
        return zdravila;
    }

    public void setZdravila(String zdravila) {
        this.zdravila = zdravila;
    }

    public Long getId() {
        return id;
    }
}
