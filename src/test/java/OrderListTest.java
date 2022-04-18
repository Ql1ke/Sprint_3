import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    @Test
    @DisplayName("Получить список заказа. Список не пустой")
    public void getOrderListShouldNotNull() {
        OrderCard oc = new OrderCard();
        List<Integer> idList = oc.getOrders().assertThat().statusCode(200).extract().path("orders.id");
        Assert.assertFalse("Empty order list", idList.isEmpty());
    }
}
