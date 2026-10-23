package hooks;

import Utils.Config;
import Utils.Factory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UIHooks {
    private static final Logger log = LoggerFactory.getLogger(UIHooks.class);
    private final Factory factory;
    private final Config config;
    private Browser browser;

    public UIHooks(Factory factory, Config config) {
        this.factory = factory;
        this.config = config;
    }

    @Before
    public void setup(Scenario scenario) {
        log.info("Starting scenario: {}", scenario.getName());
        browser = factory.getBrowser();
        
        // Start tracing if enabled
        if (Boolean.getBoolean("playwright.tracing.enabled")) {
            factory.getBrowser().newContext().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));
        }
    }

    @After
    public void teardown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                takeScreenshot(scenario);
                saveTrace(scenario);
            }
        } finally {
            factory.closeApp();
            log.info("Finished scenario: {} - Status: {}", scenario.getName(), scenario.getStatus());
        }
    }

    private void takeScreenshot(Scenario scenario) {
        try {
            Page page = factory.getBrowser().contexts().get(0).pages().get(0);
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", "screenshot-" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "-"));
            log.info("Screenshot attached for failed scenario: {}", scenario.getName());
        } catch (Exception e) {
            log.warn("Failed to take screenshot: {}", e.getMessage());
        }
    }

    private void saveTrace(Scenario scenario) {
        try {
            if (Boolean.getBoolean("playwright.tracing.enabled")) {
                String traceName = "trace-" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "-") 
                        + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) 
                        + ".zip";
                Path tracePath = Paths.get("target", "traces", traceName);
                Files.createDirectories(tracePath.getParent());
                
                factory.getBrowser().contexts().get(0).tracing().stop(new Tracing.StopOptions()
                        .setPath(tracePath));
                log.info("Trace saved to: {}", tracePath);
            }
        } catch (Exception e) {
            log.warn("Failed to save trace: {}", e.getMessage());
        }
    }
}