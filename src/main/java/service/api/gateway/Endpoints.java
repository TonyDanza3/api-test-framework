package service.api.gateway;

import etc.Endpoint;
import etc.HttpMethod;

public enum Endpoints {
    HEALTH(new Endpoint(HttpMethod.GET, "/health")),
    READY(new Endpoint(HttpMethod.GET, "/ready"));


    private final Endpoint endpoint;
    public Endpoint getEndpoint() {
        return endpoint;
    }

    Endpoints(Endpoint endpoint) {
        this.endpoint = endpoint;
    }
}