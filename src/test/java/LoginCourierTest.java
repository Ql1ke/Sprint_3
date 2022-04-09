import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginCourierTest {

        private CourierLogin courierClient;
        private int courierID;
        private CourierAccount courierAccount = new CourierAccount();
        Courier courier;

        @Before
        public void setUp() {
            courierClient = new CourierLogin();
            courier = Courier.getRandom();
            courierClient.create(courier);
        }

        @After
        public void deleteCourier(){
            courierID = courierClient.login(courierAccount.account(courier.login,courier.password));
            if (courierID > 0) {
                courierClient.delete(courierID);
            }
        }

        @Test
        @DisplayName("Авторизация курьера")
        public void loginCourierShouldAuthorization(){
            ValidatableResponse getLoginCourierResponse = courierClient.getLoginCourierResponse(courier.login, courier.password);
            getLoginCourierResponse.assertThat().statusCode(200);
        }

        @Test
        @DisplayName("Авторизация курьера без обязательного поля. Код ответа 400")
        public void loginCourierForAuthorizationNeedAllFieldsReturnErrorCode(){
            ValidatableResponse getLoginCourierResponse = courierClient.getLoginCourierResponse(courier.login, "");
            getLoginCourierResponse.assertThat().statusCode(400);
        }

        @Test
        @DisplayName("Авторизация курьера с пропущенным пользователем, Код ответа 404")
        public void loginCourierBadRequestShouldReturnErrorCode() {
            ValidatableResponse getLoginCourierResponse = courierClient.getLoginCourierResponse("ADsd", courier.password);
            getLoginCourierResponse.assertThat().statusCode(404);
        }

        @Test
        @DisplayName("Авторизация курьера без обязательного поля. Сообщение об ошибке")
        public void loginCourierForAuthorizationNeedAllFieldsReturnErrorMessage(){
            ValidatableResponse getLoginCourierResponse = courierClient.getLoginCourierResponse(courier.login, "");
            getLoginCourierResponse.statusCode(400);
            getLoginCourierResponse.assertThat().body("message",equalTo("Недостаточно данных для входа"));
        }

        @Test
        @DisplayName("Авторизация курьера с пропущенным пользователем. Сообщение об ошибке")
        public void loginCourierBadRequestShouldReturnErrorMessage() {
            ValidatableResponse getLoginCourierResponse = courierClient.getLoginCourierResponse("Asdsd", courier.password);
            getLoginCourierResponse.statusCode(404);
            getLoginCourierResponse.assertThat().body("message", equalTo("Учетная запись не найдена"));
        }

        @Test
        @DisplayName("Авторизация курьера. Должен вернуться правильный ID")
        public void loginCourierShouldAuthorizationShouldReturnID(){
            courierID = courierClient.login(courierAccount.account(courier.login,courier.password));
            ValidatableResponse getLoginCourierResponse = courierClient.getLoginCourierResponse(courier.login, courier.password);
            getLoginCourierResponse.statusCode(200);
            getLoginCourierResponse.assertThat().body("id",equalTo(courierID));
        }
    }


