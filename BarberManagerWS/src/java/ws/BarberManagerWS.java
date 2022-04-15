package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author lucas
 */
@Path("ws")
public class BarberManagerWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BarberManagerWS
     */
    public BarberManagerWS() {
    }

    /**
     * Retrieves representation of an instance of ws.BarberManagerWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        return "Barber Manger WS is ok right now!";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("registrarpc/{chave}")
    public String getRegistro(@PathParam("chave") String chave) {
        Registro r = new Registro(chave);
        return r.getNovoPC();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("validaracesso/{chave}/{pc}")
    public String getValidacao(@PathParam("chave") String chave, @PathParam("pc") String pc) {
        Acesso a = new Acesso(chave, pc);
        return a.getStatus();
    }

    /**
     * PUT method for updating or creating an instance of BarberManagerWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
