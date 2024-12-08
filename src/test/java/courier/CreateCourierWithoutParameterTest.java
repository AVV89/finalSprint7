package courier;

import api.CourierApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CourierData;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import static model.CourierGenerator.getRandomCourier;
import static org.hamcrest.CoreMatchers.is;

public class CreateCourierWithoutParameterTest {
    protected CourierData courierData;

    @Before
    public void setUP(){
        courierData = getRandomCourier("Victor", "password", "Victor");
    }

    @DisplayName("Check courier can not be created without login and with password")
    @Test
    public void courierCanNotBeCreatedWithoutLogin(){

        CourierData courierDataWithoutLogin = new CourierData(null,courierData.getPassword());
        CourierApi courierApi = new CourierApi();

        ValidatableResponse response = courierApi.createCourier(courierDataWithoutLogin);

        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));

    }

    @DisplayName("Check courier can not be created without password and with login")
    @Test
    public void courierCanNotBeCreatedWithoutPassword(){

        CourierData courierDataWithoutLogin = new CourierData(courierData.getLogin(),null);
        CourierApi courierApi = new CourierApi();

        ValidatableResponse response = courierApi.createCourier(courierDataWithoutLogin);

        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));

    }
}
