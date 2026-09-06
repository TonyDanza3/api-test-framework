package core.http;

import core.endpoint.Endpoint;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpRequest {
    private static final Logger LOGGER = LogManager.getLogger();
    public static Response send(Endpoint endpoint) {
        Response response = new RestAssuredResponseImpl();
        switch (endpoint.method()) {
            case GET:
                LOGGER.log(Level.INFO, "Sending " + endpoint.method() + " request to " + endpoint.endpoint() + " endpoint");
                response = RestAssured.given(HttpSpecification.getRequestSpecification())
                        .get(endpoint.endpoint())
                        .then()
                        .spec(HttpSpecification.getResponseSpecification())
                        .extract().response();
                break;
//            case POST:
        }
        return response;
    }
}
