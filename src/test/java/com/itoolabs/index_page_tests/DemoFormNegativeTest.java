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
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

@Tag("demo_form_negative_test")
@Owner("a.gradov")
@Feature("Форма запроса Demo-версии")
@DisplayName("Негативные тесты формы запроса Demo-версии")
public class DemoFormNegativeTest extends TestBase {

    IndexPage indexPage = new IndexPage();
    static Faker faker = new Faker();

    @Step("Открываем форму")
    public void openDemoForm() {
        step("Нажимаем на демолайне 1 на кнопку получения Demo-версии",
                () -> $(".demoBuyButton.first .buyDemo").click());
    }

    @ParameterizedTest(name = "Тест поля Имя значением: {0}")
    @MethodSource("nameFieldDataStream")
    @Story("Пользователь хочет отправить форму с невалидными данными в поле Имя")
    @Description("Проверяются вывод предупреждения о необходимости ввода валидных данных в поле Имя и " +
            "невозможность отправки формы с невалидными данными")
    void nameFieldTest(String parameter, String value) {
        openDemoForm();
        String email = faker.internet().emailAddress();
        String company = faker.company().name();
        step(String.format("Вводим в поля E-mail и Компания валидные данные: %s, %s", email, company),
                () -> {
                    indexPage.demoForm.emailField.setValue(email).pressTab();
                    indexPage.demoForm.companyField.setValue(company).pressTab();
                });
        step("Вводим в поле Имя текст: " + parameter,
                () -> indexPage.demoForm.nameField.setValue(value).pressTab());
        step("Проверяем появление предупреждения справа от поля",
                () -> {
                    if (!indexPage.demoForm.nameField.parent().sibling(0).$(".err_txt").isDisplayed()) {
                        step("Если его нет, то проверяем появление предупреждения при наведении мыши на кнопку Отправить ",
                                () -> {
                                    indexPage.demoForm.submitButton.hover();
                                    indexPage.demoForm.requiredFieldsMessage.shouldHave(text("Имя"));
                                });
                    }
                });
        step("Проверяем что кнопка Отключить неактивна",
                () -> {
                    indexPage.demoForm.submitButton.hover();
//                    sleep(1000);
                    indexPage.demoForm.submitButton.shouldBe(disabled);
                });
    }

    static Stream<Arguments> nameFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("число", "123"),
                Arguments.of("спецсимволы", "$"),
                Arguments.of("null", null));
    }

    @ParameterizedTest(name = "Тест поля E-mail значением: {0}")
    @MethodSource("emailFieldDataStream")
    @Story("Пользователь вводит невалидные данные в поле E-mail")
    @Description("Проверяются вывод предупреждения о необходимости ввода валидных данных в поле E-mail и " +
            "невозможность отправки формы с невалидными данными")
    void emailFieldTest(String parameter, String value) {
        openDemoForm();
        String name = faker.name().firstName();
        String company = faker.company().name();
        step(String.format("Вводим в поля Имя и Компания валидные данные: %s, %s", name, company),
                () -> {
                    indexPage.demoForm.nameField.setValue(name).pressTab();
                    indexPage.demoForm.companyField.setValue(company).pressTab();
                });
        step("Вводим в поле E-mail текст: " + parameter,
                () -> indexPage.demoForm.emailField.setValue(value).pressTab());
        step("Проверяем появление предупреждения справа от поля",
                () -> {
                    if (!indexPage.demoForm.emailField.parent().sibling(0).$(".err_txt").isDisplayed()) {
                        step("Если его нет, то проверяем появление предупреждения при наведении мыши на кнопку Отправить ",
                                () -> {
                                    indexPage.demoForm.submitButton.hover();
                                    indexPage.demoForm.requiredFieldsMessage.shouldHave(text("Имя"));
                                });
                    }
                });
        step("Проверяем что кнопка Отключить неактивна",
                () -> {
                    indexPage.demoForm.submitButton.hover();
                    indexPage.demoForm.submitButton.shouldBe(disabled);
                });
    }

    static Stream<Arguments> emailFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("число", "123"),
                Arguments.of("спецсимволы", "$"),
                Arguments.of("null", null),
                Arguments.of("только @ в адресе", "@"),
                Arguments.of("адрес из чисел", "123@456.78"),
                Arguments.of("другой символ вместо @", "name#mail.dom"),
                Arguments.of("число в домене верхнего уровня", "name@mail.123"),
                Arguments.of("нет домена верхнего уровня", "name@mail."),
                Arguments.of("нет '.' и домена верхнего уровня", "name@mail"));
    }

    @ParameterizedTest(name = "Тест поля Компания значением: {0}")
    @MethodSource("companyFieldDataStream")
    @Story("Пользователь вводит невалидные данные в поле Компания")
    @Description("Проверяются вывод предупреждения о необходимости ввода валидных данных в поле Компания и " +
            "невозможность отправки формы с невалидными данными")
    void companyFieldTest(String parameter, String value) {
        openDemoForm();
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        step(String.format("Вводим в поля Имя и E-mail валидные данные: %s, %s", name, email),
                () -> {
                    indexPage.demoForm.nameField.setValue(name).pressTab();
                    indexPage.demoForm.emailField.setValue(email).pressTab();
                });
        step("Вводим в поле Компания текст: " + parameter,
                () -> indexPage.demoForm.companyField.setValue(value).pressTab());
        step("Проверяем появление предупреждения справа от поля",
                () -> {
                    if (!indexPage.demoForm.companyField.parent().sibling(0).$(".err_txt").isDisplayed()) {
                        step("Если его нет, то проверяем появление предупреждения при наведении мыши на кнопку Отправить ",
                                () -> {
                                    indexPage.demoForm.submitButton.hover();
                                    indexPage.demoForm.requiredFieldsMessage.shouldHave(text("Имя"));
                                });
                    }
                });
        step("Проверяем что кнопка Отключить неактивна",
                () -> {
                    indexPage.demoForm.submitButton.hover();
                    indexPage.demoForm.submitButton.shouldBe(disabled);
                });
    }

    static Stream<Arguments> companyFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("null", null));
    }

    @ParameterizedTest(name = "Тест поля Телефон значением: {0}")
    @MethodSource("phoneFieldDataStream")
    @Story("Пользователь вводит невалидные данные в поле Телефон")
    @Description("Проверяются вывод предупреждения о необходимости ввода валидных данных в поле Телефон и " +
            "невозможность отправки формы с невалидными данными")
    void phoneFieldTest(String parameter, String value) {
        openDemoForm();
        step("Вводим в поле Телефон текст: " + parameter,
                () -> indexPage.demoForm.phoneField.setValue(value).pressTab());
        sleep(3000);
        step("Проверяем появление предупреждения справа от поля",
                () -> indexPage.demoForm.phoneField.parent().sibling(0).$(".err_txt").shouldBe(visible));
    }

    static Stream<Arguments> phoneFieldDataStream() {
        return Stream.of(
                Arguments.of("пустая строка", ""),
                Arguments.of("пробелы", "   "),
                Arguments.of("null", null),
                Arguments.of("спецсимволы", "$"),
                Arguments.of("цифр в номере < 10 ", "123"),
                Arguments.of("цифр в номере > 10 ", "1234567890123"),
                Arguments.of("некорректный формат", "()1234567890"));
    }
}
