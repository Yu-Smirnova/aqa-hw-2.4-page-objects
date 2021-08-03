package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @AfterEach
    void correctBalances() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
        dashboardPage.correctBalances();
    }

    @Test
    public void shouldTransferMoneyBetweenOwnCardsFromFirstToSecond() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
        dashboardPage.TopUpCardBalance("1", 500);
        int expectedFirstCardBalance = 10_500;
        int expectedSecondCardBalance = 9_500;
        int actualFirstCardBalance = dashboardPage.getCardBalance("1");
        int actualSecondCardBalance = dashboardPage.getCardBalance("2");
        $(byText("Ваши карты")).shouldBe(Condition.visible);
        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    public void shouldTransferMoneyBetweenOwnCardsFromSecondToFirst() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
        dashboardPage.TopUpCardBalance("2", 500);
        int expectedFirstCardBalance = 9_500;
        int expectedSecondCardBalance = 10_500;
        int actualFirstCardBalance = dashboardPage.getCardBalance("1");
        int actualSecondCardBalance = dashboardPage.getCardBalance("2");
        $(byText("Ваши карты")).shouldBe(Condition.visible);
        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }
}

