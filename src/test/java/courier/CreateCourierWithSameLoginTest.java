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
import static org.hamcrest.CoreMatchers.is;

public class CreateCourierWithSameLoginTest {

    protected int courierId;
    protected CourierData courierData;

    @Before
    public void setUP(){
        courierData = getRandomCourier("Victor", "password", "Victor");
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.createCourier(courierData);
        response.log().all();

    }

    @After
    public void cleanUP(){
        //Удалять только если код ответа есть и сущесвутет этот курьер которого нужно удалять
        CourierData courierDataForLogin = new CourierData(courierData.getLogin(),courierData.getPassword());
        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.loginCourier(courierDataForLogin);
        courierId = response.extract().path("id");
        courierApi.deleteCourier(courierDataForLogin, String.valueOf(courierId));
    }




    @DisplayName("Check courier can not be created with the same login")
    @Test
    public void courierCanNotBeCreatedWithSameLoginTest(){

        CourierApi courierApi = new CourierApi();
        ValidatableResponse response = courierApi.createCourier(courierData);

        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body("message", is("Этот логин уже используется. Попробуйте другой."));

    }
}
