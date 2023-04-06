package si.um.feri.jee.sample.jsf.dto;

public class Zdravnik {
    private String ime;
    private String priimek;
    private String email;
    private int kvotaPacientov;

    public Zdravnik(){}

    public Zdravnik(si.um.feri.jee.sample.jsf.vao.Zdravnik zdravnik){
        this.ime = zdravnik.getIme();
        this.priimek = zdravnik.getPriimek();
        this.email = zdravnik.getEmail();
        this.kvotaPacientov = zdravnik.getKvotaPacientov();
    }

    public si.um.feri.jee.sample.jsf.vao.Zdravnik vrniVao(){
        si.um.feri.jee.sample.jsf.vao.Zdravnik zdravnik = new si.um.feri.jee.sample.jsf.vao.Zdravnik();
        zdravnik.setEmail(this.getEmail());
        zdravnik.setIme(this.getIme());
        zdravnik.setPriimek(this.getPriimek());
        zdravnik.setKvotaPacientov(this.getKvotaPacientov());
        return zdravnik;
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

    public int getKvotaPacientov() {
        return kvotaPacientov;
    }

    public void setKvotaPacientov(int kvotaPacientov) {
        this.kvotaPacientov = kvotaPacientov;
    }
}
