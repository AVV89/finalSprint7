package order;

import api.OrderApi;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;


public class GetOrdersTest {


    @Test
    public void CreateOrderWithColorTest() {

        OrderApi orderApi = new OrderApi();
        ValidatableResponse response = orderApi.getOrders();

        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("orders", notNullValue());;
    }
}
