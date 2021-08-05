package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    ;

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String verificationCode;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String id;
        private String number;
        private int InitialBalance;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("1", "5559 0000 0000 0001", 10_000);
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("2", "5559 0000 0000 0002", 10_000);
    }

    public static CardInfo getCardInfo(String id) {
        if (id.equals(getFirstCardInfo().getId())) {
            return getFirstCardInfo();
        } else {
            return getSecondCardInfo();
        }
    }

}
