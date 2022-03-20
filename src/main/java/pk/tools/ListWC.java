package pk.tools;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pk.tools.interfaces.ListComponent;
import pk.tools.interfaces.WebElementMeta;
import pk.tools.primitives.Link;
import pk.tools.primitives.UIElement;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Universal (pk.tools.examples.widgets') tool to work with lists of web components like table rows, cards, lists of complex
 * elements/pk.tools.examples.widgets etc.
 * Build on basic selenide class ElementCollection to make it strongly typed.
 * To present any web entity as item list need to implement interface ListComponent and describe method "get()"
 * which takes "id" as argument and using it to find element in collection.
 * Any item's field can be used as "id" what makes filtering very easy.
 *
 * Example of use:
 *      Scenario:
 *      @Given We have list of product cards on dashboard
 *      @Then need to find card with name "Example Product"
 *      @Then check price is 1000
 *      @And click to button "Buy"
 *
 *   On pure selenide we can do it like this in test steps:
 *
 *      <code>
 *      ElementsCollection productCards = $$(".list-item-class");
 *      product = productCards.filter(attribute(text("ExampleProduct"))).first();
 *      Assertions.assertEquals(Integer.parseInt(product.$(withText("1000")).text()), 1000);  // looks creepy
 *      product.$(".buy-button").click();
 *      </code>
 *
 *      And this code must be repeated in any case whe need to find card.
 *      And what if some elements on card will have similar text and numbers in few elements ??
 *      There will be big troubles.
 *    ------------------------------------------------
 *
 *    Using ListWC it will be made in next way:
 *
 *     <code>
 *      class ProductCard implements ListComponent {
 *          SelenideElement root;
 *          SelenideElement nameElement;
 *          SelenideElement priceElement;
 *          SelenideElement buyButton;
 *
 *          public ProductCard(SelenideElement root) {
 *              this.root = root;
 *              name = root.$(".name");
 *              price = root.$(".price");
 *              buyButton = root.$(".button");
 *          }
 *
 *          public String get() {
 *              return nameElement.text();
 *          }
 *
 *          public int getPrice() {
 *              return Integer.parseInt(priceElement.text());
 *          }
 *      }
 *     </code>
 *
 *     and writing steps like:
 *
 *     <code>
 *         ListWC<ProductCard> productCards = new List<>(".list-item-class", ProductCard.class);
 *         ProductCard productCard = productCard.get("ExampleProduct");
 *         Assertions.assertEquals(productCard.getPrice(), 1000);
 *         productCard.getBuyButton().click();
 *     </code>
 *
 *     No logic in steps anymore :)
 *
 *     Also since each item can be a self-sufficient widget and have its own class it also can have personal behaviour.
 *     So there is no restriction of how complex is each component, it always can be listed.
 *
 *     ------------------------------------------------
 *
 * @param <T> any entity with interface ListComponent
 */
@Slf4j
@NoArgsConstructor
public class ListWC<T extends ListComponent> implements WebElementMeta {

    private ElementsCollection sourceElements;
    private List<T> components = new ArrayList<>();
    private Class<T> componentsClass;
    @Setter @Getter
    private String genericTypeName = "void";

    @Getter
    @Setter
    private String name = "";

    @Getter
    @Setter
    private String description = "";

    @Setter
    private String assignNameMethod = "text";

    @Getter
    @Setter
    protected AbstractWidget parent = null;

    public ListWC(ElementsCollection sourceElements, Class<T> componentsClass) {
        this.componentsClass = componentsClass;
        this.sourceElements = sourceElements;
    }

    public ListWC(By listItemLocator, Class<T> componentsClass) {
        this.componentsClass = componentsClass;
        this.sourceElements = $$(listItemLocator);
    }

    public ListWC(String selenideSelector, Class<T> componentsClass) {
        this.componentsClass = componentsClass;
        this.sourceElements = $$(selenideSelector);
    }

