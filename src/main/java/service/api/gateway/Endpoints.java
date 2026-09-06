package service.api.gateway;

import core.endpoint.Endpoint;
import core.http.HttpMethod;

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