package pk.tools.composers;

import org.aspectj.lang.JoinPoint;
import pk.tools.ListWC;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ListWCTypeBuilder {

    public static void buildType(JoinPoint joinPoint, Iterable<Field> fields) {
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().equals(ListWC.class)) {
                    ListWC<?> listWC = (ListWC<?>) field.get(joinPoint.getThis());
                    Type listTypeFullName = field.getGenericType();
                    Pattern pattern = Pattern.compile("<(.*?)>");
                    Matcher matcher = pattern.matcher(listTypeFullName.getTypeName());
                    matcher.matches();
                    if (matcher.find()) {
                        listWC.setGenericTypeName(matcher.group().replace("<","").replace(">",""));
                    } else {
                        throw new PatternSyntaxException("Generic ListWC Type is not found", pattern.pattern(), 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
