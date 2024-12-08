package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestApi {

    protected RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ApiEndpoints.BASE_URI)
                .setContentType(ContentType.JSON)
                .build()
                .filter(new AllureRestAssured())
                .log().all();
    }

}
