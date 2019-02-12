package com.pure.page.objects;

import com.pure.utils.TaskExecutor;
import org.openqa.selenium.By;

import static com.pure.utils.Elements.getElement;
import static com.pure.utils.Elements.isElementDisplayed;
import static com.pure.utils.Elements.waitUntilDisplayed;
import static com.pure.utils.LocatorBuilder.byDataHook;
import static com.pure.utils.LocatorBuilder.byFrameName;
import static com.pure.utils.PageHelper.clickOn;
import static com.pure.utils.PageHelper.getText;
import static com.pure.utils.PageHelper.switchToFrame;

public class ProductPageObjects extends BaseAbstractPage {

    public ProductPageObjects() {
        switchToFrame(Locators.PAGE_FRAME);
        waitUntilDisplayed(Locators.PRODUCT_PAGE, 15);
    }

    public boolean isOnPage() {
        return isElementDisplayed(Locators.PRODUCT_PAGE);
    }

    public CartWidgetObjects addItemToCart() {
        // Unfortunately this button could ignore click, so will try 3 times to click it
        return TaskExecutor.executeTask(() -> {
            clickOn(Locators.ADD_TO_CART_BUTTON);
            return new CartWidgetObjects();
        }, "Failed to click on button");
    }

    public String getProductName() {
        return getText(getElement(Locators.PRODUCT_TITLE));
    }

    public ProductPageObjects focusOnFrame() {
        switchToFrame(Locators.PAGE_FRAME);
        return this;
    }

    interface Locators {
        By PAGE_FRAME = byFrameName("product");
        By PRODUCT_PAGE = By.className("product-page");
        By ADD_TO_CART_BUTTON = byDataHook("add-to-cart");
        By PRODUCT_TITLE = byDataHook("product-title");
        By BREADCRUMBS_TITLE = By.cssSelector("[data-hook='breadcrumbs-item']:nth-of-type(2)");
        By PRODUCT_PRICE = byDataHook("product-price");
        By PRODUCT_OPTIONS = byDataHook("product-options-inputs");
        By NUMBER_INPUT = byDataHook("number-input-spinner-input");
        By PRODUCT_INFO = By.cssSelector("[data-hook='collapse-info-item']:nth-of-type(1)"); //Should be expanded
        By RETURN_AND_REFUND_INFO = By.cssSelector("[data-hook='collapse-info-item']:nth-of-type(2)");
        By SHIPPING_INFO = By.cssSelector("[data-hook='collapse-info-item']:nth-of-type(3)");

    }
}
