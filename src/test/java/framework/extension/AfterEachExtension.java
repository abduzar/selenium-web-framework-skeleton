package framework.extension;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import framework.services.DriverService;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AfterEachExtension implements AfterTestExecutionCallback {

    @Attachment(value = "screenshot", type = "image/png")
    public byte[] attachScreenshot() throws IOException {
        RemoteWebDriver driver = DriverService.getInstance().getDriver();
        BufferedImage image = Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).getImage();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws IOException {
        if (extensionContext.getExecutionException().isPresent()) {
            attachScreenshot();
        }
    }
}
