package si.um.feri.jee.sample.jsf.vao;
import java.util.ArrayList;

public class Zdravnik{
    private String ime;
    private String priimek;
    private String email;
    private int kvotaPacientov;
    private ArrayList <Pacient> izbraniPacienti;
    public Zdravnik(){
        izbraniPacienti = new ArrayList<>();
        this.ime = "";
        this.priimek = "";
        this.email = "";
        this.kvotaPacientov = 0;
    }

    public Zdravnik(String ime, String priimek, String email, int kvotaPacientov) {
        this();
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.kvotaPacientov = kvotaPacientov;
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

    public ArrayList<Pacient> getIzbraniPacienti() {
        return izbraniPacienti;
    }

    public void setIzbraniPacienti(ArrayList<Pacient> izbraniPacienti) {
        this.izbraniPacienti = izbraniPacienti;
    }
}
