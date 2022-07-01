package com.itoolabs.index_page_tests;

public enum Locale {
    RU("© ITooLabs – Облачная АТС для операторов связи и провайдеров услуг"),
    EN("© ITooLabs – Cloud PBX for telecom providers"),
    MK("© ITooLabs – Облачната АТЦ за телекомуникациски оператори и провајдери");

    private final String assertionText;

    Locale(String assertionText) {
        this.assertionText = assertionText;
    }

    public String getAssertionText() {
        return assertionText;
    }
}
