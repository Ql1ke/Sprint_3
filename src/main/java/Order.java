
import java.util.List;

public class Order {

    public final String firstName;
    public final String lastName;
    public final String address;
    public final int metroStation;
    public final String  phone;
    public final int  rentTime;
    public final String  deliveryDate;
    public final String  comment;
    public final List<String> colours;

    public Order(String firstName, String lastName,
                 String address, int metroStation,
                 String phone, int rentTime, String deliveryDate,
                 String comment, List<String> colours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colours = colours;
    }
}
