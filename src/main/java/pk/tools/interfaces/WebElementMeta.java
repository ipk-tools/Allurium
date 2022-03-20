package pk.tools.interfaces;

import pk.tools.AbstractWidget;

public interface WebElementMeta {

    void setName(String name);

    String getName();

    void setDescription(String description);

    String getDescription();

    void setParent(AbstractWidget parent);

    AbstractWidget getParent();

    void setAssignNameMethod(String method);
}
