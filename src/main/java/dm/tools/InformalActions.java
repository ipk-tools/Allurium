package dm.tools;

import java.util.function.Predicate;

public class InformalActions {

    public static <T> void retryActionUntil(Runnable action, T item, Predicate<T> until, int retries) {
        try {
            if (until.test(item)) {
                if (retries > 0) {
                    action.run();
                } else {
                    retryActionUntil(action, item, until, retries - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
