package at.utils.listeners;

import at.utils.DriverUtils;
import at.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public final class AllureOnFailListener implements ITestListener {

    static Logger log = LogManager.getLogger(FileUtils.class);

    @Override
    public void onTestStart(final ITestResult result) {
        log.info(String.format("\033[30;1m TEST - %s START\" \033[0m", result.getMethod().getDescription()));
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        log.info(String.format("\033[32;1m TEST - %s ENDED SUCCESSFULLY \033[0m", result.getMethod().getDescription()));
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        log.info(String.format("\033[31;1m TEST %s FAILURE  \033[0m", result.getMethod().getDescription()));
        try {
            DriverUtils.screenshot();
            DriverUtils.screenshotFull();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        log.info(String.format("TEST SKIPPED - %s", result.getMethod().getDescription()));
        try {
            DriverUtils.screenshot();
            DriverUtils.screenshotFull();
        } catch (IOException e) {
            e.printStackTrace();
        }    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
        try {
            DriverUtils.screenshot();
            DriverUtils.screenshotFull();
        } catch (IOException e) {
            e.printStackTrace();
        }    }

    @Override
    public void onStart(final ITestContext context) {
    }

    @Override
    public void onFinish(final ITestContext context) {
    }


}
