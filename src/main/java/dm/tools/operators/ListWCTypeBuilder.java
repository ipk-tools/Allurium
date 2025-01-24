package dm.tools.operators;

import dm.tools.exceptions.ListComponentTypeException;
import dm.tools.lists.ListWC;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListWCTypeBuilder {

    public static void buildType(JoinPoint joinPoint, Iterable<Field> fields) {
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().equals(ListWC.class)) {
                    ListWC<?> listWC = (ListWC<?>) field.get(joinPoint.getThis());
                    Type listTypeFullName = field.getGenericType();
                    if (listTypeFullName.getTypeName().equals(""))
                        throw new ListComponentTypeException(listWC, "Type is not selected for ListWC with name='%s'");
                    Pattern pattern = Pattern.compile("<(.*?)>");
                    Matcher matcher = pattern.matcher(listTypeFullName.getTypeName());
                    matcher.matches();
                    if (matcher.find()) {
                        try {
                            listWC.setGenericTypeName(matcher.group().replace("<","").replace(">",""));
                        } catch (NullPointerException nEx) {
                            System.out.println(matcher.group() + " setting generic type name to Null, caused NullPointerException");
                            System.out.println(
                                    "Component's list (ListWC) probably has '@ListLocator' but hasn't been instances " +
                                            "like '= new ListWC(By.id(someId)') or you haven't properly overridden getId() " +
                                            "method of a component that is in ListWC");
                        }
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
