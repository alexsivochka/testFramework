package at;

import at.webDriverProviders.ChromeDriverProvider;
import at.webDriverProviders.FirefoxDriverProvider;
import at.webDriverProviders.RemoteDriverBrowser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;

public class SetUpAndTearDown {

    private static final Logger LOGGER = Logger.getLogger(SetUpAndTearDown.class.getName());
    private final SimpleConfig config = ConfigFactory.create(SimpleConfig.class);
    private final String browser = config.browser();

    @BeforeSuite(alwaysRun = true)
    public void SetUpBrowser() {
        switch (browser){
            case "firefox" :
                Configuration.browser = FirefoxDriverProvider.class.getName();
                break;
            case "chrome" :
                Configuration.browser = ChromeDriverProvider.class.getName();
                break;
            case "remote" :
                Configuration.browser = RemoteDriverBrowser.class.getName();
                break;
            default:
                throw new IllegalStateException("Нет определена конфигурация для browser" );
        }
        Configuration.timeout = 4000;
        Configuration.startMaximized = true;
    }

    @BeforeTest(alwaysRun = true)
    public void openMainPage() {
        open(config.homePage());
    }

    @AfterTest(alwaysRun = true)
    public void afterClassMethod() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @BeforeSuite(alwaysRun = true)
    void deleteOldDirs() throws IOException {
        File buildReportsDir = new File("build/reports");
        if (buildReportsDir.exists())
            FileUtils.deleteDirectory(buildReportsDir);
    }

    @AfterSuite(alwaysRun = true)
    void closeBrowser() {
        if(WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void createEnvironmentProps() {
        FileOutputStream fos = null;
        try {
            Properties props = new Properties();
            fos = new FileOutputStream("target/allure-results/environment.properties");

            ofNullable(getProperty("browser")).ifPresent(s -> props.setProperty("browser", s));
            ofNullable(getProperty("driver.version")).ifPresent(s -> props.setProperty("driver.version", s));
            ofNullable(getProperty("os.name")).ifPresent(s -> props.setProperty("os.name", s));
            ofNullable(getProperty("os.version")).ifPresent(s -> props.setProperty("os.version", s));
            ofNullable(getProperty("os.arch")).ifPresent(s -> props.setProperty("os.arch", s));

            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");

            fos.close();
        } catch (IOException e) {
            LOGGER.error("IO problem when writing allure properties file", e);
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }
}
