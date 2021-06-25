package at.tests;

import at.SetUpAndTearDown;
import at.SimpleConfig;
import at.database.PostgreRequests;
import at.entity.Document;
import at.utils.listeners.AllureOnFailListener;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import lombok.extern.log4j.Log4j;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Listeners(AllureOnFailListener.class)
@Feature(value = "Тест съют")
@Log4j
public class TestSuite {

    private final SimpleConfig config = ConfigFactory.create(SimpleConfig.class, System.getProperties());

    @Test(priority = 10, description = "TestShouldPass")
    public void testShouldPass() {
        Assert.assertTrue(true);
    }

    @Test(priority = 20, description = "TestShouldFail")
    public void testShouldFail() {
        Assert.assertTrue(false);
    }

    @Test(priority = 30, description = "TestShouldSkip", dependsOnMethods = "testShouldFail")
    public void testShouldSkip() {
        Assert.assertTrue(true);
    }

}
