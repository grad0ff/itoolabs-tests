package com.itoolabs.index_page_tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import com.itoolabs.pages.IndexPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.HoverOptions.withOffset;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("index_page_positive_test")
@Owner("a.gradov")
@Link(name = "itoolabs.com", url = "https://itoolabs.com/")
@Feature("Главная страница сайта")
@DisplayName("Позитивные тесты главной страницы сайта itoolabs.com")
public class IndexPageTest extends TestBase {

    IndexPage indexPage = new IndexPage();
    Faker faker = new Faker();

    @Test
    @Story("Пользователь переходит на главную страницу")
    @Description("Проверяется переход на главную страницу itoolabs.com при нажатии на логотип")
    @DisplayName("Тест ссылки в логотипе")
    void logoLinkTest() {
        step("Открываем главную страницу в фоне в новой вкладке",
                () -> $("a.logo").sendKeys(Keys.CONTROL, Keys.ENTER));
        step("Переходим на новую вкладку",
                () -> switchTo().window(1));
        step("Проверяем что открылась главная страница",
                () -> assertThat(WebDriverRunner.url()).isEqualTo(
                        Configuration.baseUrl + String.format("/%s/", $(".lang .selected").getOwnText())
                )
        );
    }

    @ParameterizedTest(name = "Тест выбора текущей локали: {0}")
    @EnumSource(Locale.class)
    @Story("Пользователь меняет язык интерфейса")
    @Description("Проверяется возможность выбора локали и соответствие текста страницы выбранному языку")
    void setLocaleTest(Locale locale) {
        String localeName = locale.name().toLowerCase();
        step("Выставляем исходную локаль",
                () -> {
                    if (localeName.equals("ru")) {
                        $(".lang .en").click();
                    } else {
                        $(".lang .ru").click();
                    }
                });
        step("Нажимаем на локаль с названием " + locale.name(),
                () -> $(".lang ." + localeName).click());
        step("Проверяем что локаль выставилась корректно",
                () -> $(".lang ." + localeName).shouldHave(attribute("class", localeName + " selected")));
        step("Проверяем что поменялся язык текста на странице",
                () -> $(".copyright").shouldHave(text(locale.getAssertionText())));
    }

    @ParameterizedTest(name = "Тест появления формы по клику на элементе: {0}")
    @EnumSource(HeadFeature.class)
    @Story("Пользователь выбрал в хэдере интересующую его тему ")
    @Description("Проверяется появление модального окна с формой запроса Demo-версии продукта по клику по услуге в хэдере")
    void headerFeaturesTest(HeadFeature headFeature) {
        SelenideElement headFeatureElement = $("#" + headFeature.name().toLowerCase());
        step("Проверяем что у элемента изначально не виден попап",
                () -> headFeatureElement.$("span").shouldNotBe(visible));
        step("Наводим мышь на элемент",
                () -> {
                    // Задаем параметры смещения мыши, на случай если попап не покажется при наведении на элемент
                    Map<String, List<Integer>> offsetMap = Map.of(
                            "left", List.of(-10, 0),
                            "up", List.of(0, 10),
                            "right", List.of(0, 10),
                            "down", List.of(0, -10));
                    // Если попап не покажется, проводим поиск со смещением мыши
                    if (!headFeatureElement.hover().$("span").isDisplayed()) {
                        for (List<Integer> offset : offsetMap.values()) {
                            headFeatureElement.hover(withOffset(offset.get(0), offset.get(1)));
                            if (headFeatureElement.hover().$("span").isDisplayed()) break;
                        }
                    }
                });
        step("Проверяем что у элемента появился попап ",
                () -> headFeatureElement.hover().$("span").shouldHave(attribute("style", "opacity: 1;"))
        );
        step("Кликаем на элемент",
                (Allure.ThrowableRunnableVoid) headFeatureElement::click);
        step("Проверяем что появилась окно с формой ",
                () -> $("#formPopUp").shouldBe(visible));
    }

    @ParameterizedTest(name = "Тест появления формы по клику по кнопке на демолайне {index}")
    @ValueSource(strings = {"first", "second"})
    @Story("Пользователь решил приобрести Demo-версии продукта")
    @Description("Проверяется появление модального окна с формой запроса Demo-версии продукта по клику по кнопке на демолайне")
    void demoLineLinksTest(String linkNum) {
        step("Нажимаем на кнопку получения Demo-версии",
                () -> $(String.format(".demoBuyButton.%s .buyDemo", linkNum)).click());
        step("Проверяем что форма появилась",
                () -> $("#formPopUp").shouldBe(visible));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Пользователь заполняет форму для получения Demo-версии")
    @Description("Проверяется корректность заполнения всех полей формы запроса Demo-версии продукта")
    @DisplayName("Тест всех полей формы")
    void demoFormFieldsTest() {
        step("Нажимаем на демолайне на кнопку получения Demo-версии ",
                () -> $(".demoBuyButton.first .buyDemo").click());
        step("Заполняем поле Имя",
                () -> indexPage.demoForm.nameField.setValue(faker.name().firstName()));
        step("Заполняем поле E-mail",
                () -> indexPage.demoForm.emailField.setValue(faker.internet().emailAddress()));
        step("Заполняем поле Телефон",
                () -> indexPage.demoForm.phoneField.setValue(faker.phoneNumber().subscriberNumber(10)));
        step("Заполняем поле Компания",
                () -> indexPage.demoForm.companyField.setValue(faker.company().name()));
        step("Заполняем поле Комментарий",
                () -> indexPage.demoForm.commentsField.setValue(faker.chuckNorris().fact()));
        step("Проверяем что кнопка отправки формы активна",
                () -> indexPage.demoForm.submitButton.hover().shouldBe(enabled));
        step("Проверяем что нет сообщения о необходимости заполнения обязательный полей",
                () -> indexPage.demoForm.requiredFieldsMessage.shouldNotBe(visible));
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Пользователь пролистывает ленту новостей")
    @Description("Проверяется корректность работы кнопок слайдера ленты новостей")
    @DisplayName("Тест работы кнопок слайдера")
    void newsSliderTest() {
        int newsCount = $$(".newSlider li").size();
        step("Пролистываем ленту до первой новости",
                () -> {
                    for (int i = 0; i < newsCount; i++) {
                        $(".news a.bx-prev").click();
                        sleep(500);
                    }
                    assertThat($(".newSlider li").isDisplayed()).isTrue();
                });
        step("Проверяем что отображается первая новость из списка",
                () -> $(".newSlider li").shouldBe(visible));
        step("Пролистываем ленту до последней новости",
                () -> {
                    for (int i = 0; i < newsCount; i++) {
                        $(".news a.bx-next").click();
                        sleep(500);
                    }
                    $(".newSlider").lastChild().shouldHave(visible);
                });
        step("Проверяем что отображается последняя новость из списка",
                () -> assertThat($(".newSlider").lastChild().isDisplayed()).isTrue());

    }
}
