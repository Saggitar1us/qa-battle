package com.qabattle.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.qabattle.util.allure.AllureLogger;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author Aleksei Stepanov
 */

@Getter
public class LoginPage {

    private final SelenideElement page = $("#registrationContainer");

    private final SelenideElement loginForm = $(".card-body");

    private final SelenideElement inputLogin = $("input[id='loginInput']");

    private final SelenideElement inputPassword = $("input[id='passwordInput']");

    private final SelenideElement btnSignIn = $("[src='sign.png']");

    private final SelenideElement titleSite = $(".card-header ");


    private SelenideElement getInputField(String attr, String value) {
        return $$(".form-group").findBy(Condition.attribute(attr, value));
    }

    public void hoverSubmitBtn(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('button').trigger('mouseenter')");
    }

    @Step("Checking view index page on the site")
    public LoginPage checkViewForm() {
        page.shouldBe(Condition.visible.because("The index page didn't load"));
        loginForm.shouldBe(Condition.visible.because("Form login isn't show"));
        AllureLogger.attachScreenshot("Login form");
        return this;
    }

    @Step("Checking view login field on login form")
    public LoginPage checkLoginField() {
        getInputField("onclick", "startInputLogin()").shouldBe(Condition.visible.because("Field <input> isn't show"));
        return this;
    }

    @Step("Checking view password field on login form")
    public LoginPage checkPasswordField() {
        inputPassword.shouldBe(Condition.visible.because("Field <password> isn't show"));
        return this;
    }

    @Step("Checking sign in on the site for user")
    public LoginPage checkSignIn() {
        getInputField("onclick", "startInputLogin()").click();
        getInputLogin().sendKeys("test");
        getInputField("onclick", "startInputPassword()").click();
        getInputPassword().sendKeys("test");
        hoverSubmitBtn(WebDriverRunner.getWebDriver());
        btnSignIn.shouldBe(Condition.visible.because("Button <Sign in> ins't show")).click();
        return this;
    }

    @Step("Checking view title on the site")
    public LoginPage checkTitle() {
        getTitleSite().shouldBe(Condition.visible.because("Title by the site isn't show"));
        AllureLogger.saveTextLog("Title", getTitleSite().getText());
        return this;
    }

    @Step("Checking confirm window")
    public LoginPage checkConfirm() {
        confirm("Are you sure you want to login?");
        confirm("Really sure?");
        return this;
    }

    @Step("Checking dismiss window")
    public LoginPage checkDismiss() {
        dismiss("Are you sure you want to login?");
        checkViewForm();
        return this;
    }

    @Step("Checking dismiss after confirm")
    public LoginPage checkAfterConfirm() {
        confirm("Are you sure you want to login?");
        dismiss("Really sure?");
        checkViewForm();
        return this;
    }

    @Step("Checking sign in with empty user fields")
    public LoginPage checkWithEmptyData() {
        dismiss("Are you sure you want to login?");
        getInputField("onclick", "startInputLogin()").click();
        getInputLogin().setValue("");
        getInputField("onclick", "startInputPassword()").click();
        getInputPassword().setValue("");
        // I think it is necessary
        btnSignIn.shouldBe(Condition.visible.because("Button <Sign in> ins't show")).click();
        confirm("Fill the fields login/password");
        return this;
    }

}
