package steps;

import Pages.CheckoutProcess;
import Pages.Login;
import Pages.OrderProcess;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyStepdefs {
    private static final Logger log = LoggerFactory.getLogger(MyStepdefs.class);

    private final Page page;
    private final Login login;
    private final OrderProcess orderProcess;
    private final CheckoutProcess checkoutProcess;
    private final String baseUrl;

    public MyStepdefs(PageProvider provider) {
        this.page = provider.getPage();
        this.login = provider.getLogin();
        this.orderProcess = provider.getOrderProcess();
        this.checkoutProcess = provider.getCheckoutProcess();
        this.baseUrl = provider.getBaseUrl();
    }

    @Given("Navigate to application")
    public void navigateToApplication() {
        log.info("Navigate to application: {}", baseUrl);
        login.navigateToLoginPage(baseUrl);
    }

    @When("Login to application with {string} and {string}")
    public void loginToApplicationWithAnd(String username, String password) {
        log.info("Login with username: {}", username);
        login.login(username, password);
    }

    @Then("Verify Login")
    public void verifyLogin() {
        log.info("Verify successful login");
        login.verifyLoginSuccessful();
    }

    @Then("Verify Login fails with {string}")
    public void verifyLoginFailsWith(String expectedError) {
        log.info("Verify login failure with error: {}", expectedError);
        login.verifyLoginFailed(expectedError);
    }

    @When("Add first {string} to Shopping Cart")
    public void addToShoppingCart(String productName) {
        log.info("Add first product: {}", productName);
        orderProcess.addProduct(productName);
    }

    @When("Add second {string} to Shopping Cart")
    public void addSecondToShoppingCart(String productName) {
        log.info("Add second product: {}", productName);
        orderProcess.addProduct(productName);
    }

    @Then("Verify first {string} in Shopping Cart")
    public void verifyFirstInShoppingCart(String productName) {
        log.info("Verify first product in cart: {}", productName);
        orderProcess.verifyProductInCart(productName);
    }

    @Then("Verify second {string} in Shopping Cart")
    public void verifySecondInShoppingCart(String productName) {
        log.info("Verify second product in cart: {}", productName);
        orderProcess.verifyProductInCart(productName);
    }

    @When("Navigate to Shopping Cart")
    public void navigateToShoppingCart() {
        log.info("Navigate to shopping cart");
        orderProcess.navigateToCart();
    }

    @Then("Navigate to Checkout")
    public void navigateToCheckout() {
        log.info("Navigate to checkout");
        orderProcess.proceedToCheckout();
    }

    @Then("Add User Information {string} and {string} and {string}")
    public void addUserInformationAndAnd(String firstName, String lastname, String PLZ) {
        log.info("Add user info: {} {} {}", firstName, lastname, PLZ);
        checkoutProcess.fillUserInfo(firstName, lastname, PLZ);
    }

    @Then("Validate Checkout Overview {string} and {string}")
    public void validateCheckoutOverviewAnd(String productName1, String productName2) {
        log.info("Validate checkout overview for: {} and {}", productName1, productName2);
        checkoutProcess.continueToOverview();
        checkoutProcess.verifyOverviewContains(productName1);
        checkoutProcess.verifyOverviewContains(productName2);
    }

    @Then("Finish Order")
    public void finishOrder() {
        log.info("Finish order");
        checkoutProcess.finishOrder();
    }

    @Then("Verify Finish Order")
    public void verifyFinishOrder() {
        log.info("Verify order finished");
        checkoutProcess.verifyOrderComplete();
    }
}