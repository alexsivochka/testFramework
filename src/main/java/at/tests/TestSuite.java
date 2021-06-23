package at.tests;

import at.SetUpAndTearDown;
import at.SimpleConfig;
import at.database.PostgreRequests;
import at.entity.Document;
import at.utils.listeners.AllureOnFailListener;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Listeners(AllureOnFailListener.class)
@Feature(value = "Тест съют")
public class TestSuite extends SetUpAndTearDown {

    private final SimpleConfig config = ConfigFactory.create(SimpleConfig.class, System.getProperties());

    @Test(priority = 10, description = "Test")
    public void screenTest() {
        PostgreRequests pr = new PostgreRequests();
        Document val2 = pr.getDocByCpCode("UA4000207880");
        System.out.println(val2);

        System.out.println("---------------------------");

        List<Document> ls = pr.getAll();
        ls.forEach(System.out::println);

        System.out.println("---------------------------");

        double cpCode1 = pr.getDocBidPriceByCpCode("UA4000207880");
        System.out.println(cpCode1);
    }

}
