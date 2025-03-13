package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.StepTextProvider;
import allurium.inputs.AbstractRequiredTextField;
import allurium.utilities.AllureStepHelper;
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
public class RequiredTextFieldAspects {

    @Around("execution (* allurium.inputs.AbstractRequiredTextField.assertMarkedAsRequired(..))")
    @SuppressWarnings("unchecked")
    public void stepAssertMarkedAsRequired(ProceedingJoinPoint invocation) throws Throwable {
        AbstractRequiredTextField inputField = (AbstractRequiredTextField) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "text_field_assert_marked_required",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    @Around("execution (* allurium.inputs.AbstractRequiredTextField.assertNotMarkedAsRequired(..))")
    @SuppressWarnings("unchecked")
    public void stepAssertNotMarkedAsRequired(ProceedingJoinPoint invocation) throws Throwable {
        AbstractRequiredTextField inputField = (AbstractRequiredTextField) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "text_field_assert_not_marked_required",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
