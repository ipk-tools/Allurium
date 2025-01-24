package dm.tools.aspects;

import dm.tools.AbstractWidget;
import dm.tools.annotations.Name;
import dm.tools.annotations.PageObject;
import dm.tools.exceptions.LocatorByException;
import dm.tools.exceptions.PageObjectInPageObjectException;
import dm.tools.operators.*;
import dm.tools.primitives.UIElement;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Field;

import static dm.tools.operators.WebElementMetaDataBuilder.getFieldsUpTo;

@Aspect
public class PageAspects {

    @After("within(@dm.tools.annotations.PageObject *) && execution(*.new(..))")
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
