package pk.tools.interfaces;

import org.openqa.selenium.InvalidArgumentException;

/**
 * Interface for pk.tools.examples.widgets which serves to selecting options like radio buttons ore any
 * other elements instead.
 */
public interface SelectWC extends WebComponent {

    void select(String value) throws InvalidArgumentException;
}
