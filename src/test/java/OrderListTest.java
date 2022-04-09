import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    OrderCard card = new OrderCard();

    @Test
    @DisplayName("Получить список заказа. Список не пустой")
    public void getOrderListShouldNotNull() {
        card.getOrderListResponse().statusCode(200);
        card.getOrderListResponse().assertThat().body("orders",notNullValue());
    }
}
