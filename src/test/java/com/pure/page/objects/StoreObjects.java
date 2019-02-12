package com.pure.page.objects;

import org.openqa.selenium.By;

import static com.pure.utils.Elements.getElement;
import static com.pure.utils.Elements.isElementDisplayed;
import static com.pure.utils.Elements.waitUntilDisplayed;
import static com.pure.utils.LocatorBuilder.buildXpath;
import static com.pure.utils.LocatorBuilder.byDataHook;
import static com.pure.utils.LocatorBuilder.byFrameName;
import static com.pure.utils.PageHelper.hoverOverElementAndClickOnIt;
import static com.pure.utils.PageHelper.switchToFrame;

public class StoreObjects extends BaseAbstractPage{

    public StoreObjects() {
        switchToFrame(Locators.PAGE_FRAME);
        waitUntilDisplayed(Locators.GALLERY, 10);
    }

    public boolean isOnPage() {
        return isElementDisplayed(Locators.GALLERY);
    }

    @Override
    public StoreObjects focusOnFrame() {
        switchToFrame(Locators.PAGE_FRAME);
        return this;
    }

    public ProductPageObjects selectItemByName(String name) {
        hoverOverElementAndClickOnIt(getElement(buildXpath(Locators.PRODUCT_LOCATOR_XPATH, name)));
        return new ProductPageObjects();
    }

    interface Locators {
        String PRODUCT_LOCATOR_XPATH = "//*[@data-hook='title' and contains(text(), \"%s\")]";

        By PAGE_FRAME = byFrameName("gallery");
        By GALLERY = byDataHook("gallery");
    }
}
