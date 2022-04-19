import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTest {

        private ArrayList<Order> cards;
        OrderCard orderCard;

        @Before
        public void setUp(){
            cards = new ArrayList<>();
            orderCard = new OrderCard();
        }

        @Test
        @DisplayName("Создание заказа с цветом BLACK. Код ответа 201")
        public void creatingOrderColorBlackShouldReturnCode201(){
            cards.add(new Order("Naruto",
                    "Uchiha",
                    "Konoha, 142 apt.",
                    4,
                    "+7 800 355 35 35",
                    5,
                    "2020-06-06",
                    "Saske, come back to Konoha",
                    List.of("BLACK")));
            ValidatableResponse createOrderResponse = orderCard.createOrder(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа с цветом GREY. Код ответа 201")
        public void creatingOrderColorGreyShouldReturnCode201(){
            cards.add(new Order("Naruto",
                    "Uchiha",
                    "Konoha, 142 apt.",
                    4,
                    "+7 800 355 35 35",
                    5,
                    "2020-06-06",
                    "Saske, come back to Konoha",
                    List.of("GREY")));
            ValidatableResponse createOrderResponse = orderCard.createOrder(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа с цветом BLACK и GRAY. Код ответа 201")
        public void creatingOrderColorGreyAndBlackShouldReturnCode201(){
            cards.add(new Order("Naruto",
                    "Uchiha",
                    "Konoha, 142 apt.",
                    4,
                    "+7 800 355 35 35",
                    5,
                    "2020-06-06",
                    "Saske, come back to Konoha",
                    List.of("BLACK")));
            cards.add(new Order("Naruto",
                    "Uchiha",
                    "Konoha, 142 apt.",
                    4,
                    "+7 800 355 35 35",
                    5,
                    "2020-06-06",
                    "Saske, come back to Konoha",
                    List.of("GRAY")));
            ValidatableResponse createOrderResponse = orderCard.createOrder(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа без указания цвета. Код ответа 201")
        public void creatingOrderNoColorShouldReturnCode201(){
            cards.add(new Order("Naruto",
                    "Uchiha",
                    "Konoha, 142 apt.",
                    4,
                    "+7 800 355 35 35",
                    5,
                    "2020-06-06",
                    "Saske, come back to Konoha",
                    null));
            ValidatableResponse createOrderResponse = orderCard.createOrder(cards);
            createOrderResponse.assertThat().statusCode(201);
        }

        @Test
        @DisplayName("Создание заказа. Тело ответа содержит track")
        public void creatingOrderResponseShouldHaveTrack(){
            ValidatableResponse createOrderResponse = orderCard.createOrder(cards);
            createOrderResponse.assertThat().statusCode(201).body("track",notNullValue());
        }
    }

