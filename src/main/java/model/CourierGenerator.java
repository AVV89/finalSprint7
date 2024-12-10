package model;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    @Step("Generate random courier")
    public static CourierData getRandomCourier(String loginParam, String passwordParam,
                                               String firstNameParam){
        String login = loginParam + RandomStringUtils.randomAlphabetic(5);
        String password = passwordParam + RandomStringUtils.randomAlphabetic(5);
        String firstName = firstNameParam + RandomStringUtils.randomAlphabetic(5);

        return new CourierData(login, password, firstName);
    }

}
