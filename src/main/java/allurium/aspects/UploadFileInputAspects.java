package allurium.aspects;

import allurium.StepTextProvider;
import allurium.inputs.UploadField;
import allurium.utilities.AllureStepHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.io.File;

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
    @SuppressWarnings("unchecked")
    public void stepUploadFileViaPath(ProceedingJoinPoint invocation) throws Throwable {
        UploadField uploadField = (UploadField) invocation.getThis();
        String filePath = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "upload_file",
                uploadField.getParent(),
                Pair.of("{name}", uploadField.wrappedName()),
                Pair.of("{path}", filePath)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Wraps the {@link UploadField#uploadFile(File)} method to log the file upload action in Allure.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.UploadField.uploadFile(File))")
    @SuppressWarnings("unchecked")
    public void stepUploadFile(ProceedingJoinPoint invocation) throws Throwable {
        UploadField uploadField = (UploadField) invocation.getThis();
        File file = (File) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "upload_file",
                uploadField.getParent(),
                Pair.of("{name}", uploadField.wrappedName()),
                Pair.of("{path}", file.getAbsolutePath())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
