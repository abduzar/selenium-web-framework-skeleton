package framework.utils;

import framework.services.DriverService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeUtilities {
    private static final Logger LOG = LoggerFactory.getLogger(CodeUtilities.class.getName());

    private static boolean checkPage() {
        RemoteWebDriver driver = DriverService.getInstance().getDriver();
        String jsRequest = driver.executeScript("return document.body.innerHTML").toString();
        String toLog = (jsRequest.length() > 10) ? jsRequest.substring(0, 10) : jsRequest;
        LOG.debug("Inner type of body is {} ....", toLog);
        return !(jsRequest.equals("") || jsRequest.contains("WebDriver"));
    }

    public static void checkPageIsLoaded(WebElement element) throws Error {
        if (checkPage()) {
            try {
                LOG.debug("Searching for element ... ");
                element.isDisplayed();
            } catch (Exception e) {
                LOG.warn("Page is not fully loaded.");
                throw new Error();
            }
        } else {
            throw new AssertionError("Page loading is not started.");
        }
    }
}
