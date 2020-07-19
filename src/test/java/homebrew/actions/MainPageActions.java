package homebrew.actions;

import framework.base.BaseActions;
import homebrew.pages.MainPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPageActions extends BaseActions {
    private MainPage page = new MainPage();

    public void openPage() {
        page.get();
    }

    public void writeSearchQuery(String query) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(page.getSearchInput()));
        page.getSearchInput().sendKeys(query);
    }

    public boolean checkForLogo() {
        return  page.getGoogleLogo().isDisplayed();
    }
}
