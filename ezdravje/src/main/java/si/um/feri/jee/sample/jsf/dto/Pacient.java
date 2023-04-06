package si.um.feri.jee.sample.jsf.dto;

import java.util.Date;

public class Pacient {
    private String ime;
    private String priimek;
    private String email;
    private Date rojstniDatum;
    private String posebnosti;
    private Zdravnik osebniZdravnik;

    public Pacient(){}

    public Pacient(si.um.feri.jee.sample.jsf.vao.Pacient pacient){
        this.ime = pacient.getIme();
        this.priimek = pacient.getPriimek();
        this.email = pacient.getEmail();
        this.rojstniDatum = pacient.getRojstniDatum();
        this.posebnosti = pacient.getPosebnosti();
        if(pacient.getOsebniZdravnik() != null)
            this.osebniZdravnik = new Zdravnik(pacient.getOsebniZdravnik());
    }
    public si.um.feri.jee.sample.jsf.vao.Pacient vrniVao(){
        si.um.feri.jee.sample.jsf.vao.Pacient pacient = new si.um.feri.jee.sample.jsf.vao.Pacient();
        Zdravnik temp = this.getOsebniZdravnik();
        if(temp != null){
            pacient.setOsebniZdravnik(this.getOsebniZdravnik().vrniVao());
        }
        pacient.setIme(this.getIme());
        pacient.setPriimek(this.getPriimek());
        pacient.setRojstniDatum(this.getRojstniDatum());
        pacient.setPosebnosti(this.getPosebnosti());
        pacient.setEmail(this.getEmail());
        return pacient;
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
