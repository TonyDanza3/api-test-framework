package core.http;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Config;

public class HttpSpecification {
    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
//                .log(LogDetail.ALL)
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
