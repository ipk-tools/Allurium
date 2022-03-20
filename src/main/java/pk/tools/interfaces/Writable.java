package pk.tools.interfaces;

/**
 * Interface for web components (pk.tools.examples.widgets) which basic use is in writing text into different fields or elements.
 * The main elements are native input field, textarea and generics which must have write() and clear() methods.
 */
public interface Writable {

    void write(String text);

    void clear();
}
