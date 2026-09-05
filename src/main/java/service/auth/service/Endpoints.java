package service.auth.service;

import etc.Endpoint;
import etc.HttpMethod;

public enum Endpoints {
    HEALTH(new Endpoint(HttpMethod.GET, "/health")),
    REGISTER(new Endpoint(HttpMethod.GET, "/api/v1/auth/register")),
    LOGIN(new Endpoint(HttpMethod.GET, "/api/v1/auth/login"))
    ;

    private final Endpoint endpoint;

    Endpoints (Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }
}
