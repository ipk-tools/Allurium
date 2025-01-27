package allurium.exceptions;

public class ListElementIsNotValidException extends NoSuchMethodException {

    public ListElementIsNotValidException(Class<?> clazz) {
        super("there is no constructor(SelenideElement sourceElement) found for class - " + clazz.getName());
    }

    public ListElementIsNotValidException(String className) {
        super("there is no constructor(SelenideElement sourceElement) found for class - " + className);
    }
}
