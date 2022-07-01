package com.itoolabs.index_page_tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void initTests() {
        Configuration.baseUrl = "https://itoolabs.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserPosition = "-1500x0";
    }

    @BeforeEach
    void prepareTest() {
        open("/");
    }

    @AfterAll
    static void finishTests() {
        closeWebDriver();
    }
}
