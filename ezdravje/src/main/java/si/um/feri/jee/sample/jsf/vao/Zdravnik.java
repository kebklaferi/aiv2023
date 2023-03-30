package si.um.feri.jee.sample.jsf.vao;
import jakarta.persistence.*;

@Entity
public class Zdravnik{
    private String ime;
    private String priimek;
    private String email;
    private int kvotaPacientov;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Zdravnik(){}

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

    public Long getId() {
        return id;
    }
}
