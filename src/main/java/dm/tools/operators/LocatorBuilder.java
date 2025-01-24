package dm.tools.operators;

import dm.tools.annotations.ListLocator;
import dm.tools.annotations.ListLocatorChain;
import dm.tools.annotations.Locator;
import dm.tools.annotations.LocatorChain;
import dm.tools.exceptions.ListLocatorException;
import dm.tools.exceptions.LocatorByException;
import dm.tools.lists.ListWC;
import dm.tools.primitives.UIElement;
import org.aspectj.lang.JoinPoint;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static dm.tools.operators.WebElementMetaDataBuilder.getFieldsUpTo;

public class LocatorBuilder {

    public static void buildRootForInstantiatedElements(Object obj) throws IllegalAccessException, LocatorByException {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), UIElement.class);

        for (Field field : fields) {
            if (field.isAnnotationPresent(Locator.class)) {
                field.setAccessible(true);
                if (field.get(obj) != null) {
                    Locator locator = field.getAnnotation(Locator.class);
                    int filledSearchMethodsCounter = 0;
                    if (!locator.id().equals("")) filledSearchMethodsCounter++;
                    if (!locator.css().equals("")) filledSearchMethodsCounter++;
                    if (!locator.xpath().equals("")) filledSearchMethodsCounter++;
                    if (!locator.className().equals("")) filledSearchMethodsCounter++;

                    if (filledSearchMethodsCounter > 1)
                        throw new LocatorByException(field);

                    if (UIElement.class.isAssignableFrom(field.getType())) {
                        UIElement abstractElement = (UIElement) field.get(obj);
                        if (!locator.id().equals("")) {
                            abstractElement.setRoot(By.id(locator.id()));
                        } else if (!locator.xpath().equals("")) {
                            abstractElement.setRoot(By.xpath(locator.xpath()));
                        } else if (!locator.css().equals("")) {
                            abstractElement.setRoot(By.cssSelector(locator.css()));
                        } else if (!locator.className().equals("")) {
                            abstractElement.setRoot(By.className(locator.className()));
                        }
                    }
                }
            }
        }
    }

    public static void buildRootForChainedInstantiatedElement(Object obj) throws LocatorByException,
                                                                                 IllegalAccessException
    {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), UIElement.class);

        for (Field field : fields) {
            if (field.isAnnotationPresent(LocatorChain.class)) {
                field.setAccessible(true);
                if (field.get(obj) != null) {
                    LocatorChain locator = field.getAnnotation(LocatorChain.class);
                    int filledSearchMethodsCounter = 0;
                    if (!locator.id().equals("")) filledSearchMethodsCounter++;
                    if (!locator.css().equals("")) filledSearchMethodsCounter++;
                    if (!locator.xpath().equals("")) filledSearchMethodsCounter++;
                    if (!locator.className().equals("")) filledSearchMethodsCounter++;

                    if (filledSearchMethodsCounter > 1)
                        throw new LocatorByException(field);

                    if (UIElement.class.isAssignableFrom(field.getType())) {
                        UIElement abstractElement = (UIElement) field.get(obj);
                        if (!locator.xpath().equals("")) {
                            abstractElement.setRoot(abstractElement.getParent().get().getRoot().find(By.xpath(locator.xpath())));
                        } else if (!locator.css().equals("")) {
                            abstractElement.setRoot(abstractElement.getParent().get().getRoot().find(locator.css()));
                        } else if (!locator.className().equals("")) {
                            abstractElement.setRoot(abstractElement.getParent().get().getRoot().find(By.className(locator.className())));
                        }
                    }
                }
            }
        }
    }

    public static void buildLocatorForElementsInLists(Object obj) throws LocatorByException, IllegalAccessException {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), UIElement.class);

        for (Field field : fields) {
            if (field.isAnnotationPresent(ListLocator.class)) {
                field.setAccessible(true);
                if (field.get(obj) != null) {
                    ListLocator locator = field.getAnnotation(ListLocator.class);
                    int filledSearchMethodsCounter = 0;
                    if (!locator.css().equals("")) filledSearchMethodsCounter++;
                    if (!locator.xpath().equals("")) filledSearchMethodsCounter++;
                    if (!locator.className().equals("")) filledSearchMethodsCounter++;

                    if (filledSearchMethodsCounter > 1)
                        throw new LocatorByException(field);

                    if (UIElement.class.isAssignableFrom(field.getType())) {
                        UIElement abstractElement = (UIElement) field.get(obj);
                        if (!locator.xpath().equals("")) {
                            abstractElement.setRoot(abstractElement.getParent().get().getRoot().find(By.xpath(locator.xpath())));
                        } else if (!locator.css().equals("")) {
                            abstractElement.setRoot(abstractElement.getParent().get().getRoot().find(locator.css()));
                        } else if (!locator.className().equals("")) {
                            abstractElement.setRoot(abstractElement.getParent().get().getRoot().find(By.className(locator.className())));
                        }
                    }
                }
            }
        }
    }

    public static void applyListItemLocatorForListWC(JoinPoint joinPoint, Iterable<Field> fields)
            throws LocatorByException, IllegalAccessException {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().equals(ListWC.class) && field.isAnnotationPresent(ListLocator.class)) {
                ListWC<?> listWC = (ListWC<?>) field.get(joinPoint.getThis());
                ListLocator listLocator = field.getAnnotation(ListLocator.class);
                int filledSearchMethodsCounter = 0;
                if (!listLocator.css().equals("")) filledSearchMethodsCounter++;
                if (!listLocator.xpath().equals("")) filledSearchMethodsCounter++;
                if (!listLocator.className().equals("")) filledSearchMethodsCounter++;

                if (filledSearchMethodsCounter > 1)
                    throw new LocatorByException(field);

                if (!listLocator.xpath().equals("")) {
                    listWC.setSourceElements($$x(listLocator.xpath()));
                } else if (!listLocator.css().equals("")) {
                    listWC.setSourceElements($$(listLocator.css()));
                } else if (!listLocator.className().equals("")) {
                    listWC.setSourceElements($$("."+listLocator.className()));
                }
            }
        }
    }

    public static void applyListItemLocatorChainForListWC(Object obj) throws Exception {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), UIElement.class);

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().equals(ListWC.class) && field.isAnnotationPresent(ListLocatorChain.class)) {
                ListWC<?> listWC = (ListWC<?>) field.get(obj);
                ListLocator listLocator = field.getAnnotation(ListLocator.class);
                ListLocatorChain listItemLocatorChain = field.getAnnotation(ListLocatorChain.class);
                new ListLocatorException(listWC, field).throwExceptionIfNeeded();
                int filledSearchMethodsCounter = 0;
                if (!listItemLocatorChain.css().equals("")) filledSearchMethodsCounter++;
                if (!listItemLocatorChain.xpath().equals("")) filledSearchMethodsCounter++;
                if (!listItemLocatorChain.className().equals("")) filledSearchMethodsCounter++;

                if (filledSearchMethodsCounter > 1)
                    throw new LocatorByException(field);

                if (!listItemLocatorChain.xpath().equals("")) {
                    listWC.setSourceElements(listWC.getParent().get().getRoot().$$x(listItemLocatorChain.xpath()));
                } else if (!listItemLocatorChain.css().equals("")) {
                    listWC.setSourceElements(listWC.getParent().get().getRoot().$$(listItemLocatorChain.css()));
                } else if (!listItemLocatorChain.className().equals("")) {
                    listWC.setSourceElements(listWC.getParent().get().getRoot().$$("."+listItemLocatorChain.className()));
                }
            }
        }
    }

}
