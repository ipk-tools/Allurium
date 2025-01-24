package dm.tools.exceptions;

import dm.tools.primitives.UIElement;

public class StepFailException extends Throwable {

    public StepFailException(UIElement uiElement, String stepText) {
        super("failed step [" + stepText +"]  for element - " + uiElement.getUiElementName());
    }
}
