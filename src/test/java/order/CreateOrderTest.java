package order;

import api.OrderApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.OrderData;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    public final List<String> color;

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getColor() {
        return new Object[][] {
                {Arrays.asList("BLACK", "GREY")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("")}
        };
    }

    public CreateOrderTest(List<String> color){
        this.color = color;
    }

    @DisplayName("Check order can be created")
    @Test
    public void createOrderWithColorTest() {
        OrderData orderData;

        if (!color.isEmpty()) {
            orderData = new OrderData(
                    "Misato",
                    "Katsuragi",
                    "Tokio-3",
                    3,
                    "+72526",
                    3, "2020-06-06", "Make it before the third strike!", color);
        } else {
            orderData = new OrderData(
                    "Misato",
                    "Katsuragi",
                    "Tokio-3",
                    3,
                    "+72526",
                    3, "2020-06-06", "Make it before the third strike!");
        }

        OrderApi orderApi = new OrderApi();
        ValidatableResponse response = orderApi.createOrder(orderData);

        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("track", notNullValue());
    }

}
