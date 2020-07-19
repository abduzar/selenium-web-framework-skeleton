package framework.base;

import framework.services.DriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseActions {
    protected Logger log = LoggerFactory.getLogger(this.getClass().getName());
    protected RemoteWebDriver driver;
    protected FluentWait<RemoteWebDriver> fluentWait;

    public BaseActions() {
        driver = DriverService.getInstance().getDriver();
        fluentWait = DriverService.getInstance().getFluentWait();
    }
}
