package com.itoolabs.index_page_tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.*;
import static com.itoolabs.VideoAttachHandler.addVideoAttach;

public class TestBase {

    @RegisterExtension
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            TestWatcher.super.testFailed(context, cause);
            addVideoAttach();
        }
    };

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

    @AfterEach
    void finishTest() {

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
