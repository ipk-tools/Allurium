package dm.tools;

import com.google.common.base.Strings;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * A utility class for managing and retrieving properties.
 * <p>
 * This class provides methods to access property values from multiple sources:
 * <ul>
 *     <li>System properties (retrieved using JVM's system properties).</li>
 *     <li>Custom profile properties specified via the "profile" system property.</li>
 *     <li>Default properties loaded from the `/application.properties` file.</li>
 * </ul>
 * </p>
 * <p>
 * It supports retrieving properties as various data types (e.g., String, Integer, Boolean)
 * and provides default values if a property is not found.
 * </p>
 *
 * <h3>Key Features:</h3>
 * <ul>
 *     <li>Retrieve property values with default fallbacks.</li>
 *     <li>Load properties from multiple sources.</li>
 *     <li>Parse properties as specific types (String, Integer, Boolean).</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <pre>{@code
 * String value = PropertyLoader.loadProperty("propertyName", "defaultValue");
 * }</pre>
 */
@UtilityClass
@Slf4j
public class PropertyLoader {
    private static final String PROPERTIES_FILE = "/application.properties";
    private static final Properties PROPERTIES = getPropertiesInstance();
    private static final Properties PROFILE_PROPERTIES = getProfilePropertiesInstance();

    /**
     * Returns the value of a system property by its name.
     * If the property is not found, returns the specified default value.
     *
     * @param propertyName the name of the property
     * @param defaultValue the default value to return if the property is not found
     * @return the value of the property by its name, or the default value if not found
     */
    public static String loadSystemPropertyOrDefault(String propertyName, String defaultValue) {
        String propValue = System.getProperty(propertyName);
        return propValue != null ? propValue : defaultValue;
    }

    /**
     * Returns the Integer value of a system property by its name.
     * If the property is not found, returns the specified default value.
     *
     * @param propertyName the name of the property
     * @param defaultValue the default Integer value to return if the property is not found
     * @return the Integer value of the property by its name, or the default value if not found
     */
    public static Integer loadSystemPropertyOrDefault(String propertyName, Integer defaultValue) {
        try {
            return Integer.valueOf(System.getProperty(propertyName, defaultValue.toString()).trim());
        } catch (NumberFormatException ex) {
            log.error("Could not parse value to Integer ", ex.getMessage());
            return defaultValue;
        }
    }

    /**
     * Returns the Boolean value of a system property by its name.
     * If the property is not found, returns the specified default value.
     *
     * @param propertyName the name of the property
     * @param defaultValue the default Boolean value to return if the property is not found
     * @return the Boolean value of the property by its name, or the default value if not found
     */
    public static Boolean loadSystemPropertyOrDefault(String propertyName, Boolean defaultValue) {
        String def = defaultValue.toString();
        String property = loadProperty(propertyName, def);
        return Boolean.parseBoolean(property.trim());
    }

    /**
     * Retrieves a property value by its name from the properties file.
     * If the value is not found, an exception is thrown.
     *
     * @param propertyName the name of the property
     * @return the value of the property
     * @throws IllegalArgumentException if the property is not found in the application.properties file
     */
    public static String loadProperty(String propertyName) {
        String value = tryLoadProperty(propertyName);
        if (null == value) {
            throw new IllegalArgumentException("В файле application.properties не найдено значение по ключу: " + propertyName);
        }
        return value;
    }

    /**
     * Retrieves a property value by its name from the properties file.
     * If the value is not found, the method returns the provided name as the default value.
     *
     * @param propertyNameOrValue the name of the property or the default value
     * @return the value of the property if found, or the input name/value if not
     */
    public static String getPropertyOrValue(String propertyNameOrValue) {
        return loadProperty(propertyNameOrValue, propertyNameOrValue);
    }

    /**
     * Retrieves a property value by its name from the properties file.
     * If the value is not found, returns the specified default value.
     *
     * @param propertyName the name of the property
     * @param defaultValue the default value to return if the property is not found
     * @return the value of the property or the default value
     */
    public static String loadProperty(String propertyName, String defaultValue) {
        String value = tryLoadProperty(propertyName);
        return value != null ? value : defaultValue;
    }

    /**
     * Retrieves an Integer property value by its name from the properties file.
     * If the value is not found, returns the specified default value.
     *
     * @param propertyName the name of the property
     * @param defaultValue the default Integer value to return if the property is not found
     * @return the Integer value of the property or the default value
     */
    public static Integer loadPropertyInt(String propertyName, Integer defaultValue) {
        String value = tryLoadProperty(propertyName);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    /**
     * Helper method to retrieve a property value by its name.
     * The method first checks system properties, then the profile-specific properties file
     * (if the "profile" system property is specified), and finally falls back to the
     * /application.properties file.
     *
     * @param propertyName the name of the property
     * @return the value of the property, or null if not found
     */
    public static String tryLoadProperty(String propertyName) {
        String value = null;
        if (!Strings.isNullOrEmpty(propertyName)) {
            String systemProperty = loadSystemPropertyOrDefault(propertyName, propertyName);
            if (!propertyName.equals(systemProperty)) return systemProperty;

            value = PROFILE_PROPERTIES.getProperty(propertyName);
            if (null == value) {
                value = PROPERTIES.getProperty(propertyName);
            }
        }
        return value;
    }

    /**
     * Helper method to retrieve properties from the /application.properties file.
     *
     * @return a Properties object containing the properties from /application.properties, or an empty object if the file is not found
     */
    @SneakyThrows(IOException.class)
    private static Properties getPropertiesInstance() {
        Properties instance = new Properties();
        try {
            InputStream resourceStream = PropertyLoader.class.getResourceAsStream(PROPERTIES_FILE);
            InputStreamReader inputStream = new InputStreamReader(resourceStream, Charset.forName("UTF-8"));
            instance.load(inputStream);
        } catch(NullPointerException nEx) {
            log.warn("application.properties is not found in test/resources");
        }
        return instance;
    }

    /**
     * Helper method to retrieve properties from a custom application.properties file located at the path
     * specified by the "profile" system property.
     *
     * @return a Properties object containing the properties from the custom file if "profile" is specified,
     * otherwise an empty object
     */
    @SneakyThrows(IOException.class)
    private static Properties getProfilePropertiesInstance() {
        Properties instance = new Properties();
        String profile = System.getProperty("profile", "");
        if (!Strings.isNullOrEmpty(profile)) {
            String path = Paths.get(profile).toString();
            URL url = PropertyLoader.class.getClassLoader().getResource(path);
            try (
                    InputStream resourceStream = url.openStream();
                    InputStreamReader inputStream = new InputStreamReader(resourceStream, Charset.forName("UTF-8"))
            ) {
                instance.load(inputStream);
            }
        }
        return instance;
    }
}


