package com.qabattle.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.qabattle.util.allure.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.qabattle.client.support.FIleUtility.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

;

/**
 * @author Aleksei Stepanov
 */

public class MainPage {

    /**
     * Main block
     */
    private final SelenideElement page = $("#mainContainer");

    private final SelenideElement avatar = $("#avatar");

    private final SelenideElement articlesTitle = $(".card");

    private final ElementsCollection articleList = $$(".tree-main-node");

    private final ElementsCollection articleSubListElement = $$(".sub-tree-element");

    private final ElementsCollection articlesForm = $$(".card-body");

    /**
     * Card block
     */
    private final SelenideElement cardTitle = $(".card-title");

    private final ElementsCollection cardText = $$(".card-text");

    private final SelenideElement cardTextArea = $(".form-control");

    private final ElementsCollection buttonList = $$(".btn");

    private final SelenideElement image = $("#heroImage");

    private final SelenideElement slider = $(".ui-slider");

    @Step("Checking view main page")
    public MainPage checkMainPage() {
        page.shouldBe(visible.because("Main page isn't show"));
        articlesTitle.shouldHave(text("Articles to read").because("The title of articles isn't show"));
        articlesForm.shouldHave(CollectionCondition.size(3).because("Size block with articles isn't equal"));
        return this;
    }

    @Step("Checking amount of {name} articles")
    public MainPage checkAmount(String name, int amount) {
        articleList.find(text(name).because("Button of " + name + " isn't show")).click();
        int actualAmount = (int) articleSubListElement.stream().filter(item -> item.is(visible)).count();
        assertThat(actualAmount).as("The articles of advertisers doesn't equal").isEqualTo(amount);
        return this;
    }

    @Step("Checking card {cardName}")
    public MainPage checkCardBlock(String articleName, String cardName) {
        chooseCardArticles(articleName, cardName);
        assertAll(
                () -> cardTitle.shouldBe(visible.because("The card title " + cardName + " isn't show")),
                () -> assertThat(cardTitle.getText()).as("The card name " + cardName + " isn't find").isEqualTo(cardName),
                () -> cardText.shouldHave(CollectionCondition.size(2).because("The card description not found")),
                () -> cardTextArea.shouldBe(visible.because("Text area ins't show")),
                () -> buttonList.find(text("Download info")).shouldBe(visible.because("The button <Download info> isn't show")),
                () -> image.shouldBe(visible.because("The users avatar isn't show")),
                () -> slider.shouldBe(visible.because("The slider isn't show")),
                () -> buttonList.find(text("Move to saved")).shouldBe(visible.because("The button <Move to saved> ins't show")),
                () -> buttonList.find(text("Removed from saved")).shouldBe(visible.because("The button <Removed from saved> ins't show"))
        );
        assertTrue(buttonList.find(text("Download info")).is(enabled), "The button <Download info> did'n enabled");
        assertTrue(slider.is(enabled), "Slider did't enabled");
        assertTrue(buttonList.find(text("Move to saved")).is(disabled), "The button <Move to saved> have to disabled");
        assertTrue(buttonList.find(text("Removed from saved")).is(disabled), "The button <Removed from saved> have to disabled. Cookie will not save");
        AllureLogger.attachScreenshot(cardName);
        return this;
    }

    @Step("Checking download file {card}")
    public MainPage checkCompare(String articleName, String name) {
        chooseCardArticles(articleName, name);
        String textArea = cardTextArea.getText();
        String loadFile = getStringFromFile(getFile(name));
        assertTrue(isEqualFiles(textArea, loadFile), "Files are not equals");
        return this;
    }

    @Step("Checking cookie for {cardName}")
    public MainPage checkCookie(String articleName, String cardName) {
        chooseCardArticles(articleName, cardName);
        saveCookie();
        assertTrue(isCookieValue(cardName, "saved"), "The cookie value of " + cardName + " not found");
        return this;
    }

    @Step("Is cookie value {cardName}")
    private boolean isCookieValue(String cardName, String cookieName) {
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);
        if (cookie == null) {
            return false;
        }
        return cookie.getValue().contains(cardName);
    }

    private void chooseCardArticles(String article, String name) {
        articleList.find(text(article)).shouldBe(visible.because("The article " + article + " not found")).click();
        articleSubListElement.find(text(name).because("The card " + name + " not found")).click();
    }

    @Step("Save cookie")
    private void saveCookie() {
        articlesTitle.shouldHave(text("Articles to read").because("The title of articles isn't show"));
        cardTextArea.scrollIntoView("$(\".form-control\").scrollTop($(\".form-control\")[0].scrollHeight)");
        buttonList.find(text("Move to saved")).shouldBe(enabled.because("The button <Move to saved> is disabled"));
        buttonList.find(text("Move to saved")).click();
    }

    @Step("Checking added & remove some cookies")
    public MainPage checkAddedCookies() {
        String[] publishers = {"Youtube", "Instagram"};
        Arrays.stream(publishers).forEach(publisher -> {
            chooseCardArticles("Publishers", publisher);
            saveCookie();
            assertTrue(isCookieValue(publisher, "saved"), "The cookie value of " + publisher + " not found");
            buttonList.find(text("Removed from saved")).click();
            assertFalse(isCookieValue(publisher, "saved"), "The cookie value of " + publisher + " not removed");
        });
        return this;
    }

    @Step("Checking save cookie")
    public MainPage checkSaveCookie() {
        chooseCardArticles("Advertisers", "Adidas");
        saveCookie();
        $$(".card-header").find(text("Saved articles")).shouldBe(visible.because("The articles not save"));
        $$("[class='card-body text-right']").find(text("Advertisers")).click();
        articleSubListElement.find(text("Adidas")).shouldBe(visible.because("The card Adidas not save")).click();
        assertTrue(isCookieValue("Advertisers", "savedOpened"), "The cookie value of Advertisers not found");
        refresh();
        articlesTitle.shouldHave(text("Articles to read").because("The title of articles isn't show"));
        assertTrue(isCookieValue("Advertisers", "savedOpened"), "The cookie savedOpened was remove after refresh page");
        return this;
    }

    public MainPage checkAvatar() {
        avatar.shouldBe(visible.because("Tha avatar not view on articles page")).click();
        $(".card-body").shouldBe(visible);
        assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().equals("http://localhost:8080/profile.html"),
                "After click the page /profile.html have to load");
        return this;
    }
}
