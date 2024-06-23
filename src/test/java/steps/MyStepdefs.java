package steps;

import Pages.*;
import Utils.*;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
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
    public void loginToApplicationWithAnd(String Username, String Password) {
        login.setUsernameField().fill(Username);
        login.setPasswordField().fill(Password);
        login.ClickLoginButton().click();
    }

    @Then("Verify Login")
    public void verifyLogin() {
        login.VerifyLogin();
    }

    @Given("Add first {string} to Shopping Cart")
    public void addToShoppingCart(String ProductName) {
        orderProcess.AddProduct(ProductName).click();
    }

    @And("Add second {string} to Shopping Cart")
    public void addSecondToShoppingCart(String ProductName) {
        orderProcess.AddProduct(ProductName).click();
    }

    @Then("Verify first {string} in Shopping Cart")
    public void verifyFirstInShoppingCart(String ProductName) {
        orderProcess.VerifyProduct(ProductName);
    }

    @Then("Verify second {string} in Shopping Cart")
    public void verifySecondInShoppingCart(String ProductName) {
        orderProcess.VerifyProduct(ProductName).isDisabled();
    }

    @And("Navigate to Shopping Cart")
    public void navigateToShoppingCart() {
        orderProcess.NaviToShoppingCart().click();
    }

    @Given("Navigate to Checkout")
    public void navigateToCheckout() {
        checkoutProcess.CheckoutButton().click();
    }

    @And("Add User Information {string} and {string} and {string}")
    public void addUserInformationAndAnd(String firstName, String lastname, String PLZ) {
        checkoutProcess.SetFirstName().fill(firstName);
        checkoutProcess.SetLastName().fill(lastname);
        checkoutProcess.SetPostalCode().fill(PLZ);
    }

    @And("Validate Checkout Overview {string} and {string}")
    public void validateCheckoutOverviewAnd(String ProductName1, String ProductName2) {
        orderProcess.VerifyProduct(ProductName1);
        orderProcess.VerifyProduct(ProductName2);
        checkoutProcess.ContinueButton().click();
        checkoutProcess.PaymentInformationText().innerText();
        String PaymentInformationText = checkoutProcess.PaymentInformationText().innerText();
        Util.logger(Level.INFO, "Payment information: " + PaymentInformationText);

    }

    @And("Finish Order")
    public void finishOrder() {
        checkoutProcess.FinishButton().click();
    }

    @Then("Verify Finish Order")
    public void verifyFinishOrder() {
        checkoutProcess.VerifyFinishOrder().isDisabled();
    }

    @AfterEach
    public void closeApp() {
        factory.closeApp();
    }
}
