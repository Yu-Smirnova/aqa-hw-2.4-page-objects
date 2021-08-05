package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import jdk.jfr.Name;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

@Name("Страница входа")
public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
