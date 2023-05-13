package io.csviri.metacontroller.redis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.csviri.metacontroller.redis.crd.RedisInstance;
import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.HasMetadata;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncRequest {

    private GenericKubernetesResource controller;

    private RedisInstance parent;

    private Map<String, HasMetadata> children;

    public GenericKubernetesResource getController() {
        return controller;
    }

    public SyncRequest setController(GenericKubernetesResource controller) {
        this.controller = controller;
        return this;
    }

    public RedisInstance getParent() {
        return parent;
    }

    public SyncRequest setParent(RedisInstance parent) {
        this.parent = parent;
        return this;
    }

    public Map<String, HasMetadata> getChildren() {
        return children;
    }

    public SyncRequest setChildren(Map<String, HasMetadata> children) {
        this.children = children;
        return this;
    }
}
