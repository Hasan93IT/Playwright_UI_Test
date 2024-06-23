package Pages;


import com.microsoft.playwright.*;


public class Login {
    public Page page;

    public Login(Page page) {
        this.page = page;
    }

    String usernameFieldLocator = "//input[@id=\"user-name\"]";
    String passwordFieldLocator = "//input[@id=\"password\"]";
    String loginButtonLocator = "//input[@id=\"login-button\"]";
    String shoppingCart = "//div[@id=\"shopping_cart_container\"]";


    public Locator setUsernameField() {
        return page.locator(usernameFieldLocator);
    }

    public Locator setPasswordField() {
        return page.locator(passwordFieldLocator);
    }

    public Locator clickLoginButton() {
        return page.locator(loginButtonLocator);
    }

    public void verifyLogin() {
        page.locator(shoppingCart).isDisabled();
    }

}
