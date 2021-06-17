package at.utils;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public final class DriverUtils {

    @Attachment(type = "image/png")
    public static byte[] screenshot() throws IOException {
        try {
            return Files.toByteArray(Screenshots.takeScreenShotAsFile());
        } catch (Exception e) {
            return Files.toByteArray(new File("src/main/resources/error.png").getAbsoluteFile());
        }
    }

    @Attachment(type = "image/png")
    public static byte[] screenshotFull() throws IOException {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(WebDriverRunner.getWebDriver());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(),  "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
//        try {
//            BufferedImage bufferedImage = Shutterbug
//                    .shootPage(WebDriverRunner.getWebDriver(), ScrollStrategy.WHOLE_PAGE).getImage();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage,  "png", byteArrayOutputStream);
//            return byteArrayOutputStream.toByteArray();
//        } catch (Exception e) {
//            return Files.toByteArray(new File("src/main/resources/error.png").getAbsoluteFile());
//        }
    }

}
