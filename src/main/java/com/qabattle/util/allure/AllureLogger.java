package com.qabattle.util.allure;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Aleksei Stepanov
 */


public class AllureLogger {

    public static void saveTextLog(String attachName, String message) {
        attachText(attachName, message);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    private static String attachText(String attachName, String message) {
        if (message == null) {
            message = "null";
        }
        return message;
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] attachScreenshot(String name) {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}
