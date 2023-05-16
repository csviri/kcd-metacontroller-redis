package io.csviri.metacontroller.redis;

import io.csviri.metacontroller.redis.crd.RedisStatus;
import io.fabric8.kubernetes.api.builder.Builder;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.runtime.RawExtension;
import io.fabric8.kubernetes.client.readiness.Readiness;
import io.fabric8.kubernetes.client.utils.Serialization;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Path("/sync")
public class RedisSyncController {

    private static final String DEPLOYMENT_KEY = "Deployment.apps/v1";
    public static final String REDIS_PASSWORD = "redis-password";

    private static final Logger log = LoggerFactory.getLogger(RedisSyncController.class);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SyncResponse hello(SyncRequest request) {
        log.info("Reconciling: {}", request.getParent().getMetadata().getName());

        if (request.getParent().getSpec().isRequireAuthentication() && deploymentReady(request)
                && statusIsNotReady(request)) {
            setPasswordInRedis(request);
        }

        return createResponse(request);
    }

    private SyncResponse createResponse(SyncRequest request) {
        SyncResponse response = new SyncResponse();
        List<HasMetadata> resources = new ArrayList<>();

        resources.add(desiredDeployment(request));
        resources.add(desiredService(request));

        if (request.getParent().getSpec().isRequireAuthentication()) {
            resources.add(desiredSecret(request));
        }

        response.setChildren(resources);
        RedisStatus redisStatus = new RedisStatus();
        redisStatus.setReady(deploymentReady(request));
        response.setStatus(redisStatus);
        return response;
    }

    private Deployment desiredDeployment(SyncRequest request) {
        Deployment deployment = loadYaml(Deployment.class, getClass(), "deployment.yaml");
        deployment.getMetadata().setName(request.getParent().getMetadata().getName());
        return deployment;
    }

    private Service desiredService(SyncRequest request) {
        Service service = loadYaml(Service.class, getClass(), "service.yaml");
        service.getMetadata().setName(request.getParent().getMetadata().getName());
        return service;
    }

    private Secret desiredSecret(SyncRequest request) {
        Secret secret = new Secret();
        secret.setMetadata(new ObjectMetaBuilder()
                .withName(request.getParent().getMetadata().getName())
                .build());
        secret.setData(Map.of(REDIS_PASSWORD, encode(generatePassword())));
        return secret;
    }

    private boolean statusIsNotReady(SyncRequest request) {
        return request.getParent().getStatus() == null || !request.getParent().getStatus().isReady();
    }

    private boolean deploymentReady(SyncRequest request) {
        var deployment = request.getChildren().get(DEPLOYMENT_KEY).get(request.getParent().getMetadata().getName());
        if (deployment == null) {
            return false;
        }
        return Readiness.isDeploymentReady((Deployment) deployment);
    }

    private void setPasswordInRedis(SyncRequest request) {
        log.info("Deployment ready. Setting password to Redis.");
    }

    public static <T> T loadYaml(Class<T> clazz, Class loader, String yaml) {
        try (InputStream is = loader.getResourceAsStream(yaml)) {
            return Serialization.unmarshal(is, clazz);
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot find yaml on classpath: " + yaml);
        }
    }

    private String generatePassword() {
        return "admin123";
    }

    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }
}
