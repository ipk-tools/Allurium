package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.StepTextProvider;
import allurium.lists.ListWC;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.UUID;

@Aspect
public class WidgetListAspects {

    @Around("execution (* allurium.lists.ListWC.assertSize(Integer))")
    public void stepAssertSize(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        Integer expectedSize = (Integer) invocation.getArgs()[0];
        StepResult stepResult = new StepResult().setName(
                StepTextProvider.getStepText("assert_size",
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{size}", String.valueOf(expectedSize))
                )
        );
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (AssertionError assertionError) {
            errorStatus = true;
            throw assertionError;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertSizeGreaterThan(Integer))")
    public void stepAssertSizeGreaterThan(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        Integer expectedSize = (Integer) invocation.getArgs()[0];
        StepResult stepResult = new StepResult().setName(
                StepTextProvider.getStepText("assert_size_greater_than",
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{size}", String.valueOf(expectedSize))
                )
        );
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (AssertionError assertionError) {
            errorStatus = true;
            throw assertionError;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertSizeEqualOrGreaterThen(Integer))")
    public void stepAssertSizeEqualOrGreaterThen(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        Integer expectedSize = (Integer) invocation.getArgs()[0];
        StepResult stepResult = new StepResult().setName(
                StepTextProvider.getStepText("assert_size_greater_then_or_equal", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{size}", String.valueOf(expectedSize))
                )
        );
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable e) {
            errorStatus = true;
            throw new Throwable();
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertSizeLessThan(Integer))")
    public void stepAssertSizeLessThan(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        Integer expectedSize = (Integer) invocation.getArgs()[0];
        StepResult stepResult = new StepResult().setName(
                StepTextProvider.getStepText("assert_size_less_than", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{size}", String.valueOf(expectedSize))
                )
        );
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable e) {
            errorStatus = true;
            throw new Throwable();
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertEmpty())")
    public void stepAssertEmpty(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("list_assert_empty", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName())
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertVisible())")
    public void stepAssertVisible(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("list_assert_visible", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName())
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertNotVisible())")
    public void stepAssertNotVisible(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("list_assert_not_visible", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName())
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertHasItem(..))")
    public void stepAssertHasItem(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        String itemId = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("list_assert_has_item", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{id}", itemId)
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertHasNotItem(String))")
    public void stepAssertHasNotItem(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        String itemId = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("list_assert_has_not_item", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{id}", itemId)
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
            //Assertions.assertThat(listWC.hasItem(itemId)).isFalse();
        } catch (Throwable assertionException) {
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertHasItemsWithText(String))")
    public void assertHasItemsWithText(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        String textPattern = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("assert_has_items_with_text", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{text}", textPattern)
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    @Around("execution (* allurium.lists.ListWC.assertHasNoItemsWithText(String))")
    public void stepAssertHasNoItemsWithText(ProceedingJoinPoint invocation) throws Throwable {
        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
        String textPattern = (String) invocation.getArgs()[0];
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("assert_has_no_items_with_text", listWC.getParent(),
                        Pair.of("{name}", listWC.wrappedName()),
                        Pair.of("{text}", textPattern)
                ));
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable assertionException) {
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }

    // realized in ListWC, the step shows only when written in main class, need to investigate
//    @Around("execution (* allurium.lists.ListWC.should(..))")
//    public void stepShould(ProceedingJoinPoint invocation) throws Throwable {
//        ListWC<?> listWC = (ListWC<?>) invocation.getThis();
//        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
//        StepResult stepResult = new StepResult()
//                .setName(StepTextProvider.getStepText("list_should_conditions", listWC.getParent(),
//                        Pair.of("{name}", listWC.wrappedName())
//                ));
//        AsyncAllureLogger.stopStepAsync();
//        boolean errorStatus = false;
//        try {
//            errorStatus = !(boolean) invocation.proceed();
//        } catch (Throwable assertionException) {
//            assertionException.printStackTrace();
//            errorStatus = true;
//            throw assertionException;
//        } finally {
//            if (errorStatus)
//                stepResult.setStatus(Status.FAILED);
//            else {
//                stepResult.setStatus(Status.PASSED);
//            }
//            AsyncAllureLogger.stopStepAsync();
//        }
//    }

}
