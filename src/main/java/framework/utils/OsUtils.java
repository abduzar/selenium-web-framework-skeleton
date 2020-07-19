package framework.utils;


import framework.models.BrowserUnderTest;
import framework.models.OSInTest;

public class OsUtils {
    public static OSInTest getOS() {
        String osName = System.getProperty("os.name");
        return OSInTest.fromString(osName);
    }

    public static String getDriverExecutableName(BrowserUnderTest browser) {
        switch (browser) {
            case CHROME:
                return (getOS().equals(OSInTest.WINDOWS)) ? "chromedriver.exe" : "chromedriver";
            case FIREFOX:
                return (getOS().equals(OSInTest.WINDOWS)) ? "geckodriver.exe" : "geckodriver";
            case IE:
                return (getOS().equals(OSInTest.WINDOWS)) ? "IEDriverServer.exe" : null;
            default:
                throw new IllegalArgumentException("Incorrect argument " + browser.name() + " provided");
        }
    }
}
