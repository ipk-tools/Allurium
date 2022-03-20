package pk.tools.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import pk.tools.AbstractWidget;
import pk.tools.ListWC;
import pk.tools.composers.ListWCTypeBuilder;
import pk.tools.composers.WebElementMetaDateBuilder;
import pk.tools.examples.pages.BasePage;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Aspect
public class PageAspects {

    @After("within(@pk.tools.annotations.PageObject *) && execution(*.new(..))")
    public void initializePageElements(JoinPoint joinPoint) {
        if (joinPoint.getThis() instanceof BasePage)
            WebElementMetaDateBuilder.buildMeta(joinPoint.getThis());

        Iterable<Field> fields = WebElementMetaDateBuilder
                .getFieldsUpTo(joinPoint.getThis().getClass(), AbstractWidget.class);

        ListWCTypeBuilder.buildType(joinPoint, fields);
    }
}
