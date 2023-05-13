package io.csviri.metacontroller.redis;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/sync")
public class RedisSyncController {

    private static final Logger log = LoggerFactory.getLogger(RedisSyncController.class);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SyncResponse hello(SyncRequest request) {
        log.info("Request:{}",request);
        if (requiresAuthentication(request) && deploymentReady(request) && statusIsNotReady(request)) {

        }
        return createResponse("default");
    }

    private SyncResponse createResponse(String namespace) {
        return null;
    }


    private boolean statusIsNotReady(SyncRequest request) {
        return false;
    }

    private boolean deploymentReady(SyncRequest request) {
        return false;
    }

    private boolean requiresAuthentication(SyncRequest request) {
        return false;
    }
}
