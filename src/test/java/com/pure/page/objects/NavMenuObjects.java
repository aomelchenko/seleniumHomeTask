package com.pure.page.objects;

import org.openqa.selenium.By;

import static com.pure.utils.Elements.*;
import static com.pure.utils.LocatorBuilder.byDataHook;
import static com.pure.utils.PageHelper.clickOn;
import static com.pure.utils.PageHelper.getText;
import static com.pure.utils.PageHelper.switchToDefaultContent;

public class NavMenuObjects {

    public NavMenuObjects() {
        switchToDefaultContent();
        waitUntilPresent(Locators.SCROLL_TO_TOP, 5);
        scrollIntoView(Locators.SCROLL_TO_TOP);
        waitUntilDisplayed(Locators.PAGE_LOCATOR, 10);
    }

    public static NavMenuObjects getNavMenu() {
        return new NavMenuObjects();
    }

    public String getItemsCountInBag() {
        return getText(Locators.BAG_ITEMS_BUTTON);
    }

    public boolean isNavButtonsDisplayed() {
        return isElementDisplayed(Locators.HOME_BUTTON) && isElementDisplayed(Locators.CONTACT_FORM_BUTTON)
                && isElementDisplayed(Locators.STORES_BUTTON);
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(Locators.LOGIN_BUTTON);
    }

    public StoreObjects clickOnStoresButton() {
//        waitUntilPresent(Locator.SCROLL_TO_TOP, 5);
//        scrollIntoView(Locator.SCROLL_TO_TOP);
        waitUntilClickable(Locators.STORES_BUTTON, 2);
        clickOn(Locators.STORES_BUTTON);
        return new StoreObjects();
    }

    public CartWidgetObjects clickOnBagIcon() {
        clickOn(Locators.BAG_ITEMS_BUTTON);
        return new CartWidgetObjects();
    }

    public interface Locators {
        By PAGE_LOCATOR = By.id("SITE_HEADERinlineContent-gridWrapper");
        By BAG_ITEMS_BUTTON = byDataHook("items-count");
        By HOME_BUTTON = By.cssSelector(".ddm1repeaterButton[style*='visible'][data-listposition='left']");
        By CONTACT_FORM_BUTTON = By.cssSelector(".ddm1repeaterButton[style*='visible'][data-listposition='center']");
        By STORES_BUTTON = By.cssSelector(".ddm1repeaterButton[style*='visible'][data-listposition='right']");
        By LOGIN_BUTTON = By.cssSelector("[aria-label='Login or Sign up']");
        By SCROLL_TO_TOP = By.id("SCROLL_TO_TOP");
    }
}
