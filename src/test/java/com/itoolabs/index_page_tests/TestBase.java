package com.itoolabs.index_page_tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;
import static com.itoolabs.utils.VideoAttachHandler.addVideoAttach;
import static com.itoolabs.utils.VideoAttachHandler.setTestStartTimestamp;

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
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("windowSize", "1366x768");
        Configuration.baseUrl = "https://itoolabs.com";
        if (System.getProperty("remoteWebDriver", "true").equals("true")) {setRemoteWebdriver();}
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void prepareTest() {
        open("/");
        setTestStartTimestamp();
    }

    @AfterEach
    void finishTest() {

    }

//    @AfterAll
//    static void finishTests() {
//        closeWebDriver();
//        clearBrowserCookies();
//
//    }

    static void setRemoteWebdriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
}
