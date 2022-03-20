package pk.tools.interfaces;

/**
 * Web components or any composed pk.tools.examples.widgets with this interface implemented
 * can be collected to Collection in <ListWC>
 *
 * example of using:
 *
 *    <html>
 *        <form>
 *            <span>form 1</span>
 *            <ul>
 *              <li>Good</li>
 *             <li>Normal</li>
 *              <li>Bad</li>
 *           </ul>
 *           <input type="text">
 *           <textarea>lorem ipsum</textarean
 *           <div>Buy</div>
 *        </form>
 *
 *        <form>
 *              <span>form 2</span>
 *               <ul>
 *                 <li>Good</li>
 *                 <li>Normal</li>
 *                 <li>Bad</li>
 *              </ul>
 *              <input type="text">
 *              <textarea>lorem ipsum</textarean
 *              <div>Buy</div>
 *  *        </form>
 *
 *    </html>
 *
 * ---------------------------------
 *
 *    <code>
 *        @Getter
 *        class Form implements ListComponent {
 *            SelenideElement root;
 *            SelenideElement formName;
 *            DropdownWC marks;
 *            InputField emailInput;
 *            TextArea description;
 *
 *            public Form(By rootLocator) {
 *                root = $(rootLocator);
 *                formName = root.$("span");
 *                marks = new DropdownWC(root.$("ul"));
 *                email = new InputField(root.$("input"));
 *                description = new TextArea(root.$("textarea"));
 *            }
 *
 *            @Override
 *            public get(String) {
 *                return formName.text();
 *            }
 *        }
 *
 *        @Step simple example
 *        public void writeReview() {
 *            ListWC<Form> reviewForms = new ListWC("form", Form.class);
 *            Form form = reviewForms.get("form 2");
 *            form.getMarks().select("Normal")
 *            form.getEmailInput().write("example@email.com")
 *            form.getDescription.write("example description");
 *        }
 *    </code>
 */
public interface ListComponent extends WebComponent {

    String getId();
}
