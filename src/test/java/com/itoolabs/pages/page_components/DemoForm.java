package com.itoolabs.pages.page_components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;

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

    public DemoForm fillnameField(Object object) {
        nameField.sendKeys((CharSequence) object);
        return this;
    }


}
