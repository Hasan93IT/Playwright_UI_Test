package Pages;


import com.microsoft.playwright.*;


public class Login {
    public Page page;

    public Login(Page page) {
        this.page = page;
    }

    String usernameFieldLocator = "//input[@id=\"user-name\"]";
    String PasswordFieldLocator = "//input[@id=\"password\"]";
    String LoginButtonLocator = "//input[@id=\"login-button\"]";
    String ShoppingCart = "//div[@id=\"shopping_cart_container\"]";


    public Locator setUsernameField() {
        return page.locator(usernameFieldLocator);
    }

    public Locator setPasswordField() {
        return page.locator(PasswordFieldLocator);
    }

    public Locator ClickLoginButton() {
        return page.locator(LoginButtonLocator);
    }

    public void VerifyLogin() {
        page.locator(ShoppingCart).isDisabled();
    }

}
