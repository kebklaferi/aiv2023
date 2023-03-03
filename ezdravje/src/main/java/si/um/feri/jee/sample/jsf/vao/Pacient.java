package si.um.feri.jee.sample.jsf.vao;

import java.util.Date;

public class Pacient {
    private String ime;
    private String priimek;
    private String email;
    private Date rojstniDatum;
    private String posebnosti;
    private Zdravnik osebniZdravnik;
    public Pacient(){
        this.ime = "";
        this.priimek = "";
        this.email = "";
        this.rojstniDatum = null;
        this.posebnosti = "";
        this.osebniZdravnik = null;
    }
    public Pacient(String ime, String priimek, String email, Date rojstniDatum, String posebnosti){
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.rojstniDatum = rojstniDatum;
        this.posebnosti = posebnosti;
        this.osebniZdravnik = null;
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
        this.osebniZdravnik = osebniZdravnik;
    }
}
