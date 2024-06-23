package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OrderProcess {
    public Page page;

    public OrderProcess(Page page) {
        this.page = page;
    }

    final String ShoppingCartLocator = "//*[@id=\"shopping_cart_container\"]/a";

    // add any Product to Shopping Cart dynamic using ProductName
    public Locator AddProduct(String ProductName) {
        return page.locator("//div[text()=\"" + ProductName + "\"]//following::button[1]");
    }

    // verify any Product dynamic using ProductName
    public Locator VerifyProduct(String ProductName) {
        return page.locator("//*[text()=\"" + ProductName + "\"]");
    }

    public Locator NaviToShoppingCart() {
        return page.locator(ShoppingCartLocator);
    }

}
