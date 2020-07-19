package framework.base;

import framework.services.DriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {
    protected Logger log = LoggerFactory.getLogger(this.getClass().getName());
    protected RemoteWebDriver driver;

    public BasePage() {
        init();
    }

    @Override
    protected void load() {
        throw new UnsupportedOperationException("Implement if necessary");
    }

    @Override
    protected void isLoaded() throws Error {
        throw new UnsupportedOperationException("Implement if necessary");
    }

    public void init() {
        driver = DriverService.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


}
