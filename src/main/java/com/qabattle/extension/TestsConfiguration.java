package com.qabattle.extension;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.qabattle.configuration.WebConfig;
import com.qabattle.util.allure.AllureEnvironment;
import com.qabattle.util.allure.AllureLogger;
import com.qabattle.util.annotation.AnnotationExtractor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.qabattle.configuration.WebConfig.getConfig;


/**
 * @author Aleksei Stepanov
 */

@Slf4j
public class TestsConfiguration implements BeforeEachCallback, AfterEachCallback, AfterAllCallback {

    private final WebConfig config = getConfig();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        log.info("Test '" + context.getDisplayName() + "' is started");
        ChromeOptions options = new ChromeOptions();
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.pageLoadStrategy = "none";
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getTimeout();
        Configuration.headless = config.isHeadless();
        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserCapabilities.setAcceptInsecureCerts(true);
        Configuration.reportsFolder = "./build/test-screens/";

        open(getConfig().getUrl());
        Set<Annotation> userAnnotations = AnnotationExtractor.getAnnotations(context);
        if (userAnnotations.size() == 0) {
            // В дальнейшем можно добавить обработку UserAnnotation
                setWebDriverCookie();
                openTestUrl(context);
            }
    }

    private void openTestUrl(ExtensionContext context) {
        Method get;
        Object instance = context.getRequiredTestInstance();
        try {
            get = context.getTestClass().get().getMethod("getPath");
        } catch (NoSuchMethodException e) {
            AllureLogger.saveTextLog("Error: test url not found", e.getMessage());
            log.error("ERROR: test url not found\n" + e.getMessage());
            throw new RuntimeException();
        }
        try {
            open(getConfig().getUrl() + get.invoke(instance));
        } catch (IllegalAccessException | InvocationTargetException e) {
            AllureLogger.saveTextLog("Deep link error:", "url by test method isn't found " +
                    context.getTestMethod().get().getName());
            e.printStackTrace();
            throw new IllegalStateException("ERROR: deep link for url by test method isn't found");
        }
        log.info("Before invocation finished");
    }

    private void setWebDriverCookie() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(superSeleniumMaster());
    }

    private Cookie superSeleniumMaster() {
        return new Cookie("secret", "IAmSuperSeleniumMaster", "/");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            AllureLogger.attachScreenshot("Screen Fail: " + context.getDisplayName());
        }
        log.info("Test " + context.getDisplayName() + " is finish");
        close();
    }

    @Override
    public void afterAll(ExtensionContext context) throws IOException {
        AllureEnvironment.create();
    }

}
