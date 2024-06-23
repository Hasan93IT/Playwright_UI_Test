package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutProcess {
    public Page page;

    public CheckoutProcess(Page page) {
        this.page = page;
    }

    // locators of checkout
    final String checkoutButtonLocator = "//*[@id=\"checkout\"]";
    final String firstNameField = "//*[@id=\"first-name\"]";
    final String lastNameField = "//*[@id=\"last-name\"]";
    final String postalCodeField = "//*[@id=\"postal-code\"]";
    final String continueButton = "//*[@id=\"continue\"]";
    final String finishButton = "//*[@id=\"finish\"]";
    final String paymentInformationText = "//*[@data-test=\"payment-info-value\"]";
    final String verifyFinishOrder = "//*[contains(text(),\"Thank you for your order!\")]";

    // to get locators of checkout
    public Locator checkoutButton() {
        return page.locator(checkoutButtonLocator);
    }

    public Locator setFirstName() {
        return page.locator(firstNameField);
    }

    public Locator setLastName() {
        return page.locator(lastNameField);
    }

    public Locator setPostalCode() {
        return page.locator(postalCodeField);
    }

    public Locator continueButton() {
        return page.locator(continueButton);
    }

    public Locator finishButton() {
        return page.locator(finishButton);
    }

    public Locator paymentInformationText() {
        return page.locator(paymentInformationText);
    }

    public Locator verifyFinishOrder() {
        return page.locator(verifyFinishOrder);
    }

}
