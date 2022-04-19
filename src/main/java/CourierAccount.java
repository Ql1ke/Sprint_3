import com.google.gson.Gson;
import io.qameta.allure.Step;

public class CourierAccount {

    private final String login;
    private final String password;

    public CourierAccount(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Step("Создание аккаунта")
    public String account() {
        Gson gson = new Gson();
        return gson.toJson(this);

    }
}


