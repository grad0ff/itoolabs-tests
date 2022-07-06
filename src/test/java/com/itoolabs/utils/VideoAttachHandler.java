package com.itoolabs.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class VideoAttachHandler {

    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat("mm:ss");
    private static long startTimestamp;
    private static long currentTestStartTimestamp;

    public static void setTestStartTimestamp() {
        currentTestStartTimestamp = new Date().getTime();
        if (startTimestamp == 0) {
            startTimestamp = currentTestStartTimestamp;
        }
    }

    @Attachment(value = "Log", type = "text/plain")
    public static String addLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

    @Attachment(value = "Screenshot ", type = "image/png", fileExtension = ".png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void addVideoAttach() {
        String offsetTime = getTimeOffset();
        addVideo(offsetTime);
    }

    private static String getTimeOffset() {
        long offset = currentTestStartTimestamp - startTimestamp;
        return timeFormatter.format(offset);
    }

    @Attachment(value = "Video [timecode ~{timestamp}]", type = "text/html", fileExtension = ".html")
    private static String addVideo(String timestamp) {
        String sessionId = getSessionId();
        URL videoUrl = getVideoUrl(sessionId);
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + videoUrl
                + "' type='video/mp4'></video></body></html>";
    }

    private static URL getVideoUrl(String sessionId) {
        String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}
