package api.gateway.service;

import core.http.HttpRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import core.http.HttpSpecification;

import static service.api.gateway.Endpoints.HEALTH;

public class ApiGatewayTest {
    @Test
    public void dummyTest() {
        HttpRequest.send(HEALTH.getEndpoint());

    }
}