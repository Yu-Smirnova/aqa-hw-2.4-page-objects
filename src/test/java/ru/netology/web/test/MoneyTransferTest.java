package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @AfterEach
    void correctBalances() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
        if (dashboardPage.getCardBalance("1") != dashboardPage.getCardBalance("2")) {
            if (dashboardPage.getCardBalance("1") > 10_000) {
                int amountToCorrect = dashboardPage.getCardBalance("1") - 10_000;
                var moneyTransferPage = dashboardPage.chooseCardToTopUpBalance("2");
                moneyTransferPage.topUpCardBalance(amountToCorrect, "1");
            } else {
                int amountToCorrect = dashboardPage.getCardBalance("2") - 10_000;
                var moneyTransferPage = dashboardPage.chooseCardToTopUpBalance("1");
                moneyTransferPage.topUpCardBalance(amountToCorrect, "2");
            }
        }
    }

    @Test
    public void shouldTransferMoneyBetweenOwnCardsFromFirstToSecond() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
        var moneyTransferPage = dashboardPage.chooseCardToTopUpBalance("1");
        moneyTransferPage.topUpCardBalance(500, "2");
        int expectedFirstCardBalance = 10_500;
        int expectedSecondCardBalance = 9_500;
        int actualFirstCardBalance = dashboardPage.getCardBalance("1");
        int actualSecondCardBalance = dashboardPage.getCardBalance("2");
        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    public void shouldTransferMoneyBetweenOwnCardsFromSecondToFirst() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
        var moneyTransferPage = dashboardPage.chooseCardToTopUpBalance("2");
        moneyTransferPage.topUpCardBalance(500, "1");
        int expectedFirstCardBalance = 9_500;
        int expectedSecondCardBalance = 10_500;
        int actualFirstCardBalance = dashboardPage.getCardBalance("1");
        int actualSecondCardBalance = dashboardPage.getCardBalance("2");
        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }
}

