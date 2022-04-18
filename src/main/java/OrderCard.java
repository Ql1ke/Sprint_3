import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrderCard extends RestConfig {

    private static final String ORDERS_PATH = "/api/v1/orders/";

    public OrderCard(){

    }

    @Step("Получить ответ от созданного заказа")
    public ValidatableResponse createOrder(ArrayList<Order> cards){
        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .body(cards)
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Получить ответ списка заказа")
    public ValidatableResponse getOrders(){
        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .get(ORDERS_PATH)
                .then();
    }
}

