package io.csviri.metacontroller.redis;

import io.csviri.metacontroller.redis.crd.RedisStatus;
import io.fabric8.kubernetes.api.model.HasMetadata;

import java.util.List;

public class SyncResponse {

    private RedisStatus status;

    private List<HasMetadata> children;

    public RedisStatus getStatus() {
        return status;
    }

    public SyncResponse setStatus(RedisStatus status) {
        this.status = status;
        return this;
    }

    public List<HasMetadata> getChildren() {
        return children;
    }

    public SyncResponse setChildren(List<HasMetadata> children) {
        this.children = children;
        return this;
    }
}
