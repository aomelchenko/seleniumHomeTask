package com.pure.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;

import static com.pure.utils.ClassConstructor.getInstance;
import static com.pure.utils.Elements.*;
import static com.pure.utils.LocatorBuilder.buildXpath;
import static com.pure.utils.LocatorBuilder.byDataHook;
import static com.pure.utils.LocatorBuilder.byFrameName;
import static com.pure.utils.PageHelper.*;

public class CartWidgetObjects {

    public CartWidgetObjects() {
        switchToFrame(Locators.WIDGET_FRAME);
        waitUntilDisplayed(Locators.WIDGET_CONTENT, 10);
    }

    public boolean isOnPage() {
        return isElementDisplayed(Locators.WIDGET_CONTENT);
    }

    public List<String> getItemsLabelList() {
        return getTexts(Locators.ITEM_NAME);
    }

    public boolean isProductsPresent() {
        return isElementDisplayed(Locators.PRODUCT_ITEM);
    }

    public boolean isBackgroundEnabled() {
        return isElementDisplayed(Locators.WIDGET_BACKGROUND);
    }

    public Point getPopupLocation() {
        return getElementsLocation(Locators.WIDGET_CONTENT);
    }

    public CartWidgetObjects deleteProductByName(String name) {
        WebElement productElement = getSubElementOfElement(buildXpath(Locators.PRODUCT_BY_NAME_XPATH, name), Locators.PRODUCT_REMOVE_BUTTON);
        hoverOverElementAndClickOnIt(productElement);
        waitUntilHidden(buildXpath(Locators.PRODUCT_BY_NAME_XPATH, name), 2);
        return this;
    }

    public String getPopupTitle() {
        return getText(Locators.POPUP_TITLE);
    }

    public String getProductsQuantityByName(String name) {
        return getText(getSubElementOfElement(buildXpath(Locators.PRODUCT_BY_NAME_XPATH, name), Locators.PRODUCT_QUANTITY));
    }

    public String getProductsPriceByName(String name) {
        return getText(getSubElementOfElement(buildXpath(Locators.PRODUCT_BY_NAME_XPATH, name), Locators.PRODUCT_PRICE));
    }

    public String getTotalPriceValue() {
        return getText(Locators.TOTAL_PRICE_VALUE);
    }

    public CartViewObjects clickOnViewCartButton() {
        clickOn(Locators.VIEW_CART_BUTTON);
        return new CartViewObjects();
    }

    public boolean isViewCartButtonPresent() {
        return isElementDisplayed(Locators.VIEW_CART_BUTTON);
    }

    public <T extends BaseAbstractPage> T closeCartWidget(Supplier<T> page) {
        clickOn(Locators.CLOSE_BUTTON);
        waitUntilHiddenAndAssert(Locators.WIDGET_BACKGROUND, 2, "Widget Background");
        switchToDefaultContent();
        waitUntilHiddenAndAssert(Locators.WIDGET_FRAME, 2, "Widget Frame");
        return getInstance(page);
    }

    interface Locators {
        String PRODUCT_BY_NAME_XPATH = "//*[@data-hook='cart-widget-item' and .//*[@class ='item-name' and contains(text(), '%s')]]";


        By WIDGET_FRAME = byFrameName("cartwidgetPopup");
        By PRODUCT_ITEM = byDataHook("cart-widget-item");
        By WIDGET_CONTENT = byDataHook("cart-widget-dropdown");
        By CLOSE_BUTTON = By.id("cart-widget-close");
        By POPUP_TITLE = By.id("minicart-title");
        By ITEM_NAME = By.className("item-name");
        By PRODUCT_REMOVE_BUTTON = By.className("remove-item");
        By PRODUCT_QUANTITY = By.className("item-quantity");
        By PRODUCT_PRICE = By.className("item-price");
        By VIEW_CART_BUTTON = By.cssSelector("footer [data-hook='widget-view-cart-button']");
        By TOTAL_PRICE_VALUE = byDataHook("cart-widget-total");
        By WIDGET_BACKGROUND = byDataHook("cart-widget-backdrop");
    }
}
