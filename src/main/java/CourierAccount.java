import io.qameta.allure.Step;

public class CourierAccount {
        @Step("Создание аккаунта")
        public String account(String login, String password) {
            return "{\"login\":\"" + login + "\","
                    + "\"password\":\"" + password +"\"}";
        }
    }


