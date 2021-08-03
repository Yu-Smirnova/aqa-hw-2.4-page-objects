package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
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

    public void TopUpCardBalance(String id, int amount) {
        var card = cards.get(Integer.parseInt(id) - 1);
        card.$("[data-test-id=action-deposit]").click();
        $("[data-test-id=amount] input").setValue(String.valueOf(amount));
        $("[data-test-id=from] input").setValue(DataHelper.findAnotherCardByThisCardId(id).getNumber());
        $("[data-test-id=action-transfer]").click();
    }

    public void correctBalances() {
        if (getCardBalance("1") > 10_000) {
            TopUpCardBalance("2", getCardBalance("1") - 10_000);
        } else {
            TopUpCardBalance("1", getCardBalance("2") - 10_000);
        }
    }
}
