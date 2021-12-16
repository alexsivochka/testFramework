package at.utils.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RunTestAgainIfFailed implements IRetryAnalyzer {

    static Logger log = LogManager.getLogger(RunTestAgainIfFailed.class);

    private int repeatCounter = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        int maxCount = 3;
        if (repeatCounter < maxCount) {
            repeatCounter++;
            log.info("***  Повторный перезапуск упавшего теста  ***");
            return true; // перезапускаем тест
        }
        if (repeatCounter > 0) log.error("ТЕСТ ПРОВАЛЕН ТРИЖДЫ!!!");
        repeatCounter = 0;
        return false;
    }

}