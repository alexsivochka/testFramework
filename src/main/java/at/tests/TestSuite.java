package at.tests;

import at.SimpleConfig;
import at.utils.listeners.AllureOnFailListener;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureOnFailListener.class)
@Feature(value = "Тест съют")
@Slf4j
public class TestSuite {

    private final SimpleConfig config = ConfigFactory.create(SimpleConfig.class, System.getProperties());

    @Test(priority = 10, description = "Этот тест должен был пройти")
    public void testShouldPass() {
        Assert.assertTrue(true);
    }

}
