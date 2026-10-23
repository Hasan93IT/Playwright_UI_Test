package steps;

import Pages.CheckoutProcess;
import Pages.Login;
import Pages.OrderProcess;
import Utils.Config;
import Utils.Factory;
import com.microsoft.playwright.Page;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class PageProvider {
    private static final Logger log = LoggerFactory.getLogger(PageProvider.class);

    private final Factory factory;
    private final Config config;
    private Page page;
    private Login login;
    private OrderProcess orderProcess;
    private CheckoutProcess checkoutProcess;

    public PageProvider(Factory factory, Config config) {
        this.factory = factory;
        this.config = config;
    }

    @Before
    public void createPageObjects(Scenario scenario) {
        log.debug("Creating page objects for scenario: {}", scenario.getName());
        page = factory.newPage();
        login = new Login(page);
        orderProcess = new OrderProcess(page);
        checkoutProcess = new CheckoutProcess(page);
    }

    public Page getPage() {
        return page;
    }

    public Login getLogin() {
        return login;
    }

    public OrderProcess getOrderProcess() {
        return orderProcess;
    }

    public CheckoutProcess getCheckoutProcess() {
        return checkoutProcess;
    }

    public String getBaseUrl() {
        return config.getBaseUrl();
    }
}