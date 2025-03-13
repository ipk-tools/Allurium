package allurium.aspects;

import allurium.interfaces.WebElementMeta;
import allurium.operators.ElementsInvoker;
import allurium.AbstractWidget;
import allurium.exceptions.LocatorByException;
import allurium.exceptions.WidgetFormalizationException;
import allurium.operators.ListWCTypeBuilder;
import allurium.operators.LocatorBuilder;
import allurium.operators.WebElementMetaDataBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Optional;

@Aspect
public class WidgetAspects {
    private static final Logger logger = LoggerFactory.getLogger(WidgetAspects.class);

    @After("within(@allurium.annotations.Widget *) && execution(*.new(..))")
    public void initializeWidgetElements(JoinPoint joinPoint)
            throws WidgetFormalizationException, IllegalAccessException, LocatorByException {

        if (joinPoint.getThis() instanceof AbstractWidget) {
            ElementsInvoker.invokeNotInstancedElements(joinPoint.getThis());
            ElementsInvoker.invokeElementsWithChainedLocators(joinPoint.getThis());
            WebElementMetaDataBuilder.buildMeta(joinPoint.getThis());
            LocatorBuilder.buildRootForInstantiatedElements(joinPoint.getThis());
        } else {
            throw new WidgetFormalizationException(
                    String.format("Widget %s doesn't inherit 'AbstractWidget'",
                            joinPoint.getThis().getClass().getName()));
        }

        // Collecting fields of the widget up to the very ancestor
        Iterable<Field> fields = WebElementMetaDataBuilder
                .getFieldsUpTo(joinPoint.getThis().getClass(), AbstractWidget.class);

        // Searching in collected widget fields ListWC(s) and defining classes and other data of list elements
        ListWCTypeBuilder.buildType(joinPoint, fields);
        LocatorBuilder.applyListItemLocatorForListWC(joinPoint, fields);

        // Defining and applying parent for each element of the widget
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.get(joinPoint.getThis()) instanceof WebElementMeta) {
                    WebElementMeta widget = (WebElementMeta) field.get(joinPoint.getThis());
                    widget.setParent(Optional.ofNullable((AbstractWidget) joinPoint.getThis()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void buildParents(Object obj) {
        Iterable<Field> fields = WebElementMetaDataBuilder
                .getFieldsUpTo(obj.getClass(), AbstractWidget.class);

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.get(obj) instanceof WebElementMeta) {
                    WebElementMeta widget = (WebElementMeta) field.get(obj);
                    widget.setParent(Optional.ofNullable((AbstractWidget) obj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Before("get(@allurium.annotations.LocatorChain * *)")
    public void initChildElementLocatorOnAppeal(JoinPoint joinPoint) throws LocatorByException, IllegalAccessException {
        logger.debug("[locator]: composing chained locator for the " + joinPoint.getThis().getClass().getName());
        LocatorBuilder.buildRootForChainedInstantiatedElement(joinPoint.getThis());
    }

    @Before("get(@allurium.annotations.ListLocatorChain * *)")
    public void initListWithChainedLocatorOnAppeal(JoinPoint joinPoint) throws Exception {
        logger.debug("[locator]: composing chained locator for the " + joinPoint.getThis().getClass().getName());
        LocatorBuilder.applyListItemLocatorChainForListWC(joinPoint.getThis());
    }

    /*
    @After("execution (* allurium.lists.ListWC.refresh())")
    public void initializeWithListLocatorsWithinListOnRefresh(JoinPoint joinPoint) {
        ListWC<?> listWC = (ListWC<?>) joinPoint.getThis();
        System.out.println(listWC.getGenericTypeName());
        listWC.getComponents().forEach(listComponent -> {
            try {
                ElementsInvoker.invokeElementsWithLocatorsWithinList(listComponent);
                WebElementMetaDateBuilder.buildMeta(listComponent);
                LocatorBuilder.buildLocatorForElementsInLists(listComponent);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (LocatorByException e) {
                e.printStackTrace();
            }
        });
    }
     */

}