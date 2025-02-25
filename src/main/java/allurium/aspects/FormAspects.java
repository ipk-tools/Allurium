package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.StepTextProvider;
import allurium.forms.AbstractForm;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.UUID;

/**
 * Aspect class for intercepting and logging actions performed on {@link AbstractForm} instances.
 * <p>
 * This aspect is specifically designed to enhance form submission methods by logging their execution
 * as steps in Allure reports. It captures the context of the action, including the name and type of
 * the form, and dynamically logs the step result based on the success or failure of the intercepted
 * method.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Intercepts the {@code submit()} method of {@link AbstractForm} and logs its execution.</li>
 *     <li>Generates a unique step identifier and uses {@link StepTextProvider} to create descriptive
 *     step names for Allure reports.</li>
 *     <li>Handles success and failure scenarios by updating the step's status accordingly.</li>
 * </ul>
 *
 * <h3>Example:</h3>
 * <pre>{@code
 * public class LoginForm extends AbstractForm {
 *
 *     @Name("Submit")
 *     private Button btnSubmit = $button(".submit-btn");
 *
 *     public LoginForm(SelenideElement root) {
 *         super(root);
 *     }
 *
 *     @Override
 *     public void submit() {
 *         btnSubmit.click();
 *     }
 * }
 * }</pre>
 *
 * <p>
 * The above class's {@code submit()} method will automatically be intercepted by this aspect,
 * and its execution will be logged in Allure.
 * </p>
 */
@Aspect
public class FormAspects {

    /**
     * Intercepts and logs the execution of the {@code submit()} method in {@link AbstractForm}.
     * <p>
     * This method wraps the execution of the intercepted {@code submit()} method with Allure step
     * logging. It dynamically generates a step name using {@link StepTextProvider} and records the
     * step's success or failure status.
     * </p>
     *
     * @param invocation the join point representing the intercepted method
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* allurium.forms.AbstractForm.submit())")
    @SuppressWarnings("unchecked")
    public void stepSubmit(ProceedingJoinPoint invocation) throws Throwable {
        AbstractForm form = (AbstractForm) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("form_submit", form.getParent(),
                        Pair.of("{element}", form.getUiElementType()),
                        Pair.of("{name}", form.wrappedName())
                ));
        boolean errorStatus = false;
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        try {
            invocation.proceed();
        } catch (Throwable throwable) {
            errorStatus = true;
            throwable.printStackTrace();
            throw throwable;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

}
