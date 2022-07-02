package com.itoolabs.index_page_tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {


    @BeforeAll
    static void initTests() {
        Configuration.baseUrl = "https://itoolabs.com";
        SelenideLogger.addListener("allure", new AllureSelenide());
        setRemoteWebdriver();

    }

    @BeforeEach
    void prepareTest() {
        open("/");

    }


    @AfterAll
    static void finishTests() {
        closeWebDriver();
        clearBrowserCookies();
    }

    static void setRemoteWebdriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
}
