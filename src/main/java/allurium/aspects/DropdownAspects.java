package allurium.aspects;

import allurium.inputs.DropdownSelect;
import allurium.StepConverter;
import allurium.StepTextProvider;
import allurium.utilities.AllureStepHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.ArrayList;

@Aspect
public class DropdownAspects {

    @Around("execution (* allurium.inputs.DropdownSelect.extend(..))")
    @SuppressWarnings("unchecked")
    public void stepExtend(ProceedingJoinPoint invocation) throws Throwable {
        DropdownSelect uiElement = (DropdownSelect) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "dropdown_extend",
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{parent}", uiElement.getParent().get().wrappedName()),
                Pair.of("{name}", uiElement.wrappedName())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
