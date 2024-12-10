package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CourierData;

import static io.restassured.RestAssured.given;

public class CourierApi extends RestApi{

    @Step("Create courier")
    public ValidatableResponse createCourier(CourierData courier){
        return given()
                .spec(requestSpecification())
                .and()
                .body(courier)
                .when()
                .post(ApiEndpoints.CREATE_COURIER_URI)
                .then();
    }

    @Step("Delete courier")
    public ValidatableResponse deleteCourier(CourierData courier, String id){
        return given()
                .spec(requestSpecification())
                .and()
                .body(courier)
                .when()
                .delete(ApiEndpoints.DELETE_COURIER_URI, id)
                .then();
    }

    @Step("Login courier")
    public ValidatableResponse loginCourier(CourierData courier){
        return given()
                .spec(requestSpecification())
                .and()
                .body(courier)
                .when()
                .post(ApiEndpoints.LOGIN_COURIER_URI)
                .then();
    }

}
