package at.webdriverproviders;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Запускает браузер Chrome с настроенным профилем.
 * Плагины браузера(Add-ons) автоматом добавлятся, если поместисть файлы (*.crx) в /src/main/resources/extensions
 * Драйвер браузера автоматически загружается и прописывается в classPath
 */
public class ChromeDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        WebDriverManager.chromedriver().setup();

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));

        ChromeOptions options = new ChromeOptions();
//        options.addExtensions(Paths.get("extensionProxy.crx").toFile());
//        options.addArguments("load-extension=" + System.getProperty("user.dir") + "/src/main/java/at/utils/extensionProxy");
        options.setExperimentalOption("prefs", chromePrefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.addExtensions(listExtensions());

        ChromeDriver driver = new ChromeDriver(options);

        System.setProperty("browser", options.getBrowserName() + " " + driver.getCapabilities().getVersion());
        System.setProperty("driver.version", WebDriverManager.chromedriver().getDownloadedDriverVersion());

        return driver;
    }

    private List<File> listExtensions() {
        File[] fileList = new File("src/main/resources").listFiles();
        return Arrays.stream(fileList).filter(File::isFile)
                .filter(file -> file.getName().endsWith(".crx"))
                .collect(Collectors.toList());
    }
}
