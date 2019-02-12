package com.pure.test;

import com.pure.page.objects.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.pure.page.objects.NavMenuObjects.getNavMenu;
import static com.pure.utils.PageHelper.openPage;
import static com.pure.utils.WebDriverManager.closeBrowser;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class CardUsageTests {
    private String baseUrl = "https://georgel8.wixsite.com/ait-ht/shop";
    private String glasses = "Premium Glasses";
    private String scarf = "I'm a product";
    private StoreObjects store;
    private ProductPageObjects productPage;
    private CartWidgetObjects cartPopup;
    private CartViewObjects cartView;


    @BeforeClass
    public void setUp() {
        openPage(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowser();
    }

//    Go to the Shop
//    Select product GLASSES from gallery shown

    @Test
    public void selectProduct() {
        checkNavigationButtons();

        store = new StoreObjects();

        assertThat("Store page was not loaded", store.isOnPage());

        selectProductAndCheck(glasses);
    }

//    Add item to Cart

    @Test(dependsOnMethods = "selectProduct")
    public void addProductToCart() {
        addProductToCartAndCheck(glasses);
    }

//    Remove item from Cart
    @Test(dependsOnMethods = "addProductToCart")
    public void removeItemFromCart() {
        cartPopup.deleteProductByName(glasses);

        assertThat("Cart should be empty", !cartPopup.isProductsPresent());
        assertThat("View Cart button shouldn't be visible", !cartPopup.isViewCartButtonPresent());
    }

//    Minimize the Cart

    @Test(dependsOnMethods = "removeItemFromCart")
    public void minimizeChart() {
        String zeroItems = "0";
        minimizeChartAndCheckNumberOfProductsOnCartIcon(zeroItems);
        productPage.focusOnFrame();
    }


//    Add product to Cart again

    @Test(dependsOnMethods = "minimizeChart")
    public void addProductToCartAgain() {
        addProductToCartAndCheck(glasses);
    }


//    Minimize the Cart

    @Test(dependsOnMethods = "addProductToCartAgain")
    public void minimizeChartAgain() {
        String singleItem = "1";
        minimizeChartAndCheckNumberOfProductsOnCartIcon(singleItem);
    }


//    Go back to main STORES gallery

    @Test(dependsOnMethods = "minimizeChartAgain")
    public void goBackToMainStoresGallery() {
        String singleItem = "1";

        checkNavigationButtons();

        store = getNavMenu().clickOnStoresButton();

        assertThat("Product was removed from bag icon", getNavMenu().getItemsCountInBag(), is(singleItem));
    }

//    Go to Bag of Items and expect glasses items


    @Test(dependsOnMethods = "goBackToMainStoresGallery")
    public void goToBagItemAndExpectGlassesItem() {
        cartPopup = getNavMenu().clickOnBagIcon();

        checkCartPopup(glasses);
    }

//    Minimize the Cart

    @Test(dependsOnMethods = "goToBagItemAndExpectGlassesItem")
    public void minimizeCartOnStoresPage() {
        String singleItem = "1";

        store = cartPopup.closeCartWidget(StoreObjects::new);

        assertThat("Store page was not loaded", store.isOnPage());

        assertThat("Wrong number of products on bag icon", getNavMenu().getItemsCountInBag(), is(singleItem));
    }


//    Select product SCARF from the gallery shown

    @Test(dependsOnMethods = "minimizeCartOnStoresPage")
    public void selectScarf() {
        selectProductAndCheck(scarf);
    }


//    Add item to Cart

    @Test(dependsOnMethods = "selectScarf")
    public void addScarfToCart() {
        addProductToCartAndCheck(scarf);
    }

//    Go to Cart View

    @Test(dependsOnMethods = "addScarfToCart")
    public void goToCart() {
        String twoItems = "2";
        cartView = cartPopup.clickOnViewCartButton();

        assertThat("Wrong number of unique items in cart", cartView.getNumberOfUniqueProductsInCart(), containsString(twoItems));
        checkProductInCart(glasses, 1);
        checkProductInCart(scarf, 1);
        assertThat("Quick checkout button missed", cartView.isQuickCheckoutButtonVisible());

        assertThat("Wrong number of products on bag icon", getNavMenu().getItemsCountInBag(), is(twoItems));    }


//    Change the quantity of glasses to 3

    @Test(dependsOnMethods = "goToCart")
    public void changeQuantityOfGlasses() {
        cartView
                .focusOnFrame()
                .inputQuantityByName(glasses, 3);

    }


//    Remove Scarf from the Cart View
    @Test(dependsOnMethods = "changeQuantityOfGlasses")
    public void removeScarfFromCartView() {
        String singleItem = "1";
        cartView.removeItemByName(scarf);
        assertThat("Wrong number of unique items in cart", cartView.getNumberOfUniqueProductsInCart(), containsString(singleItem));
    }

    private void addProductToCartAndCheck(String productName) {
        cartPopup = productPage.addItemToCart();

        checkCartPopup(productName);
    }

    private void minimizeChartAndCheckNumberOfProductsOnCartIcon(String numberOfItems) {
        productPage = cartPopup.closeCartWidget(ProductPageObjects::new);

        assertThat("Product page was not loaded", productPage.isOnPage());
        assertThat("Wrong number of products on bag icon", getNavMenu().getItemsCountInBag(), is(numberOfItems));
    }

    private void checkCartPopup(String productName) {
        assertThat("Cart popup was not loaded", cartPopup.isOnPage());
        assertThat("Background should be faded", cartPopup.isBackgroundEnabled());
        assertThat("Product was not added to cart", cartPopup.getItemsLabelList(), hasItem(productName));
        assertThat("Popup should be on the left", cartPopup.getPopupLocation().getX(), greaterThan(1000));
        assertThat("View Cart button should be visible", cartPopup.isViewCartButtonPresent());
    }

    private void selectProductAndCheck(String productName) {
        productPage = store
                .focusOnFrame()
                .selectItemByName(productName);

        assertThat("Product page was not loaded", productPage.isOnPage());
        assertThat("Wrong Product name", productPage.getProductName(), containsString(productName));
    }

    private void checkNavigationButtons() {
        NavMenuObjects navMenu = getNavMenu();
        assertThat("Navigation buttons was not loaded", navMenu.isNavButtonsDisplayed());
        assertThat("Login button was not loaded", navMenu.isLoginButtonDisplayed());
    }

    private void checkProductInCart(String productName, Integer quantity) {
        assertThat("Wrong quantity for " + productName, cartView.getItemsQuantityByName(productName), is(quantity.toString()));
    }
}
