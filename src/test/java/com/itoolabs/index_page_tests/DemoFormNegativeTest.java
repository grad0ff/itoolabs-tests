package com.itoolabs.index_page_tests;

import com.github.javafaker.Faker;
import com.itoolabs.pages.IndexPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Tag("demo_form_negative_test")
@Owner("a.gradov")
@Feature("Работа с формой запроса Demo-версии")
@DisplayName("Негативные тесты формы запроса Demo-версии")
public class DemoFormNegativeTest extends TestBase {

    IndexPage indexPage = new IndexPage();
    static Faker faker = new Faker();

    @Step("Открываем форму")
    public void openDemoForm() {
        step("Нажимаем на демолайне 1 на кнопку получения Demo-версии",
                () -> $(".demoBuyButton.first .buyDemo").click());
    }

    @ParameterizedTest
    @MethodSource("nameFieldDataStream")
    @Story("Пользователь вводит невалидные данные в поле Имя")
    @Description("Проверяется вывод предупреждения о необходимости ввода валидных данных в поле Имя")
    @DisplayName("Тест поля Имя: ")
    void nameFieldTest(String parameter, String value) {
        openDemoForm();
        indexPage.demoForm.setName(parameter, value);
        step("Проверяем вывод предупреждения справа от поля",
                () -> {
                    if (!indexPage.demoForm.nameField.parent().sibling(0).$(".err_txt").isDisplayed()) {
                        step("Если его нет, то проверяем вывод предупреждения при наведении мыши на кнопку Отправить ",
                                () -> {
                                    indexPage.demoForm.submitButton.hover();
                                    indexPage.demoForm.requiredFieldsMessage.shouldHave(text("Имя"));
                                });
                    }
                });
    }

    static Stream<Arguments> nameFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("число", "123"),
                Arguments.of("спецсимволы", "$"),
                Arguments.of("Null", null));
    }

    @ParameterizedTest
    @MethodSource("emailFieldDataStream")
    @Story("Пользователь вводит невалидные данные в поле E-mail")
    @Description("Проверяется вывод предупреждения о необходимости ввода валидных данных в поле E-mail")
    @DisplayName("Тест поля E-mail: ")
    void emailFieldTest(String parameter, String value) {
        openDemoForm();
        indexPage.demoForm.setEmail(parameter, value);
        step("Проверяем вывод предупреждения справа от поля",
                () -> {
                    if (!indexPage.demoForm.emailField.parent().sibling(0).$(".err_txt").isDisplayed()) {
                        step("Если его нет, то проверяем вывод предупреждения при наведении мыши на кнопку Отправить ",
                                () -> {
                                    indexPage.demoForm.submitButton.hover();
                                    indexPage.demoForm.requiredFieldsMessage.shouldHave(text("Имя"));
                                });
                    }
                });
    }

    static Stream<Arguments> emailFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("число", "123"),
                Arguments.of("спецсимволы", "$"),
                Arguments.of("Null", null),
                Arguments.of("только @ в адресе", "@"),
                Arguments.of("адрес из чисел", "123@456.78"),
                Arguments.of("другой символ вместо @", "name#mail.dom"),
                Arguments.of("число в домене верхнего уровня", "name@mail.123"),
                Arguments.of("нет домена верхнего уровня", "name@mail."),
                Arguments.of("нет '.' и домена верхнего уровня", "name@mail"));
    }

    @ParameterizedTest
    @MethodSource("companyFieldDataStream")
    @Story("Пользователь вводит невалидные данные в поле Компания")
    @Description("Проверяется вывод предупреждения о необходимости ввода валидных данных в поле Компания")
    @DisplayName("Тест поля Компания: ")
    void companyFieldTest(String parameter, String value) {
        openDemoForm();
        indexPage.demoForm.setCompany(parameter, value);
        step("Проверяем вывод предупреждения справа от поля",
                () -> {
                    if (!indexPage.demoForm.companyField.parent().sibling(0).$(".err_txt").isDisplayed()) {
                        step("Если его нет, то проверяем вывод предупреждения при наведении мыши на кнопку Отправить ",
                                () -> {
                                    indexPage.demoForm.submitButton.hover();
                                    indexPage.demoForm.requiredFieldsMessage.shouldHave(text("Имя"));
                                });
                    }
                });
    }

    static Stream<Arguments> companyFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("Null", null));
    }

    @ParameterizedTest
    @MethodSource("phoneFieldDataStream")
    @Story("Пользователь вводит невалидные данные в поле Телефон")
    @Description("Проверяется вывод предупреждения о необходимости ввода валидных данных в поле Телефон")
    @DisplayName("Тест поля Телефон: ")
    void phoneFieldTest(String parameter, String value) {
        openDemoForm();
        indexPage.demoForm.setPhone(parameter, value);
        step("Проверяем вывод предупреждения справа от поля",
                () -> indexPage.demoForm.phoneField.parent().sibling(0).$(".err_txt").shouldBe(visible));
    }

    static Stream<Arguments> phoneFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("Null", null),
                Arguments.of("спецсимволы", "$"),
                Arguments.of("цифр в номере < 10 ", "123"),
                Arguments.of("цифр в номере > 10 ", "1234567890123"),
                Arguments.of("некорректный формат", "()1234567890"));
    }

    @ParameterizedTest
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("requiredFieldDataStream")
    @Story("Пользователь пытается отправить форму с невалидными данными")
    @Description("Проверяется невозможность отправки формы с невалидными данными в полях Имя, E-mail, Компания")
    @DisplayName("Тест отправки формы: ")
    void sendInvalidFormTest(String name, String email, String company) {
        openDemoForm();
        String nameValue = name.equals("ПРОБЕЛЫ") ? "   " : name;
        String emailValue = email.equals("ПРОБЕЛЫ") ? "   " : email;
        String companyValue = company.equals("ПРОБЕЛЫ") ? "   " : company;
        indexPage.demoForm
                .setName(name, nameValue)
                .setEmail(email, emailValue)
                .setCompany(company, companyValue);
        step("Проверяем что кнопка Отключить неактивна",
                () -> indexPage.demoForm.submitButton.hover().shouldBe(disabled));
    }

    static Stream<Arguments> requiredFieldDataStream() {
        return Stream.of(
                Arguments.of("ПРОБЕЛЫ", faker.internet().emailAddress(), faker.company().name()),
                Arguments.of(faker.name().firstName(), "ПРОБЕЛЫ", faker.company().name()),
                Arguments.of(faker.name().firstName(), faker.internet().emailAddress(), "ПРОБЕЛЫ"));
    }
}
