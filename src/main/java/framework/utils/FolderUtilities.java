package framework.utils;

import framework.models.BrowserUnderTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

import static framework.utils.OsUtils.*;

public class FolderUtilities {
    private static final String PROJECT_ROOT = Paths.get(".").toAbsolutePath().normalize().toString();
    private static final Logger LOG = LoggerFactory.getLogger(FolderUtilities.class.getName());

    public static String getDriverSourcePath() {
        return getMainResourceFolder() + File.separator + "drivers";
    }

    public static String getMainResourceFolder() {
        return PROJECT_ROOT + File.separator + "src" + File.separator + "main" + File.separator + "resources";
    }

    public static String getTestResourceFolder() {
        return PROJECT_ROOT + File.separator + "src" + File.separator + "test" + File.separator + "resources";
    }

    public static String getDriverPath(BrowserUnderTest browser) {
        LOG.info("Searching for drivers in resource folder {}",
                File.separator + getOS().name().toLowerCase() + File.separator + getDriverExecutableName(browser));
        return getDriverSourcePath()
                + File.separator + getOS().name().toLowerCase()
                + File.separator + getDriverExecutableName(browser);
    }
}
