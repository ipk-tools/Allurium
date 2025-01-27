package allurium.aspects;

import allurium.annotations.PageObject;
import allurium.operators.ElementsInvoker;
import allurium.operators.ListWCTypeBuilder;
import allurium.operators.LocatorBuilder;
import allurium.operators.WebElementMetaDataBuilder;
import allurium.primitives.UIElement;
import allurium.AbstractWidget;
import allurium.exceptions.LocatorByException;
import allurium.exceptions.PageObjectInPageObjectException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Field;

import static allurium.operators.WebElementMetaDataBuilder.getFieldsUpTo;

@Aspect
public class PageAspects {

    @After("within(@allurium.annotations.PageObject *) && execution(*.new(..))")
    public void initializePageElements(JoinPoint joinPoint) throws IllegalAccessException, LocatorByException, PageObjectInPageObjectException {
        detectPageObjectsInPageObjectAndPreventCompiling(joinPoint.getThis());
        ElementsInvoker.invokeNotInstancedElements(joinPoint.getThis());
        WebElementMetaDataBuilder.buildMeta(joinPoint.getThis());
        LocatorBuilder.buildRootForInstantiatedElements(joinPoint.getThis());

        Iterable<Field> fields = getFieldsUpTo(joinPoint.getThis().getClass(), AbstractWidget.class);

        ListWCTypeBuilder.buildType(joinPoint, fields);
        LocatorBuilder.applyListItemLocatorForListWC(joinPoint, fields);
    }

    //todo:
    public static void detectPageObjectsInPageObjectAndPreventCompiling(Object pageObject) throws PageObjectInPageObjectException {
        Iterable<Field> fields = getFieldsUpTo(pageObject.getClass(), UIElement.class);

        for (Field field : fields) {
            if (field.isAnnotationPresent(PageObject.class)) {
                throw new PageObjectInPageObjectException(String.format(
                        "Page object [%s] contains other page object(s) within",
                        pageObject.getClass().toString() )
                );
            }
        }
    }

}
