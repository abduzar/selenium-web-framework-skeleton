package framework.constans;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DefaultOptions {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultOptions.class.getName());
    public static final String DOWNLOAD_DIRECTORY = System.getProperty("user.home") + File.separator + "TestDownloads";

    public static ChromeOptions chromeDefaultOptions() {
        ChromeOptions options = new ChromeOptions();
        List<String> flags =
                Arrays.asList("--start-maximized", "--incognito", "--ignore-certificate-errors");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", DOWNLOAD_DIRECTORY);
        LOG.debug("Setting download folder to {}", DOWNLOAD_DIRECTORY);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments(flags);
        return options;

    }

    public static InternetExplorerOptions ieDefaultOptions() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.introduceFlakinessByIgnoringSecurityDomains();
        internetExplorerOptions.ignoreZoomSettings();
        return internetExplorerOptions;
    }
}
