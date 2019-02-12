package com.pure.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.pure.utils.WebDriverManager.getWebDriver;

public class Elements {
    private Elements() { }

    public static void waitUntilDisplayed(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilPresent(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitUntilClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitUntilHidden(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), timeoutInSeconds);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitUntilHiddenAndAssert(By locator, int timeoutInSeconds, String errorMessage) {
        if (!waitUntilHidden(locator, timeoutInSeconds)) {
            throw new AssertionError("'" + errorMessage + "' element was not hidden after " + timeoutInSeconds + " milliseconds!");
        }
        return true;
    }

    public static boolean isElementDisplayed(By locator) {
        try {
            return scrollIntoView(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static WebElement scrollIntoView(By locator) {
        WebElement element = getElement(locator);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public static WebElement getElement(By locator) {
        return getWebDriver().findElement(locator);
    }

    public static WebElement getSubElementOfElement(By elementLocator, By subElementLocator) {
        return getElement(elementLocator).findElement(subElementLocator);
    }

    public static List<WebElement> getElements(By locator) {
        return getWebDriver().findElements(locator);
    }
}
