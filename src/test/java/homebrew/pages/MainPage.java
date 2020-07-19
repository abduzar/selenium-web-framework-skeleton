package homebrew.pages;

import framework.base.BasePage;
import framework.utils.CodeUtilities;
import framework.utils.WebElementsUtilities;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MainPage extends BasePage<MainPage> {
    @FindBy(id = "hplogo")
    private WebElement googleLogo;
    @FindBy(css = "input[name='q']")
    private WebElement searchInput;

    @Override
    protected void load() {
        String url = "https://google.com";
        log.info("Opening page {}",url);
        driver.get(url);
    }

    @Override
    protected void isLoaded() throws Error {
        CodeUtilities.checkPageIsLoaded(googleLogo);
    }
}
