package Utils;

import com.microsoft.playwright.*;

import java.util.logging.Level;


public class Factory {
    //to get the Browser properties form config file
    private final String browserName = LoadData.userData.getProperty("browser");
    private final String setBrowserHeadless1 = LoadData.userData.getProperty("setBrowserHeadless");
    private final boolean setBrowserHeadless = Boolean.parseBoolean(setBrowserHeadless1);
    public Playwright playwright;
    public String browserNameToLowerCase = browserName.toLowerCase();
    final String warringMessage = "The given browser type is not supported, the WebKit browser is used instead.";
    public Browser browser;

    public void browserTypWarring() {
        if (browserNameToLowerCase.equals("chrome") || browserNameToLowerCase.equals("firefox")) {
            Util.logger(Level.INFO, "Browser: " + browserNameToLowerCase);
        } else {
            Util.logger(Level.WARNING, warringMessage);
        }
    }

    public Browser setBrowser() {
        browserTypWarring();

        playwright = Playwright.create();
        if (browserNameToLowerCase.equals("firefox")) {
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions().setHeadless(setBrowserHeadless)
            );
        } else if (browserNameToLowerCase.equals("chrome")) {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(setBrowserHeadless)
            );
        } else {
            browser = playwright.webkit().launch(
                    new BrowserType.LaunchOptions().setHeadless(setBrowserHeadless)
            );
        }
        return browser;
    }

    public void closeApp() {
        playwright.close();
    }
}
