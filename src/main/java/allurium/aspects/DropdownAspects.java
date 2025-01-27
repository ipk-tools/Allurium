package allurium.aspects;

import allurium.inputs.DropdownSelect;
import allurium.StepConverter;
import allurium.StepTextProvider;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.ArrayList;

@Aspect
public class DropdownAspects {

    @Around("execution (* allurium.inputs.Select.extend(..))")
    public void stepExtend(ProceedingJoinPoint invocation) throws Throwable {
        DropdownSelect uiElement = (DropdownSelect) invocation.getThis();
        ArrayList<Throwable> ex = new ArrayList<>();
        StepConverter.wrapIntoStep(() -> {
            try {
                invocation.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
                ex.add(e);
            }
            return null;
        }, StepTextProvider.getStepText("dropdown_extend",
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{parent}", uiElement.getParent().get().wrappedName()),
                Pair.of("{name}", uiElement.wrappedName())
        ));
        if (ex.size() > 0) throw ex.get(0);
    }


}
