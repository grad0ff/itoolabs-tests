package com.itoolabs.index_page_tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void initTests() {
        Configuration.baseUrl = "https://itoolabs.com";
        Configuration.browserPosition = "2000x0";
//        Configuration.holdBrowserOpen = true;
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
