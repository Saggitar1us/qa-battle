package com.qabattle.configuration;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;

/**
 * @author Aleksei Stepanov
 */

@Getter
public class WebConfig {

    private static WebConfig config;

    public static WebConfig getConfig() {
        if (config == null) {
            config = new WebConfig();
        }
        return config;
    }

    private WebConfig() {
        PropertyLoader.populate(this);
    }

    @Property("url")
    private String url = "localhost:8080";

    @Property("selenide.timeout")
    private int timeout = 10_000;

    @Property("browser.size")
    private String browserSize = "1440x1024";

    @Property("selenide.browser")
    private String browser = "chrome";

    @Property("selenide.browserVersion")
    private String browserVersion = "73";

    @Property("browser.headless")
    private boolean headless = false;

}
