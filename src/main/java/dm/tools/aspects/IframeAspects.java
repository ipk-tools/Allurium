package dm.tools.aspects;

import com.codeborne.selenide.WebDriverRunner;
import dm.tools.AbstractWidget;
import dm.tools.windows.Iframe;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class IframeAspects {

    private static final Logger logger = LoggerFactory.getLogger(WidgetAspects.class);

//    @Around("execution(* dm.tools.windows..Iframe+.*(..)) && !execution(dm.tools.windows..Iframe+.new(..))")
//    public void switchFrameOnAppeal(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            System.out.println("Switching to iframe before method: " + joinPoint.getSignature());
//
//            Iframe iframe = (Iframe) joinPoint.getThis();
//            WebDriverRunner.getWebDriver().switchTo().frame();
//            joinPoint.proceed();
//            WebDriverRunner.getWebDriver().switchTo().defaultContent();
//
//            System.out.println("Switched to iframe after method: " + joinPoint.getSignature());
//        } catch (Throwable throwable) {
//            logger.error("Error switching iframe during the method execution: " + joinPoint.getSignature(), throwable);
//            throw throwable;
//        }
//    }
}
