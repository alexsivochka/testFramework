package at.utils.listeners;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RunTestAgainIfFailed implements IRetryAnalyzer {

    private static final Logger LOGGER = Logger.getLogger(RunTestAgainIfFailed.class);

    private int repeatCounter = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        int maxCount = 3;
        if (repeatCounter < maxCount) {
            repeatCounter++;
            LOGGER.info("***  Повторный перезапуск упавшего теста  ***");
            return true; // перезапускаем тест
        }
        if (repeatCounter > 0) LOGGER.error("ТЕСТ ПРОВАЛЕН ТРИЖДЫ!!!");
        repeatCounter = 0;
        return false;
    }

}