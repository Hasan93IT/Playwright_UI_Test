package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.assertj.core.api.Assertions;

public class CheckoutProcess {
    private static final Logger log = LoggerFactory.getLogger(CheckoutProcess.class);
    private final Page page;

    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator postalCodeField;
    private final Locator continueButton;
    private final Locator finishButton;
    private final Locator cancelButton;
    private final Locator paymentInfo;
    private final Locator shippingInfo;
    private final Locator totalPrice;
    private final Locator completeHeader;
    private final Locator completeText;
    private final Locator overviewItems;

    public CheckoutProcess(Page page) {
        this.page = page;
        this.firstNameField = page.locator("#first-name");
        this.lastNameField = page.locator("#last-name");
        this.postalCodeField = page.locator("#postal-code");
        this.continueButton = page.locator("#continue");
        this.finishButton = page.locator("#finish");
        this.cancelButton = page.locator("#cancel");
        this.paymentInfo = page.locator("[data-test='payment-info-value']");
        this.shippingInfo = page.locator("[data-test='shipping-info-value']");
        this.totalPrice = page.locator("[data-test='total-label']");
        this.completeHeader = page.locator(".complete-header");
        this.completeText = page.locator(".complete-text");
        this.overviewItems = page.locator(".cart_item");
    }

    public void fillUserInfo(String firstName, String lastName, String postalCode) {
        log.info("Filling user info: {} {} {}", firstName, lastName, postalCode);
        firstNameField.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        postalCodeField.fill(postalCode);
    }

    public void continueToOverview() {
        log.info("Continuing to overview");
        continueButton.click();
        paymentInfo.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void verifyOverviewContains(String productName) {
        log.info("Verifying product in overview: {}", productName);
        Locator item = overviewItems.filter(new Locator.FilterOptions().setHasText(productName));
        Assertions.assertThat(item.count())
                .as("Product '%s' should be in overview", productName)
                .isGreaterThan(0);
    }

    public void verifyPaymentInfo(String expected) {
        log.info("Verifying payment info: {}", expected);
        Assertions.assertThat(paymentInfo.innerText())
                .as("Payment info")
                .contains(expected);
    }

    public void verifyShippingInfo(String expected) {
        log.info("Verifying shipping info: {}", expected);
        Assertions.assertThat(shippingInfo.innerText())
                .as("Shipping info")
                .contains(expected);
    }

    public void verifyTotalPrice(String expected) {
        log.info("Verifying total price: {}", expected);
        Assertions.assertThat(totalPrice.innerText())
                .as("Total price")
                .contains(expected);
    }

    public void finishOrder() {
        log.info("Finishing order");
        finishButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        finishButton.click();
        completeHeader.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void verifyOrderComplete() {
        log.info("Verifying order completion");
        Assertions.assertThat(completeHeader.innerText())
                .as("Complete header")
                .isEqualTo("Thank you for your order!");
        Assertions.assertThat(completeText.isVisible())
                .as("Complete text visible")
                .isTrue();
    }

    public void cancelOrder() {
        log.info("Cancelling order");
        cancelButton.click();
    }

    public String getPaymentInfo() {
        return paymentInfo.innerText();
    }

    public String getShippingInfo() {
        return shippingInfo.innerText();
    }
}