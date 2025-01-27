package allurium.exceptions;

import allurium.inputs.Select;

import java.util.NoSuchElementException;

public class NoSuchOptionException extends NoSuchElementException {

    public NoSuchOptionException(Select select, String option) {
        super("Option '" + option + "' wasn't found in " + "select '" + select.getUiElementName() + "'");
    }
}
