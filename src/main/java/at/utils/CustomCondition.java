package at.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomCondition {

    private CustomCondition() {}

    public static Condition containsOneOfTexts(List<String> texts) {
        return new Condition("containsOneOfTexts") {
            @Override
            public boolean apply(Driver driver, WebElement webElement) {
                String text = webElement.getText();
                for (int i = 0; i < texts.size(); i++) {
                    if (text.contains(texts.get(i))) return true;
                }
                return false;
            }
        };
    }

    public static Condition hasOneOfValues(String ... texts) {
        return new Condition("hasOneOfValues") {
            @Override
            public boolean apply(Driver driver, WebElement webElement) {
                String text = webElement.getText();
                for (String val:texts) {
                    if (text.equals(val)) return true;
                }
                return false;
            }
        };
    }

}
