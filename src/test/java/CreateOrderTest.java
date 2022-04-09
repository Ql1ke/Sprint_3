import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTest {

        private ArrayList<OrderCard> cards;
        OrderCard orderCard;

        @Before
        public void setUp(){
            cards = new ArrayList<OrderCard>();
            orderCard = new OrderCard();
        }

        @Test
        @DisplayName("Создание заказа с цветом BLACK. Код ответа 201")
        public void creatingOrderColorBlackShouldReturnCode201(){
            cards.add(new OrderCard("BLACK"));
            ValidatableResponse createOrderResponse = orderCard.getCreateOrderResponse(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа с цветом GREY. Код ответа 201")
        public void creatingOrderColorGreyShouldReturnCode201(){
            cards.add(new OrderCard("GREY"));
            ValidatableResponse createOrderResponse = orderCard.getCreateOrderResponse(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа с цветом BLACK и GRAY. Код ответа 201")
        public void creatingOrderColorGreyAndBlackShouldReturnCode201(){
            cards.add(new OrderCard("GREY"));
            cards.add((new OrderCard("BLACK")));
            ValidatableResponse createOrderResponse = orderCard.getCreateOrderResponse(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа без указания цвета. Код ответа 201")
        public void creatingOrderNoColorShouldReturnCode201(){
            cards.add(new OrderCard(""));
            ValidatableResponse createOrderResponse = orderCard.getCreateOrderResponse(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа. Тело ответа содержит track")
        public void creatingOrderResponseShouldHaveTrack(){
            ValidatableResponse createOrderResponse = orderCard.getCreateOrderResponse(cards);
            createOrderResponse.assertThat().statusCode(201).body("track",notNullValue());
        }
    }

