package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement pageName = $(byText("Ваши карты"));
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        pageName.shouldBe(Condition.visible);
    }

    public int getCardBalance(String id) {
        val text = cards.get(Integer.parseInt(id) - 1).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public MoneyTransferPage chooseCardToTopUpBalance(String id) {
        var card = cards.get(Integer.parseInt(id) - 1);
        card.$("[data-test-id=action-deposit]").click();
        return new MoneyTransferPage(id);
    }

}
