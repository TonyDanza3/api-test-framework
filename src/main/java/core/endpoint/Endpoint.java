package core.endpoint;

public record Endpoint(
        HttpMethod method,
        String endpoint) {
}
