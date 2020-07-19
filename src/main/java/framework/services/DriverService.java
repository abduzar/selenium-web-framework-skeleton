package framework.services;

import framework.constans.DefaultOptions;
import framework.constans.Timers;
import framework.models.BrowserUnderTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static framework.constans.Timers.BIG_TIMEOUT_IN_SECONDS;
import static framework.constans.Timers.SHORT_TIMEOUT_IN_MILLISECONDS;
import static framework.utils.FolderUtilities.getDriverPath;

public class DriverService {
    private RemoteWebDriver driver;
    private static DriverService instance;
    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

    public DriverService() {
        System.setProperty("webdriver.chrome.driver", getDriverPath(BrowserUnderTest.CHROME));
        System.setProperty("webdriver.gecko.driver", getDriverPath(BrowserUnderTest.FIREFOX));
        System.setProperty("webdriver.firefox.marionette", String.valueOf(true));
        System.setProperty("webdriver.ie.driver", getDriverPath(BrowserUnderTest.IE));
    }

    public static synchronized DriverService getInstance() {
        if (instance == null) {
            instance = new DriverService();
        }
        return instance;
    }

    public RemoteWebDriver startDriver(BrowserUnderTest browser) {
        switch (browser) {
            case CHROME:
                driver = startChrome();
                break;
            case FIREFOX:
                driver = startFirefox();
                break;
            case IE:
                driver = startIE();
                break;
            default:
                throw new IllegalArgumentException("Incorrect browser type provided " + browser.name());
        }
        LOG.info("Starting WebDriver with screen size {} : {}",
                driver.manage().window().getSize().width,
                driver.manage().window().getSize().height);
        return driver;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public FluentWait<RemoteWebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(BIG_TIMEOUT_IN_SECONDS))
                .pollingEvery(Duration.ofMillis(SHORT_TIMEOUT_IN_MILLISECONDS))
                .ignoreAll(Arrays.asList(StaleElementReferenceException.class, NoSuchElementException.class));
    }

    public RemoteWebDriver startChromeWithOptions(ChromeOptions chromeOptions) {
        return startChrome(chromeOptions);
    }

    private RemoteWebDriver startChrome(ChromeOptions chromeOptions) {
        return new ChromeDriver(chromeOptions);
    }

    private RemoteWebDriver startChrome() {
        ChromeDriver chromeDriver = new ChromeDriver(DefaultOptions.chromeDefaultOptions());
        adjustDriverTimeouts(chromeDriver);
        driver = chromeDriver;
        return driver;
    }

    private RemoteWebDriver startFirefox() {
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        adjustDriverTimeouts(firefoxDriver);
        driver = firefoxDriver;
        return driver;
    }

    private RemoteWebDriver startIE() {
        InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver(DefaultOptions.ieDefaultOptions());
        adjustDriverTimeouts(internetExplorerDriver);
        driver = internetExplorerDriver;
        return driver;
    }

    private void adjustDriverTimeouts(RemoteWebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Timers.SHORT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
    }

    public void stopDriver() {
        driver.close();
        driver.quit();
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM ieexplore.exe /T");
        } catch (IOException e) {
            LOG.warn("Something wrong when trying to kill IE Server. StackTrace: {}", e.getMessage());
        }
    }
}
