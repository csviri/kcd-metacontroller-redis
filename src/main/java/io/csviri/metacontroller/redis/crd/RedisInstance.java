package io.csviri.metacontroller.redis.crd;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

@Group("csviri.io")
@Version("v1")
public class RedisInstance extends CustomResource<RedisSpec, RedisStatus> implements Namespaced {

}
