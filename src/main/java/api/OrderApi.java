package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.OrderData;

import static io.restassured.RestAssured.given;

public class OrderApi extends RestApi{

    @Step("Create order")
    public ValidatableResponse createOrder(OrderData orderData){
        return given()
                .spec(requestSpecification())
                .and()
                .body(orderData)
                .when()
                .post(ApiEndpoints.ORDER_URI)
                .then();
    }

    @Step("Get orders")
    public ValidatableResponse getOrders(){
        return given()
                .spec(requestSpecification())
                .and()
                .when()
                .get(ApiEndpoints.ORDER_URI)
                .then();
    }


}
