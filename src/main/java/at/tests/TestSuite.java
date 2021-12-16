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

    @Test(priority = 20, description = "Этот тест должен был упасть")
    public void testShouldFail() {
        Assert.assertTrue(false);
    }

    @Test(priority = 30, description = "Этот тест должен быть пропущен", dependsOnMethods = "testShouldFail")
    public void testShouldSkip() {
        Assert.assertTrue(false);
    }

    @Test(priority = 20, description = "Этот тест должен был упасть 1")
    public void testShouldFail1() {
        Assert.assertTrue(false);
    }

    @Test(priority = 20, description = "Этот тест должен был упасть 2")
    public void testShouldFail2() {
        Assert.assertEquals("Первый текст", "Второй текст не равен первому");
    }

    @Test(priority = 20, description = "Этот тест должен был упасть 3")
    public void testShouldFail3() {
        Assert.assertTrue(false);
    }

    @Test(priority = 20, description = "Этот тест должен был упасть 4")
    public void testShouldFail4() {
        Assert.assertTrue(false);
    }

    @Test(priority = 20, description = "Этот тест должен был упасть 5")
    public void testShouldFail5() {
        Assert.assertEquals("Первый текст", "Второй текст не равен первому");
    }

    @Test(priority = 20, description = "Этот тест должен был упасть 6")
    public void testShouldFail6() {
        Assert.assertTrue(false);
    }

}
