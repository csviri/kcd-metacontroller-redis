package io.csviri.metacontroller.redis.crd;

public class RedisSpec {

    private boolean requireAuthentication;

    public boolean isRequireAuthentication() {
        return requireAuthentication;
    }

    public RedisSpec setRequireAuthentication(boolean requireAuthentication) {
        this.requireAuthentication = requireAuthentication;
        return this;
    }
}