    public ListWC(By listItemLocator) {
        this.sourceElements = $$(listItemLocator);
    }

    public List<T> getComponents() {
        refresh();
        return components;
    }

    public ListWC<T> refresh() {
        components.clear();
        this.sourceElements.forEach(this::add);
        return this;
    }

    private ListWC<T> add(T component) {
        components.add(component);
        return this;
    }

    @SuppressWarnings("unchecked")
    private ListWC<T> add(SelenideElement sourceElement) {
        try {
            Class<?> clazz = Class.forName(genericTypeName);
            Constructor<T> ctr = (Constructor<T>) clazz.getConstructor(SelenideElement.class);
            Object obj = ctr.newInstance(sourceElement);
            T listComponent = (T) obj;
            listComponent.setAssignNameMethod(assignNameMethod);
            components.add(listComponent);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public T get(String id) {
        Allure.step("Getting element from list by [" + id + "]");
//        Allure.step("Получаем элемент из списка по идентификатору [" + id + "]");
        refresh();
        should(sizeGreaterThan(0));
        Optional<T> target = components.stream().filter(item -> item.getId().contains(id)).findFirst();
        if (target.isPresent()) return target.get();
        Assert.fail("Не найден элемент из списка " + name + " по id=" + id);
        return null;
    }

    public Optional<T> getOptional(String id) {
        refresh();
        should(sizeGreaterThan(0));
        return components.stream().filter(item -> item.getId().contains(id)).findFirst();
    }

    public T getToLowerCase(String id) {
//        Allure.step("Получаем элемент из списка по идентификатору [" + id + "]");
        Allure.step("Getting element from list by [" + id + "]");
        refresh();
        should(sizeGreaterThan(0));
        return components.stream()
                .filter(item -> item.getId().toLowerCase(Locale.ROOT)
                        .contains(id.toLowerCase(Locale.ROOT))).findFirst().get();
    }

    public T get(Integer index) {
//        Allure.step("Получаем элемент по id [" + index + "] из списка " + name);
        Allure.step("Getting element from list by [" + index + "]");
        refresh();
        should(sizeGreaterThan(index));
        return components.get(index);
    }

    public List<T> getAll() {
        refresh();
        return components;
    }

    public ListWC<T> filter(Condition condition) {
        refresh();
        return new ListWC<>(sourceElements.filter(condition).snapshot(), componentsClass).refresh();
    }

    public T first() {
        Allure.step("Получаем первый элемент из списка " + name);
        refresh();
        should(sizeGreaterThan(0));
        return components.get(0);
    }

    public T last() {
        Allure.step("Получаем последний элемент из списка " + name);
        refresh();
        should(sizeGreaterThan(1));
        return components.get(components.size() - 1);
    }

    public ListWC<T> should(CollectionCondition... conditions) {
        components.clear();
        Arrays.stream(conditions).forEach(condition -> {
            sourceElements.should(condition);
        });
        sourceElements.forEach(this::add);
        return this;
    }

    public boolean isDisplayed() {
        var elems = sourceElements.stream()
                .filter(SelenideElement::isDisplayed)
                .collect(Collectors.toList());
        return elems.size() != 0;
    }

    public int size() {
        refresh();
        return components.size();
    }

    public void assertSizeIs(int size) {
        refresh();
        Allure.step("Проверяем, что колличество виджетов в списке " + name + "  равно " + size);
        sourceElements.should(CollectionCondition.sizeGreaterThan(size));
    }

    public void dump() {
        log.info("---list dump---");
        log.info("list size: " + size());
        log.info("<components content>");
        for (int i = 0; i < components.size(); i++) {
            log.info("<" + i + ">");
            log.info(components.get(i).toString());
            log.info("HTML: " + components.get(i).getRoot().innerHtml());
            log.info("</" + i + ">\n");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListWC<?> listWC = (ListWC<?>) o;
        return components.equals(listWC.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

}
