package pk.tools.interfaces;

public interface TabsWithContent extends Switch {

    <T extends TabContentBlock> T openContentOf(Class<T> tabContentBlock);
}
