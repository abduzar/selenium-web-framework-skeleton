package framework.utils;

import framework.constans.Timers;
import framework.services.DriverService;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class WebElementsUtilities {
    private static final Logger LOG = LoggerFactory.getLogger(WebElementsUtilities.class.getName());

    public static void actionClick(WebElement element) {
        RemoteWebDriver driver = DriverService.getInstance().getDriver();
        FluentWait<RemoteWebDriver> fluentWait = DriverService.getInstance().getFluentWait();
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        Actions builder = new Actions(driver);
        builder.moveToElement(element, 0, 0).click().build().perform();
    }

    public static void moveToElement(WebElement element) {
        RemoteWebDriver driver = DriverService.getInstance().getDriver();
        FluentWait<RemoteWebDriver> fluentWait = DriverService.getInstance().getFluentWait();
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    }

    @SneakyThrows
    public static void jsClick(WebElement element) {
        RemoteWebDriver driver = DriverService.getInstance().getDriver();
        LOG.debug(String.format("Element coordinates -- X:%s , Y:%s ",
                element.getLocation().x, element.getLocation().y));
        driver.executeScript("arguments[0].scrollIntoView(true);", element);
        //TODO wait for scroll animation
        Thread.sleep(500);
        driver.executeScript("arguments[0].click();", element);
    }


    public static void doubleClick(WebElement control) {
        RemoteWebDriver driver = DriverService.getInstance().getDriver();
        LOG.debug(
                String.format("Double click on coordinates X: %s, Y:%s",
                        control.getLocation().x, control.getLocation().y));
        new Actions(driver).doubleClick(control).perform();
    }

    //TODO need rework , working too long
    public static ExpectedCondition<Boolean> absenceOfElementLocated(By locator) {
        return (WebDriver driver) -> {
            assert driver != null;
            driver.manage().timeouts().implicitlyWait(Timers.SHORT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
            try {
                driver.findElement(locator);
                return false;
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                return true;
            } finally {
                driver
                        .manage()
                        .timeouts()
                        .implicitlyWait(
                                Timers.ELEMENT_WAIT_IN_SECONDS, TimeUnit.SECONDS
                        );
            }
        };
    }

}
