package core.endpoint;

import core.http.HttpMethod;

public record Endpoint(
        HttpMethod method,
        String url) {
}
