package allurium.inputs;

import allurium.primitives.UIElement;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

import java.io.File;

/**
 * Represents an upload field (`<input type="file">`) in the UI, extending {@link UIElement}.
 * <p>
 * This class provides utility methods for interacting with file upload elements, allowing files to be uploaded via
 * file paths or {@link File} objects.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Supports file uploads using absolute file paths or Java {@link File} objects.</li>
 *     <li>Extends {@link UIElement} to inherit generic element interaction capabilities.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates behavior specific to file upload fields.</li>
 *     <li>Simplifies file upload interactions in tests.</li>
 * </ul>
 */
public class UploadField extends UIElement {

    /**
     * Default constructor. Initializes the element type as "upload_input".
     */
    public UploadField() {
        setUiElementType(ElementType.UPLOAD_INPUT.getType());
    }

    /**
     * Constructor that initializes an upload field using a Selenium {@link By} locator.
     *
     * @param rootLocator the Selenium locator for the upload field
     */
    public UploadField(By rootLocator) {
        super(rootLocator);
        setUiElementType(ElementType.UPLOAD_INPUT.getType());
    }

    /**
     * Constructor that initializes an upload field using a Selenium {@link By} locator and a name.
     *
     * @param rootLocator the Selenium locator for the upload field
     * @param name        the name of the upload field
     */
    public UploadField(By rootLocator, String name) {
        super(rootLocator, name);
        setUiElementType(ElementType.UPLOAD_INPUT.getType());
    }

    /**
     * Constructor that initializes an upload field using a Selenide locator string.
     *
     * @param selenideSelector the Selenide locator as a string
     */
    public UploadField(String selenideSelector) {
        super(selenideSelector);
        setUiElementType(ElementType.UPLOAD_INPUT.getType());
    }

    /**
     * Constructor that initializes an upload field using a Selenide locator string and a name.
     *
     * @param selenideSelector the Selenide locator as a string
     * @param name             the name of the upload field
     */
    public UploadField(String selenideSelector, String name) {
        super(selenideSelector, name);
        setUiElementType(ElementType.UPLOAD_INPUT.getType());
    }

    /**
     * Constructor that initializes an upload field using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the upload field
     */
    public UploadField(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.UPLOAD_INPUT.getType());
    }

    /**
     * Constructor that initializes an upload field using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the upload field
     * @param name            the name of the upload field
     */
    public UploadField(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.UPLOAD_INPUT.getType());
    }

    /**
     * Creates an upload field instance using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the upload field
     * @return a new {@link UploadField} instance
     */
    public static UploadField $uploadField(By locator) {
        return new UploadField(locator);
    }

    /**
     * Creates an upload field instance using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the upload field
     * @param name    the name of the upload field
     * @return a new {@link UploadField} instance
     */
    public static UploadField $uploadField(By locator, String name) {
        return new UploadField(locator, name);
    }

    /**
     * Creates an upload field instance using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     * @return a new {@link UploadField} instance
     */
    public static UploadField $uploadField(String selenideLocator) {
        return new UploadField(selenideLocator);
    }

    /**
     * Creates an upload field instance using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the upload field
     * @return a new {@link UploadField} instance
     */
    public static UploadField $uploadField(String selenideLocator, String name) {
        return new UploadField(selenideLocator, name);
    }

    /**
     * Creates an upload field instance using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the upload field
     * @return a new {@link UploadField} instance
     */
    public static UploadField $uploadField(SelenideElement selenideElement) {
        return new UploadField(selenideElement);
    }

    /**
     * Creates an upload field instance using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the upload field
     * @param name            the name of the upload field
     * @return a new {@link UploadField} instance
     */
    public static UploadField $uploadField(SelenideElement selenideElement, String name) {
        return new UploadField(selenideElement, name);
    }

    /**
     * Creates an upload field instance using an XPath string.
     *
     * @param xpath the XPath string for the upload field
     * @return a new {@link UploadField} instance
     */
    public static UploadField _$uploadField(String xpath) {
        return new UploadField(By.xpath(xpath));
    }

    /**
     * Uploads a file to the upload field using an absolute file path.
     * <p>
     * <p><b>Step: Processed by Aspect</b></p>
     * </p>
     *
     * @param filePath the absolute path to the file
     */
    public void uploadFile(String filePath) {
        root.sendKeys(filePath);
    }

    /**
     * Uploads a file to the upload field using a {@link File} object.
     *
     * @param file the {@link File} object to upload
     */
    public void uploadFile(File file) {
        root.sendKeys(file.getAbsolutePath());
    }
}
