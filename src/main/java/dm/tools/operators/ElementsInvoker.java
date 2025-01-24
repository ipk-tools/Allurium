package dm.tools.operators;

import dm.tools.AbstractWidget;
import dm.tools.annotations.ListLocator;
import dm.tools.annotations.Locator;
import dm.tools.annotations.LocatorChain;
import dm.tools.primitives.UIElement;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import static com.codeborne.selenide.Selenide.$;
import static dm.tools.operators.WebElementMetaDataBuilder.getFieldsUpTo;

@Slf4j
public class ElementsInvoker {

    /**
     * Method search not instanced elements in marked @PageObject or @Widget entities
     * and makes a new instance of widget with no argument constructor()
     * @param obj
     */
    public static void invokeNotInstancedElements(Object obj) throws IllegalAccessException {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), UIElement .class);

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(obj) == null && field.isAnnotationPresent(Locator.class)) {
                //if (field.get(obj) == null && field.getType().getSuperclass().isAssignableFrom(AbstractWidget.class)) {
                try {
                    Type type = field.getGenericType();
                    Class<?> clazz = Class.forName(type.getTypeName());
                    Constructor<?> ctr = (Constructor<?>) clazz.getConstructor();
                    Object newInstance = ctr.newInstance();
                    field.set(obj, newInstance);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | InstantiationException widgetInvocationError)
                {
                    log.warn(widgetInvocationError.getMessage());
                    widgetInvocationError.printStackTrace();
                }
            } else if (field.get(obj) == null && !field.isAnnotationPresent(Locator.class) && !field.isAnnotationPresent(LocatorChain.class)){
                log.warn("found not initialized element without any @Locator  on " + field.getDeclaringClass());
            }
        }
    }

    /**
     * Method search not instanced elements with @LocatorChain in marked @PageObject or @Widget entities
     * and makes a new instance of them
     * @param obj
     */
    public static void invokeElementsWithChainedLocators(Object obj) throws IllegalAccessException {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), UIElement.class);

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(obj) == null && field.isAnnotationPresent(LocatorChain.class)) {

                // for primitive with default constructor(SelenideElement)
                //try {
                //    Type type = field.getGenericType();
                //    Class<?> clazz = Class.forName(type.getTypeName());
                //    Constructor<?> ctr = (Constructor<?>) clazz.getConstructor(SelenideElement.class);
                //    Object newInstance = ctr.newInstance($("body")); // stub locator, changes on appeal
                //    field.set(obj, newInstance);
                //} catch (ClassNotFoundException
                //        | NoSuchMethodException
                //        | InvocationTargetException
                //        | InstantiationException elementInvocationException)
                //{
                //    log.warn(elementInvocationException.getMessage());
                //makingInstanceError.printStackTrace();

                // for Widget who may have different constructor, using constructor()
                try {
                    Type type = field.getGenericType();
                    Class<?> clazz = Class.forName(type.getTypeName());
                    Constructor<?> ctr = (Constructor<?>) clazz.getConstructor();
                    Object newInstance = ctr.newInstance();
                    field.set(obj, newInstance);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | InstantiationException widgetInvocationException)
                {
                    log.warn(widgetInvocationException.getMessage());
                    widgetInvocationException.printStackTrace();
                }
//                }

            } else if (field.get(obj) == null && !field.isAnnotationPresent(Locator.class)  && !field.isAnnotationPresent(LocatorChain.class)){
                log.warn("found not initialized element without any @LocatorChain  on " + field.getDeclaringClass());
            }
        }
    }

    /**
     * Method search not instanced elements with @ListItemLocator within lists and tryies to make an instance.
     * @param obj
     * @throws IllegalAccessException
     */
    // todo: May be this is not the best way to invoke. Incomplete currently
    public static void invokeElementsWithLocatorsWithinList(Object obj) throws IllegalAccessException {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), AbstractWidget.class);

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(obj) == null && field.isAnnotationPresent(ListLocator.class)) {
                try {
                    Type type = field.getGenericType();
                    Class<?> clazz = Class.forName(type.getTypeName());
                    Constructor<?> ctr = (Constructor<?>) clazz.getConstructor();
                    Object newInstance = ctr.newInstance();
                    field.set(obj, newInstance);
                } catch (ClassNotFoundException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | InstantiationException widgetInvocationException)
                {
                    log.warn(widgetInvocationException.getMessage());
                    widgetInvocationException.printStackTrace();
                }
            }
        }
    }

}