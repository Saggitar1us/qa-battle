package com.qabattle.util.allure;

import com.codeborne.selenide.Configuration;
import com.qabattle.configuration.WebConfig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static java.util.Optional.ofNullable;

/**
 * @author Aleksei Stepanov
 */

public class AllureEnvironment {

    public static void create() throws IOException {
        Properties properties = new Properties();
        WebConfig testProperties = WebConfig.getConfig();

        FileOutputStream fos = new FileOutputStream("build/allure-results/environment.properties");

        addPropertyToFile(properties, "Test url", testProperties.getUrl());

        String browserVersion = testProperties.getBrowserVersion() != null ? testProperties.getBrowserVersion() : "default version";
        addPropertyToFile(properties, "Browser", Configuration.browser + " " + browserVersion);

        properties.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
        fos.close();
    }

    private static void addPropertyToFile(Properties properties, String propertyName, String propertyValue) {
        ofNullable(propertyName).ifPresent(s -> properties.setProperty(s, propertyValue));
    }

}
