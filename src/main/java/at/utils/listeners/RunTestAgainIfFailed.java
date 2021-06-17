package at.utils.listeners;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RunTestAgainIfFailed implements IRetryAnalyzer {

    private final static Logger LOGGER = Logger.getLogger(RunTestAgainIfFailed.class);

    private int repeatCounter = 0;
    private final int MAX_COUNT = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (repeatCounter < MAX_COUNT) {
            repeatCounter++;
            LOGGER.info("***  Повторный перезапуск упавшего теста  ***");
            return true; // перезапускаем тест
        }
        if (repeatCounter > 0) LOGGER.error("ТЕСТ ПРОВАЛЕН ТРИЖДЫ!!!");
        repeatCounter = 0;
        return false;
    }

}