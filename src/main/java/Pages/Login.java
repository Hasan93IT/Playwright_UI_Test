package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login {
    private static final Logger log = LoggerFactory.getLogger(Login.class);
    private final Page page;

    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator cartIcon;
    private final Locator errorMessage;

    public Login(Page page) {
        this.page = page;
        this.usernameField = page.locator("#user-name");
        this.passwordField = page.locator("#password");
        this.loginButton = page.locator("#login-button");
        this.cartIcon = page.locator("#shopping_cart_container");
        this.errorMessage = page.locator("[data-test='error']");
    }

    public void navigateToLoginPage(String baseUrl) {
        log.info("Navigating to: {}", baseUrl);
        page.navigate(baseUrl);
        usernameField.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void login(String username, String password) {
        log.info("Logging in as: {}", username);
        usernameField.fill(username);
        passwordField.fill(password);
        loginButton.click();
    }

    public void verifyLoginSuccessful() {
        log.info("Verifying successful login");
        cartIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void verifyLoginFailed(String expectedError) {
        log.info("Verifying login failure");
        errorMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        org.assertj.core.api.Assertions.assertThat(errorMessage.innerText())
                .as("Error message")
                .contains(expectedError);
    }

    public boolean isErrorVisible() {
        return errorMessage.isVisible();
    }
}