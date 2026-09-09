package service.market.data.service;

import core.endpoint.Endpoint;
import core.http.HttpMethod;

public enum Endpoints {
    INSTRUMENTS_GET(new Endpoint(HttpMethod.GET, "/api/v1/instruments")),
    INSTRUMENTS_POST(new Endpoint(HttpMethod.POST, "/api/v1/instruments")),
    INSTRUMENTS_PUT(new Endpoint(HttpMethod.PUT, "/api/v1/instruments")),
    INSTRUMENTS_DELETE(new Endpoint(HttpMethod.DELETE, "/api/v1/instruments"))
    ;

    private final Endpoint endpoint;

    Endpoints (Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }
}