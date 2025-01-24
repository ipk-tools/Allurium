package dm.tools;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Accessors(fluent = true)
public class AlluriumConfig {

    private static final Properties alluriumProperties = new Properties();

    @Getter private static final String localization;
    @Getter private static final String stepDetailing;
    @Getter private static final String highlighterStart;
    @Getter private static final String highlighterEnd;
    @Getter private static final Integer retryAmount;
    @Getter private static final Long retryIntervalMs;

    static {
        try (InputStream input = getConfigInputStream()) {
            if (input == null) {
                System.out.println("[AlluriumConfig] No allurium-default.properties file found on classpath!");
            } else {
                Properties testProps = new Properties();
                testProps.load(input);

                System.out.println("[AlluriumConfig] Dumping user allurium-default.properties:");
                testProps.list(System.out);

                alluriumProperties.putAll(testProps);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        localization = loadProperty("localization", "en");
        stepDetailing = loadProperty("step.detailing", "2");
        highlighterStart = loadProperty("highlighter.start", "");
        highlighterEnd = loadProperty("highlighter.end", "");
        retryAmount = Integer.parseInt(loadProperty("retry.amount", "5"));
        retryIntervalMs = Long.parseLong(loadProperty("retry.interval.ms", "500"));
    }

    private static InputStream getConfigInputStream() {
        // Try to find allurium.properties on the classpath first
        InputStream userInputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("allurium.properties");
        if (userInputStream != null) {
            // LOG: Found user override on the classpath
            System.out.println("[AlluriumConfig] Using user-provided allurium.properties from the classpath");
            System.out.println(AlluriumConfig.highlighterEnd());
            return userInputStream;
        }

        // Otherwise fallback to the default inside our library
        System.out.println("[AlluriumConfig] Using default allurium-default.properties from the library");
        System.out.println(AlluriumConfig.highlighterEnd()+ ">>>>>>>");
        return AlluriumConfig.class.getResourceAsStream("/allurium-default.properties");
    }

    private static String loadProperty(String property, String def) {
        String value = alluriumProperties.getProperty(property);
        return (value != null && !value.isEmpty()) ? value : def;
    }
}
