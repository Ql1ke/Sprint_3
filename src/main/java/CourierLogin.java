
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierLogin extends RestConfig {
    private static final String CONTENT_TYPE = "Content-type";
    private static final String JSON = "application/json";
    private static final String COURIER_PATH ="/api/v1/courier/";
    private static final String COURIER_LOGIN_PATH = "/api/v1/courier/login/";

    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .header(CONTENT_TYPE, JSON)
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();

    }
    @Step("Авторизация курьера")
    public ValidatableResponse login(String courierPartData){
        return  given()
                .spec(getBaseSpec())
                .header(CONTENT_TYPE, JSON)
                .body(courierPartData)
                .when()
                .post(COURIER_PATH + "login").then();
    }
    @Step("Удалить курьера {0}")
    public ValidatableResponse delete(int id){
        return given()
                .spec(getBaseSpec())
                .header(CONTENT_TYPE, JSON)
                .when()
                .delete(COURIER_PATH + id)
                .then();

    }

    @Step("Получить ответ о созданном курьере")
    public ValidatableResponse getCreateCourierResponse(Courier courier){
        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Получить login курьера в ответе")
    public ValidatableResponse getLoginCourierResponse(String login, String password){
        CourierAccount courierCredentials = new CourierAccount(login,password);
        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .body(courierCredentials.account())
                .when()
                .post(COURIER_LOGIN_PATH)
                .then();
    }
}
