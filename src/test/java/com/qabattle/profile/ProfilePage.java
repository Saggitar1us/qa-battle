package com.qabattle.profile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * @author Aleksei Stepanov
 */

public class ProfilePage {

    /*
    Block User Profile
     */
    private final SelenideElement page = $(".card-body");

    private final SelenideElement userProfileBtn = $("#v-pills-home-tab");

    private final ElementsCollection btn = $$(".btn");

    private final SelenideElement inputNameField = $("#firstNameInput");

    private final SelenideElement inputLastNameField = $("#lastNameInput");

    private final SelenideElement successMsgProfile = $("#successUserInfoSaveInfo");

    /*
    Block Payment info
     */
    private final SelenideElement paymentInfo = $("#v-pills-messages-tab");

    private final SelenideElement inputCardNumber = $("#cardNumberInput");

    private final SelenideElement dropDownPaymentSystem = $("#paymentSystemSelect");

    private final SelenideElement paymentRange = $("#paymentRange");

    private final SelenideElement successMsgPaymentInfo = $("#successPaymentInfoSaveInfo");


    @Step("Checking view load of profile page")
    public ProfilePage checkViewPage() {
        page.shouldBe(Condition.visible.because("The page of profile isn't load"));
        return this;
    }

    @Step("Checking view elements for User Profile")
    public ProfilePage checkViewUser() {
        userProfileBtn.shouldBe(Condition.visible.because("The button <User Profile> not view"));
        userProfileBtn.shouldBe(Condition.enabled.because("The button <User Profile> not enabled"));
        btn.find(Condition.text("Save user info")).shouldBe(Condition.visible.because("The button <Save user info> not view"));
        btn.find(Condition.text("Save user info")).shouldBe(Condition.enabled.because("The button <Save user info> not enabled"));
        inputNameField.shouldBe(Condition.visible.because("The field name not view"));
        inputNameField.shouldBe(Condition.enabled.because("The field name not enabled"));
        inputLastNameField.shouldBe(Condition.visible.because("The field last name not view"));
        inputLastNameField.shouldBe(Condition.enabled.because("The field last name not enabled"));
        return this;
    }

    @Step("Checking view elements for Payment info")
    public ProfilePage checkViewPayment() {
        paymentInfo.shouldBe(Condition.visible.because("The button <Payment Info> not view")).click();
        inputCardNumber.shouldBe(Condition.visible.because("The field card number not view"));
        inputCardNumber.shouldBe(Condition.enabled.because("The field card number not enabled"));
        dropDownPaymentSystem.shouldBe(Condition.visible.because("The drop down of payment system not view"));
        dropDownPaymentSystem.shouldBe(Condition.enabled.because("The drop down of payment system not enabled"));
        paymentRange.shouldBe(Condition.visible.because("The range payment not view"));
        paymentRange.shouldBe(Condition.enabled.because("The range payment not enabled"));
        btn.find(Condition.text("Save payment info")).shouldBe(Condition.visible.because("The button <Save payment info> not view"));
        btn.find(Condition.text("Save payment info")).shouldBe(Condition.enabled.because("The button <Save payment info> not enabled"));
        return this;
    }

    @Step("Checking to save user profile")
    public ProfilePage saveProfile() {
        inputNameField.sendKeys("Valery");
        inputLastNameField.sendKeys("Pogremuhin");
        btn.find(Condition.text("Save user info")).click();
        successMsgProfile.shouldBe(Condition.visible.because("The success message of user profile isn't show"));
        return this;
    }

    public ProfilePage savePayment() {
        paymentInfo.shouldBe(Condition.visible.because("The button <Payment Info> not view")).click();
        inputCardNumber.sendKeys("72549872348209");
        dropDownPaymentSystem.selectOption("Visa");
        btn.find(Condition.text("Save payment info")).click();
        successMsgPaymentInfo.shouldBe(Condition.exist.because("The success message of payment info isn't show"));
        return this;
    }

    public ProfilePage saveIncorrect() {
        paymentInfo.shouldBe(Condition.visible.because("The button <Payment Info> not view")).click();
        inputCardNumber.sendKeys(UUID.randomUUID().toString());
        dropDownPaymentSystem.selectOption("Visa");
        btn.find(Condition.text("Save payment info")).click();
        successMsgPaymentInfo.shouldNotBe(Condition.exist.because("The success message of payment info isn't show"));
        return this;
    }
}
