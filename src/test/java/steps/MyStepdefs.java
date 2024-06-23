package steps;

import Pages.*;
import Utils.*;
import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.AfterEach;
import java.util.logging.Level;


public class MyStepdefs {

    private final String Url = LoadData.userData.getProperty("URL");
    public static Page page;
    public Factory factory = new Factory();
    public Login login = new Login(page);
    public OrderProcess orderProcess = new OrderProcess(page);
    public CheckoutProcess checkoutProcess = new CheckoutProcess(page);


    @Given("Navigate to application")
    public void navigateToApplication() {
        page = factory.setBrowser().newPage();
        page.navigate(Url);
        login = new Login(page);
    }

    @When("Login to application with{string} and {string}")
    public void loginToApplicationWithAnd(String username, String password) {
        login.setUsernameField().fill(username);
        login.setPasswordField().fill(password);
        login.clickLoginButton().click();
    }

    @Then("Verify Login")
    public void verifyLogin() {
        login.verifyLogin();
    }

    @Given("Add first {string} to Shopping Cart")
    public void addToShoppingCart(String productName) {
        orderProcess.addProduct(productName).click();
    }

    @And("Add second {string} to Shopping Cart")
    public void addSecondToShoppingCart(String productName) {
        orderProcess.addProduct(productName).click();
    }

    @Then("Verify first {string} in Shopping Cart")
    public void verifyFirstInShoppingCart(String productName) {
        orderProcess.verifyProduct(productName);
    }

    @Then("Verify second {string} in Shopping Cart")
    public void verifySecondInShoppingCart(String productName) {
        orderProcess.verifyProduct(productName).isDisabled();
    }

    @And("Navigate to Shopping Cart")
    public void navigateToShoppingCart() {
        orderProcess.naviToShoppingCart().click();
    }

    @Given("Navigate to Checkout")
    public void navigateToCheckout() {
        checkoutProcess.checkoutButton().click();
    }

    @And("Add User Information {string} and {string} and {string}")
    public void addUserInformationAndAnd(String firstName, String lastname, String PLZ) {
        checkoutProcess.setFirstName().fill(firstName);
        checkoutProcess.setLastName().fill(lastname);
        checkoutProcess.setPostalCode().fill(PLZ);
    }

    @And("Validate Checkout Overview {string} and {string}")
    public void validateCheckoutOverviewAnd(String productName1, String productName2) {
        orderProcess.verifyProduct(productName1);
        orderProcess.verifyProduct(productName2);
        checkoutProcess.continueButton().click();
        checkoutProcess.paymentInformationText().innerText();
        String PaymentInformationText = checkoutProcess.paymentInformationText().innerText();
        Util.logger(Level.INFO, "Payment information: " + PaymentInformationText);

    }

    @And("Finish Order")
    public void finishOrder() {
        checkoutProcess.finishButton().click();
    }

    @Then("Verify Finish Order")
    public void verifyFinishOrder() {
        checkoutProcess.verifyFinishOrder().isDisabled();
    }

    @AfterEach
    public void closeApp() {
        factory.closeApp();
    }
}
