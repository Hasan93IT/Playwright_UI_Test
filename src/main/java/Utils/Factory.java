package Utils;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Factory {
    private static final Logger log = LoggerFactory.getLogger(Factory.class);
    private final Config config = Config.getInstance();
    private Playwright playwright;
    private Browser browser;

    public Browser getBrowser() {
        if (browser == null || !browser.isConnected()) {
            initializeBrowser();
        }
        return browser;
    }

    public Page newPage() {
        Browser browser = getBrowser();
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setIgnoreHTTPSErrors(true);
        
        BrowserContext context = browser.newContext(contextOptions);
        context.setDefaultTimeout(config.getTimeout());
        context.setDefaultNavigationTimeout(config.getNavigationTimeout());
        
        Page page = context.newPage();
        log.info("New page created");
        return page;
    }

    private void initializeBrowser() {
        playwright = Playwright.create();
        
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(config.isHeadless())
                .setSlowMo(config.isHeadless() ? 0 : 50);

        String browserName = config.getBrowser();
        log.info("Launching browser: {} (headless: {})", browserName, config.isHeadless());

        switch (browserName) {
            case "firefox" -> browser = playwright.firefox().launch(launchOptions);
            case "webkit" -> browser = playwright.webkit().launch(launchOptions);
            case "chrome", "chromium" -> browser = playwright.chromium().launch(launchOptions);
            default -> {
                log.warn("Unknown browser '{}', falling back to Chromium", browserName);
                browser = playwright.chromium().launch(launchOptions);
            }
        }
    }

    public void closeApp() {
        try {
            if (browser != null) {
                browser.close();
                log.info("Browser closed");
            }
        } finally {
            if (playwright != null) {
                playwright.close();
                log.info("Playwright closed");
            }
        }
    }
}