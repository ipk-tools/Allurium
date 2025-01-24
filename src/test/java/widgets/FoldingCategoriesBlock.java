package widgets;

import dm.tools.AbstractWidget;
import dm.tools.annotations.*;
import dm.tools.lists.ListWC;
import dm.tools.primitives.Icon;
import dm.tools.primitives.Link;
import lombok.Getter;
import lombok.experimental.Accessors;

@Widget
@Getter
@Accessors(fluent = true)
public class FoldingCategoriesBlock extends AbstractWidget {

    @Name("Arrow")
    @LocatorChain(css = ".header-right svg")
    protected Icon iconArrow;

    @Name("Subcategories")
    @ListLocatorChain(css = ".menu-list .btn")
    protected ListWC<Link> subcategories = new ListWC<>();
}
