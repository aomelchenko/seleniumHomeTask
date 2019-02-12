package com.pure.utils;

import org.openqa.selenium.By;

public class LocatorBuilder {

    public static By buildCSS(String selector, Object... params) {
        return By.cssSelector(String.format(selector, params));
    }

    public static By buildCSS(String selector) {
        return By.cssSelector(selector);
    }

    public static By buildXpath(String xpathExpression) {
        return By.xpath(xpathExpression);
    }

    public static By buildXpath(String xpathExpression, String text) {
        return By.xpath(String.format(xpathExpression, text));
    }

    public static By buildXpath(String xpathExpression, Object text) {
        return By.xpath(String.format(xpathExpression, text.toString()));
    }

    public static By buildXpath(String xpathExpression, Object[] args) {
        return By.xpath(String.format(xpathExpression, args));
    }

    public static By buildXpath(String xpathExpression, String... texts) {
        return By.xpath(String.format(xpathExpression, texts));
    }

    public static By byDataHook(String dataHook) {
        return By.cssSelector("[data-hook='" + dataHook + "']");
    }

    public static By byFrameName(String frameName) {
        return buildCSS("iframe[src*='%s']", frameName);
    }
}
