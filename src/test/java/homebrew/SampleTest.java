package homebrew;

import framework.base.BaseTest;
import framework.services.DriverService;
import homebrew.actions.MainPageActions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SampleTest extends BaseTest {
    static MainPageActions mainPageActions;

    @BeforeAll
    public static void beforeAll() {
        prepare();
        mainPageActions = new MainPageActions();
    }

    @AfterAll
    public static void afterAll() {
        DriverService.getInstance().stopDriver();
    }


    @Tag("dummy")
    @ParameterizedTest(name = "I don't {0} use {1} parameters")
    @CsvSource(value = {"NOTHING:SPECIAL"}, delimiter = ':')
    public void dummyTest(String paramOne, String paramTwo) {
        System.out.println(" Passed params " + paramOne + ", " + paramTwo);
        mainPageActions.openPage();
        mainPageActions.writeSearchQuery("Some query , you know something stupid ...");
        Assertions.assertTrue(mainPageActions.checkForLogo(),"Damn logo is not visible");
    }
}
