package com.itoolabs.index_page_tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.HoverOptions.withOffset;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Tag("index_page")
@DisplayName("Тесты главной страницы itoolabs.com")
public class IndexPageTest extends TestBase {

    @Test
    @Description("Проверяется переход/возврат на главную страницу itoolabs.com при нажатии на логотип")
    @DisplayName("Тест ссылки в лого")
    void logoLinkTest() {
        step("Нажимаем на логотип",
                () -> $("a.logo").click());
    }

    @ParameterizedTest(name = "Проверка локали {argumentsWithNames}")
    @EnumSource(Locale.class)
    @Description("Проверяется возможность выбора локали")
    void logoLinkTest(Locale locale) {
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
        step("Проверяем что текущая локаль " + locale.name(),
                () -> $(".lang ." + localeName).shouldHave(attribute("class", localeName + " selected")));
    }

    @ParameterizedTest(name = "Проверка попапа на элементе {argumentsWithNames}")
    @EnumSource(HeadFeature.class)
    @Description("Проверяется появление попапа в хэдере при наведении на услугу")
    void headerFeaturesTest(HeadFeature headFeature) {
        SelenideElement headFeatureElement = $("#" + headFeature.name().toLowerCase());
        headFeatureElement.$("span").shouldNotBe(visible);
        Map<String, List<Integer>> offsetMap = Map.of(
                "left", List.of(-10, 0),
                "up", List.of(0, 10),
                "right", List.of(0, 10),
                "down", List.of(0, -10));
        if (!headFeatureElement.hover().$("span").isDisplayed()) {
            for (List<Integer> offset : offsetMap.values()) {
                headFeatureElement.hover(withOffset(offset.get(0), offset.get(1)));
                if (headFeatureElement.hover().$("span").isDisplayed()) break;
            }
        }
        headFeatureElement.hover().$("span").shouldHave(attribute("style", "opacity: 1;"));

    }
}
