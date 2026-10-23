package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private final Properties properties = new Properties();

    public Config() {
        loadProperties();
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    private void loadProperties() {
        String configPath = System.getProperty("config.file", "src/test/resources/config.properties");
        Path path = Paths.get(configPath);

        try (InputStream input = Files.newInputStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                if (input != null) {
                    properties.load(input);
                } else {
                    throw new RuntimeException("Config file not found: " + configPath, e);
                }
            } catch (IOException ex) {
                throw new RuntimeException("Failed to load config", ex);
            }
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser", "chromium").toLowerCase();
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("setBrowserHeadless", "true"));
    }

    public String getBaseUrl() {
        return properties.getProperty("URL", "https://www.saucedemo.com");
    }

    public int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "30000"));
    }

    public int getNavigationTimeout() {
        return Integer.parseInt(properties.getProperty("navigationTimeout", "30000"));
    }
}