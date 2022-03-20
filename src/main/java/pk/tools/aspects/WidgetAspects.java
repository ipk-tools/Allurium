package pk.tools.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import pk.tools.AbstractWidget;
import pk.tools.composers.ListWCTypeBuilder;
import pk.tools.composers.WebElementMetaDateBuilder;
import pk.tools.interfaces.WebElementMeta;

import java.lang.reflect.Field;

@Aspect
public class WidgetAspects {

    @After("within(@pk.tools.annotations.Widget *) && execution(*.new(..))")
    public void initializeWidgetElements(JoinPoint joinPoint) {
        if (joinPoint.getThis() instanceof AbstractWidget)
            WebElementMetaDateBuilder.buildMeta(joinPoint.getThis());

        Iterable<Field> fields = WebElementMetaDateBuilder
                .getFieldsUpTo(joinPoint.getThis().getClass(), AbstractWidget.class);

        ListWCTypeBuilder.buildType(joinPoint, fields);

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.get(joinPoint.getThis()) instanceof WebElementMeta) {
                    WebElementMeta widget = (WebElementMeta) field.get(joinPoint.getThis());
                    widget.setParent((AbstractWidget) joinPoint.getThis());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}