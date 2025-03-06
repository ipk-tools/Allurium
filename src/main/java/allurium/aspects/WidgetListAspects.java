package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.StepTextProvider;
import allurium.lists.ListWC;
import com.codeborne.selenide.ex.ConditionNotMetException;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.UUID;

@Aspect
public class WidgetListAspects {

    // aspect template
    /*
    @Around("execution (* allurium.lists.ListWC.assertSize(Integer))")
    public void stepAssertSize(ProceedingJoinPoint invocation) throws Throwable {

    }
     */

}
