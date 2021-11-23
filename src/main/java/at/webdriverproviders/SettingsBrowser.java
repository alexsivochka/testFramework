package at.webdriverproviders;

import at.SimpleConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.nio.file.Paths;
import java.util.HashMap;

public class SettingsBrowser {

    private SettingsBrowser() { }

    private static final SimpleConfig CV = ConfigFactory.create(SimpleConfig.class);

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-msdownload," +
                "application/AppleLink," +
                "application/x-newton-compatible-pkg," +
                "image/png," +
                "application/ris," +
                "text/csv," +
                "text/xml," +
                "text/html," +
                "text/plain," +
                "application/xml," +
                "application/zip," +
                "application/x-zip," +
                "application/x-zip-compressed," +
                "application/download," +
                "application/octet-stream," +
                "application/excel," +
                "application/vnd.ms-excel," +
                "application/x-excel," +
                "application/x-msexcel," +
                "application/vnd.openxmlformats-officedocument.spreadsheetml.template," +
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet," +
                "application/msword," +
                "application/csv," +
                "application/pdf," +
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document," +
                "application/vnd.openxmlformats-officedocument.wordprocessingml.template");

        return new FirefoxOptions()
                .addPreference("network.proxy.type", 0)
                .addPreference("browser.cache.disk.enable", false)
                .addPreference("browser.cache.memory.enable", false)
                .addPreference("browser.cache.offline.enable", false)
                .addPreference("network.negotiate-auth.trusted-uris", false)
                .addPreference("browser.download.folderList", 2)
                .addPreference("browser.download.manager.showWhenStarting", false)
                .addPreference("network.http.use-cache", false)
                .setProfile(firefoxProfile)
                .addPreference("pdfjs.disabled", true);
    }


    public static ChromeOptions getChromeOption() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-application-cache");
        options.setExperimentalOption("prefs", chromePrefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        //path/to/chrome --pack-extension=extensionProxy
        //http://chromedriver.chromium.org/extensions
        options.addExtensions(Paths.get("extensionProxy.crx").toFile());
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.addArguments("--lang=ru-Ru");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        return  options;
    }

    public static DesiredCapabilities getDesiredCapabilities() {
        Object optionsBrowser;

        String[] browserVersionAndName = CV.remoteBrowserVersion().split(":");
        String browserName = browserVersionAndName[0];
        String browserVersion = browserVersionAndName[1];

        if (browserName.equals("firefox")) {
            optionsBrowser = SettingsBrowser.getFirefoxOptions();
        }else {
            optionsBrowser = SettingsBrowser.getChromeOption();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, optionsBrowser);
        capabilities.setBrowserName(browserName);
        capabilities.setVersion(browserVersion);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("env", new String[]{"LANG=ru_RU.UTF-8", "LANGUAGE=ru:en", "LC_ALL=ru_RU.UTF-8"});

        return capabilities;
    }
}

