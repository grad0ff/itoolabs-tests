package com.itoolabs.pages.page_components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/*
 * Класс компонента формы запроса Demo-версии продукта главной страницы
 */
public class DemoForm {

    public SelenideElement nameField = $("#conlultForm #name");
    public SelenideElement emailField = $("#conlultForm #email");
    public SelenideElement phoneField = $("#conlultForm #phone");
    public SelenideElement companyField = $("#conlultForm #company");
    public SelenideElement commentsField = $("#conlultForm #comments");
    public SelenideElement submitButton = $("#formSendBTN");
    public SelenideElement requiredFieldsMessage = $("#formMsg");

    @Step("Вводим в поле Имя текст: {parameter}")
    public DemoForm setName(String parameter, String value) {
        nameField.setValue(value).pressTab();
        return this;
    }

    @Step("Вводим в поле E-mail текст: {parameter}")
    public DemoForm setEmail(String parameter, String value) {
        emailField.setValue(value).pressTab();
        return this;
    }

    @Step("Вводим в поле Телефон текст: {parameter}")
    public DemoForm setPhone(String parameter, String value) {
        phoneField.setValue(value).pressTab();
        return this;
    }

    @Step("Вводим в поле Компания текст: {parameter}")
    public DemoForm setCompany(String parameter, String value) {
        companyField.setValue(value).pressTab();
        return this;
    }

    @Step("Вводим в поле текст: {parameter}")
    public DemoForm setComments(String parameter, String value) {
        commentsField.setValue(value).pressTab();
        return this;
    }
}
