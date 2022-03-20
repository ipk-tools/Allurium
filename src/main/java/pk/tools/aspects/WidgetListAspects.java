package pk.tools.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class WidgetListAspects {

    @Before("execution(* pk.tools.ListWC.add(..))")
    public void setGenericWidgetType(JoinPoint joinPoint) {
    }
}
