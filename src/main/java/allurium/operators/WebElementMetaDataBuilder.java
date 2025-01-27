package allurium.operators;

import allurium.annotations.Name;
import allurium.annotations.Type;
import allurium.interfaces.WebElementMeta;
import allurium.primitives.UIElement;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public class WebElementMetaDataBuilder {

    public static void buildMeta(Object obj) {
        Iterable<Field> fields = getFieldsUpTo(obj.getClass(), UIElement.class);

        for (Field field : fields) {

            if (field.isAnnotationPresent(Name.class)) {
                Type type = null;
                if (field.isAnnotationPresent(Type.class)) {
                    type = field.getAnnotation(Type.class);
                }
                Name metaData = field.getAnnotation(Name.class);
                boolean hasMetaData = false;

                try {
                    field.setAccessible(true);
                    if (field.get(obj) instanceof WebElementMeta) {
                        hasMetaData = true;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (hasMetaData) {
                    try {
                        field.setAccessible(true);
                        WebElementMeta webElement = (WebElementMeta) field.get(obj);
                        webElement.setUiElementName(metaData.value());
                        //webElement.setAssignNameMethod(metaData.nameFrom());

                        if (!metaData.value().equals("")) {
                            webElement.setDescription(metaData.description());
                        }

                        if (field.getType().equals(UIElement.class)) {
                            if (type != null) {
                                UIElement instance = (UIElement) field.get(obj);
                                instance.setUiElementType(type.value().getType());
                            }
                        }

                    } catch (IllegalAccessException | NullPointerException exception) {
                        printStackTrace(exception);
                    }
                }
            }
        }
    }

    private static void printStackTrace(Exception ex) {
        if (ex.getClass() == NullPointerException.class) {
            log.debug("Поле не инециалзированно", ex);
        } else if (ex.getClass() == IllegalAccessException.class) {
            log.error("Ошибка доступа к полю", ex);
        }
    }

    public static Iterable<Field> getFieldsUpTo(@Nonnull Class<?> startClass, @Nullable Class<?> exclusiveParent) {
        List<Field> currentClassFields = Lists.newArrayList(startClass.getDeclaredFields());
        Class<?> parentClass = startClass.getSuperclass();
        if (parentClass != null && (exclusiveParent == null || !(parentClass.equals(exclusiveParent)))) {
            List<Field> parentClassFields = (List<Field>) getFieldsUpTo(parentClass, exclusiveParent);
            currentClassFields.addAll(parentClassFields);
        }
        return currentClassFields;
    }
}
