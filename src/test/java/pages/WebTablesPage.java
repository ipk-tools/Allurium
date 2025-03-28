package pages;

import allurium.annotations.*;
import com.codeborne.selenide.SelenideElement;
import allurium.AbstractWidget;
import allurium.lists.ListWC;
import allurium.primitives.UIElement;
import lombok.Getter;
import lombok.experimental.Accessors;

@PageObject
@Getter
@Accessors(fluent = true)
public class WebTablesPage {

    @Name("Employee")
    @ListLocator(css = ".rt-tr-group")
    protected ListWC<EmployeeListItem> listEmployee = new ListWC<>();


    @Widget
    @Getter
    @Accessors(fluent = true)
    public static class EmployeeListItem extends AbstractWidget {

        @Name("First Name")
        @LocatorChain(css = ".rt-td:nth-child(1)")
        protected UIElement firstName;

        @Name("Last Name")
        @LocatorChain(css = ".rt-td:nth-child(2)")
        protected UIElement lastName;

        @Name("Email")
        @LocatorChain(css = ".rt-td:nth-child(4)")
        protected UIElement email;

        public EmployeeListItem(SelenideElement rootElement) {
            super(rootElement);
        }

        @Override
        public String getId() {
            return lastName.text();
        }
    }

}
