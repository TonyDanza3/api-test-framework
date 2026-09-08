package service.user.service;

import core.endpoint.Endpoint;
import core.http.HttpMethod;

public enum Endpoints {
    ME(new Endpoint(HttpMethod.GET, "/api/v1/users/me"))
    ;

    private final Endpoint endpoint;

    Endpoints (Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }
}
