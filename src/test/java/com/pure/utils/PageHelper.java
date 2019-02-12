package com.pure.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static com.pure.utils.Elements.getElement;
import static com.pure.utils.Elements.waitUntilDisplayed;
import static com.pure.utils.WebDriverManager.getWebDriver;
import static java.lang.Thread.sleep;
import static java.util.Optional.ofNullable;

public class PageHelper {
    public static void openPage(String fullUrl) {
        getWebDriver().get(fullUrl);
    }

    public static void clickOn(By locator) {
        waitUntilDisplayed(locator, 2);
        getElement(locator).click();
    }

    public static void clickOn(WebElement element) {
        element.click();
    }

    public static void inputText(WebElement inputField, String value) {
        inputField.clear();
        inputField.sendKeys(value, Keys.ENTER);
    }

    public static void clearTextFieldFromKeyboard(WebElement element) {
        Callable<Boolean> task = () -> {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            sleep(200); //Wait while text becomes selected
            element.sendKeys(Keys.BACK_SPACE);
            return getText(element).isEmpty() ? true : null; // return null to trigger task rerun
        };
        TaskExecutor.executeTask(task, 3, 500, "Failed to clear input field");
    }

    public static String getText(By locator) {
        return getText(getElement(locator));
    }

    public static String getValue(WebElement element) {
        return ofNullable(element.getAttribute("value"))
                .orElse("");
    }

    public static String getText(WebElement element) {
        return element.getText().trim();
    }

    public static List<String> getTexts(By locator) {
        waitUntilDisplayed(locator, 5);
        return getWebDriver()
                .findElements(locator)
                .stream()
                .map(PageHelper::getText)
                .collect(Collectors.toList());
    }

    public static void switchToFrame(By locator) {
        switchToDefaultContent();
        waitUntilDisplayed(locator, 15);
        getWebDriver().switchTo().frame(getElement(locator));
    }

    public static void switchToDefaultContent() {
        getWebDriver().switchTo().defaultContent();
    }

    public static Point getElementsLocation(By location) {
        return getElement(location).getLocation();
    }

    public static void hoverOverElementAndClickOnIt(WebElement element) {
        Actions action = new Actions(getWebDriver());
        action
                .moveToElement(element)
                .click()
                .build()
                .perform();
    }
}
