package com.qabattle.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.qabattle.util.allure.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author Aleksei Stepanov
 */

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

    public void hoverSubmitBtn() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("$('button').trigger('mouseenter')");
    }

    @Step("Checking view index page on the site")
    public LoginPage checkViewForm() {
        page.shouldBe(Condition.visible.because("The index page didn't load"));
        loginForm.shouldBe(Condition.visible.because("Form login didn't show"));
        AllureLogger.attachScreenshot("Login form");
        return this;
    }

    @Step("Checking view login field on login form")
    public LoginPage checkLoginField() {
        getInputField("onclick", "startInputLogin()").shouldBe(Condition.visible.because("Field <input> didn't show"));
        return this;
    }

    @Step("Checking view password field on login form")
    public LoginPage checkPasswordField() {
        inputPassword.shouldBe(Condition.visible.because("Field <password> didn't show"));
        return this;
    }

    @Step("Checking sign in on the site for user")
    public LoginPage checkSignIn() {
        getInputField("onclick", "startInputLogin()").click();
        inputLogin.sendKeys("test");
        getInputField("onclick", "startInputPassword()").click();
        inputPassword.sendKeys("test");
        hoverSubmitBtn();
        btnSignIn.shouldBe(Condition.visible.because("Button <Sign in> didn't show")).click();
        return this;
    }

    @Step("Checking view title on the site")
    public LoginPage checkTitle() {
        titleSite.shouldBe(Condition.visible.because("Title by the site didn't show"));
        AllureLogger.saveTextLog("Title", titleSite.getText());
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
        inputLogin.setValue("");
        getInputField("onclick", "startInputPassword()").click();
        inputPassword.setValue("");
        // I think it is necessary
        btnSignIn.shouldBe(Condition.visible.because("Button <Sign in> didn't show")).click();
        confirm("Fill the fields login/password");
        return this;
    }

    @Step("Checking sign in with wrong user data")
    public LoginPage checkWrondData() {
        getInputField("onclick", "startInputLogin()").click();
        inputLogin.sendKeys(UUID.randomUUID().toString());
        getInputField("onclick", "startInputPassword()").click();
        inputPassword.setValue(UUID.randomUUID().toString());
        hoverSubmitBtn();
        btnSignIn.shouldBe(Condition.visible.because("Button <Sign in> didn't show")).click();
        // I think it is necessary
        confirm("Login or password ins't correct");
        return this;
    }

}
