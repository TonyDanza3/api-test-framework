package core.http;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import utils.Config;

import static org.hamcrest.Matchers.*;

public class HttpSpecification {
    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(allOf(greaterThanOrEqualTo(200), lessThanOrEqualTo(299)))
                .build();
    }

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri(Config.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build()
//                .log()
//                .all()
                ;
    }
}
