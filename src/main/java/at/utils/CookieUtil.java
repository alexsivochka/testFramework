package at.utils;

import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Log4j
public class CookieUtil {

    public List<Cookie> getCookie() {
        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        return new ArrayList<>(cookies);
    }


    public void setCookieForBrowser(Map<String, String> mapCookie) {
        mapCookie.entrySet()
                .stream()
                .map(item -> new Cookie(item.getKey(), item.getValue()))
                .forEach(c -> WebDriverRunner.getWebDriver().manage().addCookie(c));
    }


    public List<Cookie> getCookiesForApiFromBrowser() {
        List<Cookie> cookiesForRequestRestAssured = new ArrayList();
        List<Cookie> cookiesFromBrowser = getCookie();
        for (Cookie cookieSelenium : cookiesFromBrowser) {
            Cookie cookieSeleniumRestAssured =
                    new Cookie.Builder(cookieSelenium.getName(), cookieSelenium.getValue()).build();
            cookiesForRequestRestAssured.add(cookieSeleniumRestAssured);
        }
        return cookiesForRequestRestAssured;
    }
}