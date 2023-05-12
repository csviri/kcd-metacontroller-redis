package io.csviri;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Path("/sync")
public class RedisSyncController {

    private static final Logger log = LoggerFactory.getLogger(RedisSyncController.class);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String hello(Map<String, Object> payload) {
        log.info("Request:{}",payload);
        return "{}";
    }
}
