package si.um.feri.jee.sample.jsf.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.jee.sample.jsf.dao.ZdravnikDAO;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;

@Path("/zdravniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ZdravnikResource {

    @EJB ZdravnikDAO zdrDao;
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("info")
    public String info(){
        return "Zdravniki";
    }
    @GET
    @Path("/")
    public List<Zdravnik> vrniZdravnike(){
        return zdrDao.pridobiVseZdravnike();
    }
    @GET
    @Path("/{email}")
    public Zdravnik pridobiZdravnika(@PathParam("email") String email){
        return zdrDao.pridobiZdravnika(email);
    }
    @POST
    @Path("/")
    public void dodajZdravnika(Zdravnik zdr){
        zdrDao.dodajZdravnika(zdr);
    }
}
