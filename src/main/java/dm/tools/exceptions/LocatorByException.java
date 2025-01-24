package dm.tools.exceptions;

import dm.tools.annotations.ListLocator;
import dm.tools.annotations.Locator;
import dm.tools.lists.ListWC;
import dm.tools.primitives.UIElement;

import java.lang.reflect.Field;

public class LocatorByException extends Exception {

    public LocatorByException(Field uiElement) {
        super(defineReason(uiElement));
    }

    public LocatorByException(UIElement uiElement) {
        super(defineReason(uiElement));
    }

    public LocatorByException(ListWC listWC) {
        super(defineReason(listWC));
    }

    private static String defineReason(Field uiElement) {
        Locator locator = uiElement.getAnnotation(Locator.class);
        String xpath = locator.xpath();
        String css = locator.css();
        String className = locator.className();
        return String.format("More then one locator defined for var '%s' in '%s' class",
                uiElement.getType().getName(), uiElement.getDeclaringClass());
    }

    private static String defineReason(UIElement uiElement) {
        return String.format("More then one locator defined for var '%s' in '%s' class",
                uiElement.getUiElementName(), uiElement.getClass());
    }

    private static String defineReason(ListWC listWC) {
        ListLocator listLocator = listWC.getClass().getDeclaredAnnotationsByType(ListLocator.class)[0];
        return String.format("More then one locator defined for ListWC '%s' in '%s' class",
                listWC.getUiElementName(), listWC.getClass());
    }
}
