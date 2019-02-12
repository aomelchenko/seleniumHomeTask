package com.pure.page.objects;

import org.openqa.selenium.By;

import java.util.List;

import static com.pure.utils.Elements.getSubElementOfElement;
import static com.pure.utils.Elements.isElementDisplayed;
import static com.pure.utils.Elements.waitUntilDisplayed;
import static com.pure.utils.Elements.waitUntilHidden;
import static com.pure.utils.LocatorBuilder.buildXpath;
import static com.pure.utils.LocatorBuilder.byDataHook;
import static com.pure.utils.LocatorBuilder.byFrameName;
import static com.pure.utils.PageHelper.*;

public class CartViewObjects extends BaseAbstractPage{

    public CartViewObjects() {
        switchToFrame(Locators.PAGE_FRAME);
        waitUntilDisplayed(Locators.PAGE_LOCATOR, 15);
    }

    public boolean isOnPage() {
        return isElementDisplayed(Locators.PAGE_LOCATOR);
    }

    @Override
    public CartViewObjects focusOnFrame() {
        switchToFrame(Locators.PAGE_FRAME);
        return this;
    }

    public List<String> getNamesOfItemsList() {
        return getTexts(Locators.PRODUCT_NAME);
    }

    public boolean isQuickCheckoutButtonVisible() {
        return isElementDisplayed(Locators.QUICK_CHECOUT_BUTTON);
    }

    public String getNumberOfUniqueProductsInCart() {
        return getText(Locators.NUMBER_OF_ITEMS_IN_ITEMS_LIST);
    }

    public CartViewObjects removeItemByName(String itemName) {
        By itemLocator = buildXpath(Locators.ITEM_XPATH, itemName);
        clickOn(getSubElementOfElement(itemLocator, Locators.REMOVE_BUTTON));
        waitUntilHidden(itemLocator, 2);
        return this;
    }

    public String getItemsQuantityByName(String itemName) {
        By itemLocator = buildXpath(Locators.ITEM_XPATH, itemName);
        return getValue(getSubElementOfElement(itemLocator, Locators.QUANTITY_FIELD));
    }

    public CartViewObjects inputQuantityByName(String itemName, Integer quantity) {
        By itemLocator = buildXpath(Locators.ITEM_XPATH, itemName);
        inputText(getSubElementOfElement(itemLocator, Locators.QUANTITY_FIELD), quantity.toString());
        return this;
    }

    interface Locators {
        String ITEM_XPATH = "//*[@data-hook='item' and .//*[contains(text(), \"%s\")]]";

        By PAGE_FRAME = byFrameName("cart?");
        By PAGE_LOCATOR = byDataHook("item-list");

        By QUICK_CHECOUT_BUTTON = By.cssSelector("[aria-label='quick navigation'] [data-hook='checkout-button-button']");
        By TOTAL_CHECKOUT_BUTTON = By.cssSelector("[data-hook='total-area'] [data-hook='checkout-button-button']");
        By NUMBER_OF_ITEMS_IN_ITEMS_LIST = By.cssSelector("span[data-hook='items-count']");
        By PRODUCT_NAME = byDataHook("product-name");
        By REMOVE_BUTTON = byDataHook("remove-button");
        By QUANTITY_FIELD = By.cssSelector("[aria-label='quantity']");
    }
}
