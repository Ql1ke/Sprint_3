import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreatingCourierTest {

    private CourierLogin courierClient;
    private int courierID;
    //        private CourierAccount courierAccount = new CourierAccount(courier.login,courier.password);
    private Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierLogin();
        courier = Courier.getRandom();
    }

    @After
    public void deleteCourier() {
        if (courier.login.length() != 0 && courier.password.length() != 0) {
            CourierAccount courierAccount = new CourierAccount(courier.login, courier.password);
            courierID = courierClient.login(courierAccount.account()).assertThat().extract().path("id");
            courierClient.delete(courierID).assertThat().statusCode(200);
        }
    }

    @Test
    @DisplayName("Создать курьера")
    public void creatingCourierShouldCreatedReturnTrue() {
        ValidatableResponse isCreated = courierClient.create(courier);
        isCreated.assertThat().statusCode(201);
    }

    @Test
    @DisplayName("Создать 2-х одинаковых курьеров, должен вернуться код 409")
    public void creatingTwoIdenticalCouriersCannotCreatedShouldReturnCode409() {
        courierClient.create(courier);
        ValidatableResponse createCourierResponse = courierClient.getCreateCourierResponse(courier);
        createCourierResponse.assertThat();
        createCourierResponse.statusCode(409);
    }

    @Test
    @DisplayName("Создание курьера без одного из параметров, должен вернуться код 400")
    public void creatingCourierWithoutRequiredFieldCreationErrorShouldReturnCode400() {
        courier.password = "";
        ValidatableResponse createCourierResponse = courierClient.getCreateCourierResponse(courier);
        createCourierResponse.assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера, должен вернуться код 201")
    public void creatingCourierRequestShouldReturnsСode201() {
        ValidatableResponse createCourierResponse = courierClient.getCreateCourierResponse(courier);
        createCourierResponse.assertThat().statusCode(201);
    }

    @Test
    @DisplayName("Создать курьера, должно вернуться ok:true")
    public void creatingCourierRequestShouldReturnCorrectText() {
        ValidatableResponse createCourierResponse = courierClient.getCreateCourierResponse(courier);
        createCourierResponse.statusCode(201);
        createCourierResponse.assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создать курьера без параметров, должно вернуться сообщение об ошибке")
    public void creatingCourierRequestShouldReturnCorrectErrorText() {
        courier.password = "";
        ValidatableResponse createCourierResponse = courierClient.getCreateCourierResponse(courier);
        createCourierResponse.statusCode(400);
        createCourierResponse.assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создать 2-х курьеров, должно вернуться сообщение об ошибке")
    public void creatingDoubleCourierRequestShouldReturnCorrectResponseErrorText() {
        courierClient.create(courier);
        ValidatableResponse createCourierResponse = courierClient.getCreateCourierResponse(courier);
        createCourierResponse.statusCode(409);
        createCourierResponse.assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}


