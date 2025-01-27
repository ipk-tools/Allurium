package allurium;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.StepResult;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class AllureUtils {

    public static void attachElementScreenshotToStep(SelenideElement element, String name, StepResult stepResult) {
        ArrayList<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment();
        attachment.setType("image/png");
        attachment.setName(name);
        attachment.setSource(element.screenshot().getAbsolutePath());
        attachments.add(attachment);
        stepResult.setAttachments(attachments);
    }
}
