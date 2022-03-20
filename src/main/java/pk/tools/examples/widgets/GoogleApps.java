package pk.tools.examples.widgets;

import org.openqa.selenium.By;
import pk.tools.AbstractWidget;
import pk.tools.annotations.Widget;
import pk.tools.primitives.Icon;

@Widget
public class GoogleApps extends AbstractWidget {

    protected Icon appsIcon = Icon.from(By.xpath("//*[contains(text(),'Sign')]"));
}
