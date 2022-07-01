package com.itoolabs.pages.page_components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/*
 * Класс компонента формы запроса Demo-версии продукта главной страницы
 */
public class DemoForm {

    public SelenideElement nameField = $("#conlultForm #name");
    public SelenideElement email = $("#conlultForm #email");
    public SelenideElement phoneField = $("#conlultForm #phone");
    public SelenideElement companyField = $("#conlultForm #company");
    public SelenideElement commentsField = $("#conlultForm #comments");
    public SelenideElement submitButton = $("#formSendBTN");
    public SelenideElement requiredFieldsMessage = $("#formMsg");
}
