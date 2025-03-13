package allurium.aspects;

import allurium.StepTextProvider;
import allurium.inputs.Select;
import allurium.utilities.AllureStepHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;

/**
 * Aspect class for handling additional behavior and logging for the {@link Select} class.
 * <p>
 * This class intercepts and augments specific method calls on {@link Select} instances to provide
 * automated logging, step injection into Allure reports, and error handling.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Intercepts select actions by text, index, and other criteria.</li>
 *     <li>Handles assertions related to the selected value and dropdown options.</li>
 *     <li>Automatically adds steps to Allure reports for better traceability.</li>
 *     <li>Provides consistent error handling and logging for select-related operations.</li>
 * </ul>
 */
@Aspect
public class SelectAspects {

    /**
     * Intercepts and logs the step for selecting an option by its text value.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.select(String))")
    @SuppressWarnings("unchecked")
    public void stepSelect(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "select_option", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{option}", option)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for selecting an option by its index.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.select(int))")
    @SuppressWarnings("unchecked")
    public void stepSelectByIndex(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        int option = (int) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "select_by_index", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{id}", String.valueOf(option))
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /*
    @Around("execution (* allurium.inputs.Select.selectByArrowsLeftAndRight(String))")
    @SuppressWarnings("unchecked")
    public void stepSelectByArrowsLeftAndRightInjection(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0]; //todo: сделать чтобы шаги не падали если нет аргумента

        StepConverter.wrapIntoStep(() -> {
            try {
                invocation.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        }, StepTextProvider.getStepText("dropdown_select",
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{parent}", select.getParent().get().wrappedName()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{option}", option)
        ));
    }*/

    /**
     * Intercepts and logs the step for selecting an option using arrow keys.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectByArrowsLeftAndRight(String))")
    @SuppressWarnings("unchecked")
    public void stepSelectByArrowsLeftAndRight(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "select_option", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{option}", option)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for selecting the first option in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectFirst())")
    @SuppressWarnings("unchecked")
    public void stepSelectFirst(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "select_option_first", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for selecting the last option in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectLast())")
    @SuppressWarnings("unchecked")
    public void stepSelectLast(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "select_option_last", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for selecting any option in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectAny())")
    @SuppressWarnings("unchecked")
    public void stepSelectAny(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "select_option_any", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for selecting any option besides the specified value.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectAnyBesides(String))")
    @SuppressWarnings("unchecked")
    public void stepSelectAnyBesides(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "select_option_any_but_not", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{option}", option)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for asserting the current selected value in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.assertCurrentValue(String))")
    @SuppressWarnings("unchecked")
    public void stepAssertCurrentValue(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "select_assert_current_value", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{option}", option)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for asserting that the current selected value is not the specified value.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.assertCurrentValueIsNot(String))")
    @SuppressWarnings("unchecked")
    public void stepAssertCurrentValueIsNot(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "select_assert_current_value_not", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{option}", option)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts and logs the step for asserting that the options list contains all of required options
     *
     * @param invocation
     * @throws Throwable
     */
    @Around("execution (* allurium.inputs.Select.assertHasItems(List))")
    @SuppressWarnings("unchecked")
    public void stepAssertHasItems(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        List<String> options = (List<String>) invocation.getArgs()[0];
        String optionList = String.join(", ", options);
        String stepName = StepTextProvider.getStepText(
                "select_assert_has_option", select.getParent(),
                Pair.of("{element}", select.getUiElementType()),
                Pair.of("{name}", select.wrappedName()),
                Pair.of("{option}", optionList)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
