package si.um.feri.jee.sample.jsf.rest;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        fillCors(containerResponseContext.getHeaders());
    }
    public static void fillCors(MultivaluedMap<String, Object> m){
        m.add("Access-Control-Allow-Origin", "*");
        m.add("Access-Control-Allow-Credentials", "true");
        m.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        m.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
