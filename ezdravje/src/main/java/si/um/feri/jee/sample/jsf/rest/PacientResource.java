package si.um.feri.jee.sample.jsf.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.jee.sample.jsf.dao.PacientDAO;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.dto.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.List;

@Path("/pacienti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacientResource {

    @EJB PacientDAO pacDao;
    @EJB ZdravnikDAO zdrDao;
    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    public String info(){
        return "pacienti";
    }

    @GET
    @Path("/")
    public List<Pacient> vrniPaciente(){
       List<si.um.feri.jee.sample.jsf.vao.Pacient> pac = pacDao.pridobiVsePaciente();
       List<Pacient> dtoPAc = new ArrayList<>();
       pac.forEach( pacient -> {
           dtoPAc.add(new Pacient(pacient));
               }
       );
       return dtoPAc;
    }
    @GET
    @Path("/{email}")
    public Pacient pridobiPacienta(@PathParam("email") String email){
        return new Pacient(pacDao.pridobiPacienta(email));
    }
    @POST
    @Path("/")
    public Long dodajPacienta(Pacient pac){
       return pacDao.dodajPacienta(pac.vrniVao());
    }
    @PUT
    @Path("/{email}/dodajZdr/{id}")
    public void izberiZdravnika(@PathParam("email") String pacMail, @PathParam("id") Long zdrId){
        Zdravnik zdravnik = zdrDao.pridobiZdravnika(zdrId);
        System.out.println(zdravnik.getKvotaPacientov());
        if(zdravnik.getKvotaPacientov() > pacDao.getPacientiByZdravnik(zdravnik)){
            si.um.feri.jee.sample.jsf.vao.Pacient pac = pacDao.pridobiPacienta(pacMail);
            pac.setOsebniZdravnik(zdravnik);
            pacDao.posodobiPacienta(pac, pac.getId());
        }
    }
}
