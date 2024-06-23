package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OrderProcess {
    public Page page;

    public OrderProcess(Page page) {
        this.page = page;
    }

    final String shoppingCartLocator = "//*[@id=\"shopping_cart_container\"]/a";

    // add any Product to Shopping Cart dynamic using ProductName
    public Locator addProduct(String productName) {
        return page.locator("//div[text()=\"" + productName + "\"]//following::button[1]");
    }

    // verify any Product dynamic using ProductName
    public Locator verifyProduct(String productName) {
        return page.locator("//*[text()=\"" + productName + "\"]");
    }

    public Locator naviToShoppingCart() {
        return page.locator(shoppingCartLocator);
    }

}
