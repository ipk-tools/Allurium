package widgets;

import allurium.AbstractWidget;
import allurium.annotations.ListLocatorChain;
import allurium.annotations.LocatorChain;
import allurium.annotations.Name;
import allurium.annotations.Widget;
import allurium.lists.ListWC;
import allurium.primitives.Icon;
import allurium.primitives.Link;
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
