package test;

import org.junit.jupiter.api.Test;

import static core.http.HttpRequest.send;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.api.gateway.Endpoints.HEALTH;
import static service.api.gateway.Endpoints.READY;

public class ApiGatewayTest {
    private static final String HEALTHY_RESPONSE = "{\"status\":\"ok\"}";

    @Test
    public void healthEndpointTest() {
        assertEquals(HEALTHY_RESPONSE, send(HEALTH.getEndpoint(), 200).getBody().asString().trim(), "Body is incorrect");
    }

    @Test
    public void readyEndpointTest() {
        assertEquals(HEALTHY_RESPONSE, send(READY.getEndpoint(), 200).getBody().asString().trim(), "Body is incorrect");
    }
}