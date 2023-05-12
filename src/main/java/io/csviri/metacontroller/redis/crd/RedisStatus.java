package io.csviri.metacontroller.redis.crd;

public class RedisStatus {

    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
