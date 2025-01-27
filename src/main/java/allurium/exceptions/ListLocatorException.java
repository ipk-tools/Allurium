package allurium.exceptions;

import allurium.annotations.ListLocator;
import allurium.annotations.ListLocatorChain;
import allurium.lists.ListWC;

import java.lang.reflect.Field;

public class ListLocatorException extends Exception {
    ListLocator listLocator;
    ListLocatorChain listItemLocatorChain;
    ListWC<?> listWC;

    public ListLocatorException(ListWC<?> listWC, Field field) {
        this.listWC = listWC;
        ListLocator listLocator = field.getAnnotation(ListLocator.class);
        ListLocatorChain listItemLocatorChain = field.getAnnotation(ListLocatorChain.class);
    }

    public void throwExceptionIfNeeded() throws Exception {
        int counter = 0;
        if (listLocator != null) counter++;
        if (listItemLocatorChain != null) counter++;
        if (counter > 1) {
            throw new Exception(listWC.getUiElementName() + " list has both 'ListLocator' and " +
                    "'ListLocatorChain' at the same time");
        }
    }
}
