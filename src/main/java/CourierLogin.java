
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierLogin {
    private static final String contentType = "Content-type";
    private static final String json = "application/json";
    private static final String COURIER_PATH ="https://qa-scooter.praktikum-services.ru/api/v1/courier/";
    private static final String COURIER_LOGIN_PATH = "https://qa-scooter.praktikum-services.ru/api/v1/courier/login/";

    @Step("Создание курьера")
    public boolean create(Courier courier){
        return given()
                .header(contentType, json)
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }
    @Step("Авторизация курьера")
    public int login(String courierPartData){
        return  given()
                .header(contentType, json)
                .body(courierPartData)
                .when()
                .post(COURIER_PATH + "login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }
    @Step("Удалить курьера {0}")
    public boolean delete(int id){
        return given()
                .header(contentType, json)
                .when()
                .delete(COURIER_PATH + id)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }

    @Step("Получить ответ о созданном курьере")
    public ValidatableResponse getCreateCourierResponse(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Получить login курьера в ответе")
    public ValidatableResponse getLoginCourierResponse(String login, String password){
        CourierAccount courierCredentials = new CourierAccount();
        return given()
                .header("Content-type", "application/json")
                .body(courierCredentials.account(login,password))
                .when()
                .post(COURIER_LOGIN_PATH)
                .then();
    }
}
