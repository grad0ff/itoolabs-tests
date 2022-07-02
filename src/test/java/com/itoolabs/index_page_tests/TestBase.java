package com.itoolabs.index_page_tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {


    @BeforeAll
    static void initTests() {
        Configuration.baseUrl = "https://itoolabs.com";
        Configuration.browserPosition = "1700x0";
        Configuration.browserSize= "1920x1080";
//        Configuration.holdBrowserOpen = true;
        SelenideLogger.addListener("allure", new AllureSelenide());

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
}
