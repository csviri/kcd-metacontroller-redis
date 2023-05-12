package io.csviri.metacontroller.redis;

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
    public SyncResponse hello(Map<String,Object> payload) {
        log.info("Request:{}",payload);

        if (requiresAuthentication(payload) && deploymentReady(payload) && statusIsNotReady(payload)) {

        }

        return createResponse("default");
    }

    private SyncResponse createResponse(String namespace) {
        return null;
    }


    private boolean statusIsNotReady(Map<String, Object> payload) {
        return false;
    }

    private boolean deploymentReady(Map<String, Object> payload) {
        return false;
    }

    private boolean requiresAuthentication(Map<String, Object> payload) {
        return false;
    }
}
