package Utils;

import com.microsoft.playwright.*;

public class Factory {
    //to get the Browser properties form config file
    private final String BrowserName = LoadData.userData.getProperty("browser");
    private final String setBrowserHeadless1 = LoadData.userData.getProperty("setBrowserHeadless");
    boolean setBrowserHeadless=Boolean.parseBoolean(setBrowserHeadless1);


    public Browser setBrowser( ) {

        Playwright playwright = Playwright.create();
        Browser browser = null;
        if (BrowserName.equals("firefox")) {
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions().setHeadless(setBrowserHeadless)
            );
        } else if (BrowserName.equals("chrome")) {
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
}
