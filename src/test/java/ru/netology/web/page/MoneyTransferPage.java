package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement pageName = $(byText("Пополнение карты"));
    private SelenideElement amountToTransfer = $("[data-test-id=amount] input");
    private SelenideElement cardTransferFrom = $("[data-test-id=from] input");
    private SelenideElement continueButton = $("[data-test-id=action-transfer]");

    public MoneyTransferPage(String id) {
        pageName.shouldBe(Condition.visible);
    }


    public DashboardPage topUpCardBalance(int amount, String cardIdFrom) {
        amountToTransfer.setValue(String.valueOf(amount));
        cardTransferFrom.setValue(DataHelper.getCardInfo(cardIdFrom).getNumber());
        continueButton.click();
        return new DashboardPage();
    }
}
