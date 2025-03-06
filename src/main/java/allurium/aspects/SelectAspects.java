package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.StepTextProvider;
import allurium.inputs.Select;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;
import java.util.UUID;

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
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_option", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName()),
                        Pair.of("{option}", option)
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
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
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_by_index", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName()),
                        Pair.of("{id}", String.valueOf(option))
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
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
    public void stepSelectByArrowsLeftAndRightInjection(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_option", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName()),
                        Pair.of("{option}", option)
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts and logs the step for selecting the first option in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectFirst())")
    @SuppressWarnings("unchecked")
    public void stepSelectFirstInjection(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_option_first", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName())
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts and logs the step for selecting the last option in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectLast())")
    @SuppressWarnings("unchecked")
    public void stepSelectLastInjection(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_option_last", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName())
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts and logs the step for selecting any option in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectAny())")
    @SuppressWarnings("unchecked")
    public void stepSelectAnyInjection(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_option_any", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName())
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts and logs the step for selecting any option besides the specified value.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.selectAnyBesides(String))")
    @SuppressWarnings("unchecked")
    public void stepSelectAnyBesidesInjection(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_option_any_but_not", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName())
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts and logs the step for asserting the current selected value in the select or dropdown list.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.assertCurrentValue(String))")
    public void stepAssertCurrentValue(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_assert_current_value", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName()),
                        Pair.of("{option}", option)
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts and logs the step for asserting that the current selected value is not the specified value.
     *
     * @param invocation the method invocation context
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.inputs.Select.assertCurrentValueIsNot(String))")
    public void stepAssertCurrentValueIsNot(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_assert_current_value_not", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName()),
                        Pair.of("{option}", option)
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    public void assertHasItem() {}

    /**
     * Intercepts and logs the step for asserting that the options list contains all of required options
     *
     * @param invocation
     * @throws Throwable
     */
    @Around("execution (* allurium.inputs.Select.assertHasItems(List))")
    public void stepAssertHasItems(ProceedingJoinPoint invocation) throws Throwable {
        Select select = (Select) invocation.getThis();
        List<String> option = (List<String>) invocation.getArgs()[0];
        StringBuilder sb = new StringBuilder();
        option.forEach(item -> sb.append(item));
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("select_assert_has_option", select.getParent(),
                        Pair.of("{element}", select.getUiElementType()),
                        Pair.of("{name}", select.wrappedName()),
                        Pair.of("{option}", sb.toString())
                ));
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            assertionException.printStackTrace();
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep(stepUuid);
        }
    }

}
