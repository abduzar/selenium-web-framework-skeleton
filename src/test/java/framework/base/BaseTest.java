package framework.base;

import framework.extension.AfterEachExtension;
import framework.models.BrowserUnderTest;
import framework.services.DriverService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;

@ExtendWith(value = {AfterEachExtension.class})
public abstract class BaseTest {
    static RemoteWebDriver driver;

    public static void prepare() {
        //TODO implement passing here browser under test value
        DriverService.getInstance().startDriver(BrowserUnderTest.CHROME);
        driver = DriverService.getInstance().getDriver();
    }
}
