package com.qabattle.client;

import com.qabattle.domain.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Aleksei Stepanov
 */

@DisplayName("Main page")
public class MainLoginPageTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Check view main page")
    void checkLoginPage() {
        assertTrue(true);
        System.out.println("");
    }

    @Override
    public String getPath() {
        return "/main.html";
    }

}
