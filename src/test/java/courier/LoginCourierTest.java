package courier;

import api.CourierApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CourierData;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static model.CourierGenerator.getRandomCourier;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;

public class LoginCourierTest {

    protected int courierId;
    protected CourierData courierData;

    @Before
    public void setUP(){
        courierData = getRandomCourier("Victor", "password", "Victor");
        CourierApi courierApi = new CourierApi();
        courierApi.createCourier(courierData);
    }

    @After
    public void cleanUP(){
        CourierData courierDataForLogin = new CourierData(courierData.getLogin(),courierData.getPassword());
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.loginCourier(courierDataForLogin);
        courierId = response.extract().path("id");
        courierApi.deleteCourier(courierDataForLogin, String.valueOf(courierId));
    }


    @DisplayName("Check courier can be login")
    @Test
    public void courierCanBeLogin() {
        CourierData courierDataForLogin = new CourierData(courierData.getLogin(),courierData.getPassword());
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.loginCourier(courierDataForLogin);

        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue());
    }

}
