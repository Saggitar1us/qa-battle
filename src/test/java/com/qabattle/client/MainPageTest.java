package com.qabattle.client;

import com.qabattle.domain.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Aleksei Stepanov
 */

@DisplayName("Main page")
public class MainPageTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Check view main page")
    void checkLoginPage() {
        mainPage.checkMainPage();
    }

    @ParameterizedTest(name = "Check amount articles on the main page: {0}")
    @MethodSource("com.qabattle.client.support.DataProvider#getAmountArticles")
    void checkAdvertisers(String name, int amount) {
        mainPage.checkAmount(name, amount);
    }

    @ParameterizedTest(name = "Check card block of articles: {0}, card: {1}")
    @MethodSource("com.qabattle.client.support.DataProvider#getArticles")
    void checkCardArticles(String nameArt, String card) {
        mainPage.checkCardBlock(nameArt, card);
    }

    @ParameterizedTest(name = "Check compare download article files for card: {1}")
    @MethodSource("com.qabattle.client.support.DataProvider#getArticles")
    void checkCompareFiles(String nameArt, String card) {
        mainPage.checkCompare(nameArt, card);
    }

    @ParameterizedTest(name = "Check data cookie of article files for card: {1}")
    @MethodSource("com.qabattle.client.support.DataProvider#getArticles")
    void checkDataCookie(String article, String card) {
        mainPage.checkCookie(article, card);
    }

    @Test
    @DisplayName("Check add some cookies by articles")
    void checkSomeCookies() {
        mainPage.checkAddedCookies();
    }

    @Test
    @DisplayName("Check save cookie after opened card")
    void checkSaveCookieAfterOpen() {
        mainPage.checkSaveCookie();
    }

    @Test
    @DisplayName("Check avatar on article page")
    void checkAvatarPage() {
        mainPage.checkAvatar();
    }

    @Override
    public String getPath() {
        return "/main.html";
    }

}
