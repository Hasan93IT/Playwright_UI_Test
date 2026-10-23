package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.assertj.core.api.Assertions;

import java.util.List;

public class OrderProcess {
    private static final Logger log = LoggerFactory.getLogger(OrderProcess.class);
    private final Page page;

    private final Locator cartLink;
    private final Locator cartItems;
    private final Locator checkoutButton;
    private final Locator continueShoppingButton;

    public OrderProcess(Page page) {
        this.page = page;
        this.cartLink = page.locator("#shopping_cart_container a");
        this.cartItems = page.locator(".cart_item");
        this.checkoutButton = page.locator("#checkout");
        this.continueShoppingButton = page.locator("#continue-shopping");
    }

    public void addProduct(String productName) {
        log.info("Adding product to cart: {}", productName);
        Locator productButton = page.locator("div.inventory_item:has-text('" + productName + "') button");
        productButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        productButton.click();
    }

    public void verifyProductInCart(String productName) {
        log.info("Verifying product in cart: {}", productName);
        Locator item = cartItems.filter(new Locator.FilterOptions().setHasText(productName));
        Assertions.assertThat(item.count())
                .as("Product '%s' should be in cart", productName)
                .isGreaterThan(0);
    }

    public void verifyProductNotInCart(String productName) {
        log.info("Verifying product NOT in cart: {}", productName);
        Locator item = cartItems.filter(new Locator.FilterOptions().setHasText(productName));
        Assertions.assertThat(item.count())
                .as("Product '%s' should not be in cart", productName)
                .isZero();
    }

    public int getCartItemCount() {
        cartLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String badgeText = page.locator(".shopping_cart_badge").innerText();
        return badgeText.isEmpty() ? 0 : Integer.parseInt(badgeText);
    }

    public void navigateToCart() {
        log.info("Navigating to cart");
        cartLink.click();
        cartItems.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void proceedToCheckout() {
        log.info("Proceeding to checkout");
        checkoutButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        checkoutButton.click();
    }

    public void continueShopping() {
        log.info("Continuing shopping");
        continueShoppingButton.click();
    }

    public List<String> getCartItemNames() {
        return cartItems.locator(".inventory_item_name").allInnerTexts();
    }
}