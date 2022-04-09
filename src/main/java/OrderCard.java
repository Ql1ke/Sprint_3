import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrderCard {
    private String color;
    private static final String ORDERS_PATH = "https://qa-scooter.praktikum-services.ru/api/v1/orders/";

    public OrderCard(){

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public OrderCard(String color) {
        this.color = color;
    }

    @Step("Получить данные для заказа")
    public String orderCredentials(ArrayList<OrderCard> color){
        return "{\"firstName\":\"" + "English" + "\","
                + "\"lastName\":\"" + "Man" +"\","
                + "\"addres\":\"" + "Moscow, Lenina str." +"\","
                + "\"metroStation\":\""+ 5 + "\","
                + "\"phone\":\"" + "+7 911 123 22 33" + "\","
                + "\"rentTime\":\"" + 6 + "\","
                + "\"deliveryDate\":\"" + "2021-06-06" + "\","
                + "\"comment\":\"" + "gogogo" +"\","
                + "\"comment\":\"" + color + "\"}";
    }

    @Step("Получить ответ от созданного заказа")
    public ValidatableResponse getCreateOrderResponse (ArrayList<OrderCard> card){
        return given()
                .header("Content-type", "application/json")
                .body(card)
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Получить ответ списка заказа")
    public ValidatableResponse getOrderListResponse (){
        return given()
                .header("Content-type", "application/json")
                .get(ORDERS_PATH)
                .then();
    }
}

