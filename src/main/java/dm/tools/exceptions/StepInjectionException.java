package dm.tools.exceptions;

import dm.tools.primitives.UIElement;

public class StepInjectionException extends Throwable {

    public StepInjectionException(UIElement uiElement, String stepText) {
        super("failed trying to integrate step='" + stepText +"' into " + uiElement.getUiElementName() + " method");
    }
}
