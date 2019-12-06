package com.qabattle.util.common;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Aleksei Stepanov
 */

public class Waiter {

    private static WebDriverWait waiter;

    private static WebDriverWait getWaiter() {
        if (waiter == null) {
            waiter = new WebDriverWait(WebDriverRunner.getWebDriver(), 20);
        }
        return waiter;
    }

    /**
     * @param changedText value by which the text should change
     * {@literal This method until the TEXT in the specified SelenideElement begins to change to the value changedText}
     */
    public static <T extends SelenideElement> void waitUnChangeText(T element, String changedText) {
        try {
            getWaiter().until((ExpectedCondition<Boolean>) wd ->
                    !((element)).getText().contains(changedText));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element text isn't find " + changedText);
        }
    }

    /**
     * {@literal This method waits until the text in the specified SelenideElement changes to the value changedText}
     */
    public static <T extends SelenideElement> void waitChangeText(T element, String changedText) {
        try {
            getWaiter().until((ExpectedCondition<Boolean>) wd ->
                    ((element)).getText().contains(changedText));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element text isn't find " + changedText);
        }

    }

    /**
     * Этот метод ожидает, пока ЦИФРЫ в ТЕКСТЕ в указанном SelenideElement не изменятся до значения changedDigits
     * или 60 секунд. При вызове метода передавать переменную 'regex', которая будет вычищать ненужное
     * regex передаётся при вызове
     */
    public static <T extends SelenideElement> void waitFinishDigitsChange(T element, String changedDigits, String regex) {
        try {
            getWaiter().until((ExpectedCondition<Boolean>) wd ->
                    ((element)).getText().replaceAll(regex, "").contains(changedDigits));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element value isn't find " + changedDigits);
        }
    }

    /**
     * @param changedValue value by which should change
     * {@literal This method waits until the value in the specified SelenideElement changes to the value changedValue}
     */
    public static <T extends SelenideElement> void waitFinishChangeValue(T element, String changedValue) {
        try {
            getWaiter().until((ExpectedCondition<Boolean>) wd ->
                    ((element)).val().contains(changedValue));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element value isn't find " + changedValue);
        }
    }

    /**
     * {@literal This method waits until the value of size by the ElementsCollection is equal to the expectedSize value}
     */
    public static <T extends ElementsCollection> void waitChangeCollectionSize(T ElementsCollection, int expectedSize) {
        try {
            getWaiter().until((ExpectedCondition<Boolean>) wd ->
                    ((ElementsCollection.size() == expectedSize)));
        } catch (TimeoutException e) {
            throw new RuntimeException("Size of Element Collection isn't equal " + expectedSize);
        }
    }
}
