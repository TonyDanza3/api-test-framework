package core.http;

import core.endpoint.Endpoint;
import dto.request.auth.RequestBody;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger LOGGER = LogManager.getLogger();

    public static Response send(Endpoint endpoint, int expectedStatusCode) {
        Response response = new RestAssuredResponseImpl();
        switch (endpoint.method()) {
            case GET:
                LOGGER.log(Level.INFO, "Sending " + endpoint.method() + " request to " + endpoint.endpoint() + " endpoint");
                response = RestAssured.given(HttpSpecification.getRequestSpecification())
                        .get(endpoint.endpoint())
                        .then()
                        .statusCode(expectedStatusCode)
                        .spec(HttpSpecification.getResponseSpecification())
                        .extract().response();
                break;
//            case POST:
        }
        return response;
    }

    public static Response send(Endpoint endpoint, Map<String, String> headers, int expectedStatusCode) {
        Response response = new RestAssuredResponseImpl();
        switch (endpoint.method()) {
            case GET:
                LOGGER.log(Level.INFO, "Sending " + endpoint.method() + " request to " + endpoint.endpoint() + " endpoint with headers:\n" + headersLogBuilder(headers));
                response = RestAssured.given(HttpSpecification.getRequestSpecification())
                        .headers(headers)
                        .get(endpoint.endpoint())
                        .then()
                        .statusCode(expectedStatusCode)
                        .spec(HttpSpecification.getResponseSpecification())
                        .extract()
                        .response();
                break;
//            case POST:
        }
        return response;
    }

    public static Response send(Endpoint endpoint, RequestBody body, int expectedStatusCode) {
        Response response = new RestAssuredResponseImpl();
        switch (endpoint.method()) {
            case POST:
                LOGGER.log(Level.INFO, "Sending " + endpoint.method() + " request to " + endpoint.endpoint() + " endpoint with body:\n" + body.toString());
                response = RestAssured
                        .given(HttpSpecification.getRequestSpecification())
                        .body(body)
                        .post(endpoint.endpoint())
                        .then()
                        .statusCode(expectedStatusCode)
                        .spec(HttpSpecification.getResponseSpecification())
                        .extract().response();
                break;
        }
        return response;
    }

    private static String headersLogBuilder(Map<String, String> headers) {
        StringBuilder result = new StringBuilder();
        headers.entrySet().forEach(entry -> {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        });
        return result.toString();
    }
}
