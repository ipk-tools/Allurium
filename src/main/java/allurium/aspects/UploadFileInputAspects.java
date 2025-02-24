package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.StepTextProvider;
import allurium.inputs.UploadField;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.File;
import java.util.UUID;

/**
 * Aspect for adding Allure reporting capabilities to the {@link UploadField} class.
 * <p>
 * This aspect provides enhanced logging and reporting for file upload actions performed on {@link UploadField}
 * elements. It wraps specific methods to log their execution details as steps in Allure reports.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Wraps the file upload methods of the {@link UploadField} class.</li>
 *     <li>Logs detailed information about file upload actions in Allure reports, including file path and field name.</li>
 *     <li>Handles both success and failure scenarios, updating the Allure report with the appropriate status.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Enhances test visibility by logging file upload actions and their results in Allure.</li>
 *     <li>Standardizes reporting for file upload interactions across test cases.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // File upload interaction in a test
 * UploadField uploadField = new UploadField("input[type='file']");
 * uploadField.uploadFile("path/to/file.txt");  // Logs "Upload file: path/to/file.txt" in Allure
 * }
 * </pre>
 */
@Aspect
public class UploadFileInputAspects {

    /**
     * Wraps the {@link UploadField#uploadFile(String)} method to log the file upload action in Allure.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.UploadField.uploadFile(String))")
    public void stepUploadFileViaPath(ProceedingJoinPoint invocation) throws Throwable {
        UploadField uploadField = (UploadField) invocation.getThis();
        String filePath = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("upload_file", uploadField.getParent(),
                        Pair.of("{name}", uploadField.wrappedName()),
                        Pair.of("{path}", filePath)
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    /**
     * Wraps the {@link UploadField#uploadFile(File)} method to log the file upload action in Allure.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.UploadField.uploadFile(File))")
    public void stepUploadFile(ProceedingJoinPoint invocation) throws Throwable {
        UploadField uploadField = (UploadField) invocation.getThis();
        File file = (File) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("upload_file", uploadField.getParent(),
                        Pair.of("{name}", uploadField.wrappedName()),
                        Pair.of("{path}", file.getAbsolutePath())
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

}
