package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutProcess {
    public Page page;

    public CheckoutProcess(Page page) {
        this.page = page;
    }

    final String CheckoutButtonLocator = "//*[@id=\"checkout\"]";
    final String FirstNameField = "//*[@id=\"first-name\"]";
    final String LastNameField = "//*[@id=\"last-name\"]";
    final String PostalCodeField = "//*[@id=\"postal-code\"]";
    final String ContinueButton = "//*[@id=\"continue\"]";
    final String FinishButton = "//*[@id=\"finish\"]";
    final String PaymentInformationText= "//*[@data-test=\"payment-info-value\"]";
    final String VerifyFinishOrder= "//*[contains(text(),\"Thank you for your order!\")]";


    public Locator CheckoutButton() {
        return page.locator(CheckoutButtonLocator);
    }

    public Locator SetFirstName() {
        return page.locator(FirstNameField);
    }

    public Locator SetLastName() {
        return page.locator(LastNameField);
    }

    public Locator SetPostalCode() {
        return page.locator(PostalCodeField);
    }

    public Locator ContinueButton() {
        return page.locator(ContinueButton);
    }

    public Locator FinishButton() {
        return page.locator(FinishButton);
    }
    public Locator PaymentInformationText() {
        return page.locator(PaymentInformationText);
    }

    public Locator VerifyFinishOrder() {
        return page.locator(VerifyFinishOrder);
    }

}
