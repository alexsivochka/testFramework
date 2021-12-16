package at.utils.steploger;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepsLogger implements StepLifecycleListener, TestLifecycleListener {

    @Override
    public void beforeStepStop(final StepResult result) {
        log.info(String.format("Finishing step: %s", result.getName()));
    }
}

