package allurium.lists;

import allurium.AbstractWidget;
import allurium.AlluriumConfig;
import allurium.StepTextProvider;
import allurium.aspects.WidgetAspects;
import allurium.exceptions.ListComponentTypeException;
import allurium.exceptions.LocatorByException;
import allurium.interfaces.ListComponent;
import allurium.interfaces.WebElementMeta;
import allurium.operators.ElementsInvoker;
import allurium.operators.LocatorBuilder;
import allurium.operators.WebElementMetaDataBuilder;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * A universal utility for working with lists of web components such as table rows, cards, or complex widgets.
 * <p>
 * The `ListWC` class wraps `ElementCollection` and enhances it by providing strong typing for the contained
 * elements. It supports common list operations such as filtering, element retrieval, assertions, and size checks.
 * This class is highly configurable and integrates seamlessly with Allure for step reporting.
 * </p>
 *
 * @param <T> the type of the list components, which must implement {@link ListComponent}.
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Supports dynamic type-based component initialization.</li>
 *     <li>Provides robust mechanisms for interacting with and validating list content.</li>
 *     <li>Integrates with Allure for enhanced test reporting.</li>
 *     <li>Supports advanced filtering and assertions using Selenide's {@link Condition} and {@link CollectionCondition}.</li>
 *     <li>Refreshes and rebuilds components dynamically.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interaction with web component lists in tests.</li>
 *     <li>Simplifies complex list operations and error handling.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #ListWC(ElementsCollection, Class)} - Initializes the list with elements and component type.</li>
 *     <li>{@link #ListWC(By, Class)} - Initializes the list with a locator and component type.</li>
 *     <li>{@link #ListWC(String, Class)} - Initializes the list with a Selenide selector and component type.</li>
 *     <li>{@link #ListWC(By)} - Initializes the list with a locator without specifying a component type.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * // Define a custom list component
 * public class TableRow implements ListComponent {
 *
 *     public TableRow(SelenideLocator sl) {
 *         super(sl);
 *     }
 *
 *     public String getId() {
 *         return getRoot().$x("//td[1]").text();
 *     }
 * }
 *
 * // Initialize and use the ListWC
 * ListWC<TableRow> tableRows = new ListWC<>(".table-row");
 * TableRow row = tableRows.get("Row 1");
 * row.assertVisible();
 * }</pre>
 *
 * <h3>Thread-Safety:</h3>
 * The class is not thread-safe. Each thread should create its own instance if needed.
 *
 * @see ListComponent
 * @see CollectionCondition
 * @see Condition
 * @author Iaroslav Pilipenko
 */
@Slf4j
@NoArgsConstructor
public class ListWC<T extends ListComponent> implements WebElementMeta {

    @Setter
    @Getter
    private ElementsCollection sourceElements;
    private List<T> components = new ArrayList<>();
    private Class<T> componentsClass;
    private final StringBuffer metaKeys = new StringBuffer().append("role:list");
    private Constructor<T> cachedConstructor = null;
    private String lastFingerprint = "";

    private By listItemLocator;

    private static String nameWrapStart = AlluriumConfig.highlighterStart();
    private static String nameWrapEnd = AlluriumConfig.highlighterEnd();

    @Setter
    @Getter
    private String genericTypeName = "void";

    @Getter
    @Setter
    private String uiElementName = "";

    @Getter
    @Setter
    private String description = "";

    @Setter
    private String assignNameMethod = "text";

    @Getter
    @Setter
    protected Optional<AbstractWidget> parent = Optional.empty();
    private boolean isBuilt = false;
    private boolean staleRecoveryTried = false;

    /**
     * Constructs a `ListWC` with the provided source elements and component class type.
     *
     * @param sourceElements  the {@link ElementsCollection} representing the source elements in the list
     * @param componentsClass the class type of the list components
     */
    public ListWC(ElementsCollection sourceElements, Class<T> componentsClass) {
        this.componentsClass = componentsClass;
        this.sourceElements = sourceElements;
    }

    /**
     * Constructs a `ListWC` using a Selenium {@link By} locator to identify list items and the component class type.
     *
     * @param listItemLocator the {@link By} locator for the list items
     * @param componentsClass the class type of the list components
     */
    public ListWC(By listItemLocator, Class<T> componentsClass) {
        this.componentsClass = componentsClass;
        this.listItemLocator = listItemLocator;
        this.sourceElements = $$(listItemLocator);
    }

    /**
     * Constructs a `ListWC` using a Selenide selector string to identify list items and the component class type.
     *
     * @param selenideSelector the Selenide selector string for the list items
     * @param componentsClass  the class type of the list components
     */
    public ListWC(String selenideSelector, Class<T> componentsClass) {
        this.componentsClass = componentsClass;
        this.sourceElements = $$(selenideSelector);
    }

    /**
     * Constructs a `ListWC` using a Selenium {@link By} locator without specifying the component class type.
     *
     * @param listItemLocator the {@link By} locator for the list items
     */
    public ListWC(By listItemLocator) {
        this.listItemLocator = listItemLocator;
        this.sourceElements = $$(listItemLocator);
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

    @Override
    public StringBuffer getMetaKeys() {
        return metaKeys;
    }

    /**
     * Helper to generate a fingerprint for the current collection.
     */
    private String generateFingerprint(ElementsCollection collection) {
        String concatenated = executeJavaScript(
                "return Array.from(arguments[0]).map(e => (e.id || '') + e.innerText).join('');",
                collection
        );
        return Integer.toString(concatenated.hashCode());
    }

    public void logDump() {
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

    /**
     * Wraps the list's name with the highlighter prefixes and suffixes.
     *
     * @return the wrapped name of the list
     */
    public String wrappedName() {
        return nameWrapStart + uiElementName + nameWrapEnd;
    }

    /**
     * Retrieves the list of components after refreshing the source elements.
     *
     * @return the list of components of type {@code T}
     */
    public List<T> getComponents() {
        refresh();
        return components;
    }

    private void lazyBuild() {
        if (!isBuilt) {
            refresh();
            isBuilt = true;
        }
    }

    @SuppressWarnings("unchecked")
    private void runAssertionStep(String stepKey, Runnable assertionCode, Pair<String, String>... placeholders) {
        String stepUuid = UUID.randomUUID().toString();
        String stepName = StepTextProvider.getStepText(stepKey, getParent(), placeholders);
        StepResult stepResult = new StepResult().setName(stepName);
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            assertionCode.run();
        } catch (AssertionError e) {
            errorStatus = true;
            throw e; // rethrow so that test fails
        } finally {
            stepResult.setStatus(errorStatus ? Status.FAILED : Status.PASSED);
            Allure.getLifecycle().stopStep(stepUuid);
        }
    }


    /**
     * Wraps an operation so that if we hit a StaleElementReferenceException,
     * we do 'refresh()' once, then retry the same operation.
     */
    private <R> R runWithStaleRecovery(Supplier<R> operation) {
        try {
            return operation.get();
        } catch (StaleElementReferenceException e) {
            // If haven't retried yet, do one forced refresh and try again
            if (!staleRecoveryTried) {
                staleRecoveryTried = true;
                refresh();
                return operation.get();
            }
            // If it's still stale after a second try, rethrow or fail.
            throw e;
        }
    }

    /**
     * Refreshes the list by clearing the existing components and rebuilding them
     * based on the current state of the source elements.
     * <p>
     * If the generic type name is not properly set, throws a {@link ListComponentTypeException}.
     * Also invokes utility methods to enrich the components with metadata.
     * </p>
     *
     * @return the current instance of {@link ListWC} for method chaining
     */
    public ListWC<T> refresh() {
        if (genericTypeName.equals("void"))
            //todo: move exception to signature
            try {
                throw new ListComponentTypeException(this);
            } catch (ListComponentTypeException e) {
                e.printStackTrace();
            }
        String currentFingerprint = generateFingerprint(sourceElements);
        if (currentFingerprint.equals(lastFingerprint)) {
            log.debug("No changes detected in source elements based on fingerprint; skipping refresh.");
            return this;
        }
        lastFingerprint = currentFingerprint;
        components.clear();
        try {
            sourceElements.asDynamicIterable().forEach(this::add);
        } catch (NullPointerException nEx) {
            log.info("There are no elements to refresh in the list: " + uiElementName);
        }
        components.forEach(component -> {
            try {
                ElementsInvoker.invokeElementsWithLocatorsWithinList(component);
                WidgetAspects.buildParents(component);
                WebElementMetaDataBuilder.buildMeta(component);
                LocatorBuilder.buildLocatorForElementsInLists(component);
            } catch (IllegalAccessException | LocatorByException e) {
                log.error("Error updating component metadata", e);
            }
        });
        return this;
    }

    // Need to add case when constructor is not set at once. This is need for '@LocatorChain' to work. (not actual)
    /**
     * Dynamically adds a component to the list based on its class type and associates it with the provided source element.
     * <p>
     * This method tries to create an instance of the component class {@code T} using reflection.
     * It first attempts to use a constructor that accepts a {@link SelenideElement}.
     * If such a constructor is unavailable, it falls back to a default (no-argument) constructor.
     * If both attempts fail, appropriate warnings are logged, and the component is skipped.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Handles both parameterized and default constructors dynamically.</li>
     *     <li>Associates each component with its parent list for better metadata tracking.</li>
     *     <li>Logs warnings for missing constructors instead of throwing exceptions to avoid breaking the list refresh process.</li>
     * </ul>
     *
     * @param sourceElement the {@link SelenideElement} representing a single list item
     * @return the current instance of {@link ListWC} for method chaining
     *
     * @throws ClassNotFoundException if the class corresponding to {@code genericTypeName} cannot be found
     * @throws InvocationTargetException if the constructor being invoked throws an exception
     * @throws InstantiationException if the component class cannot be instantiated
     * @throws IllegalAccessException if the constructor is not accessible
     * @throws NoSuchMethodException if no matching constructor is found
     */
    @SuppressWarnings("unchecked")
    private ListWC<T> add(SelenideElement sourceElement) {
        try {
            if (cachedConstructor == null) {
                Class<?> clazz = Class.forName(genericTypeName);
                cachedConstructor = (Constructor<T>) clazz.getConstructor(SelenideElement.class);
            }
            T listComponent = cachedConstructor.newInstance(sourceElement);
            listComponent.setAssignNameMethod(assignNameMethod);
            if (sourceElement.exists()) {
                listComponent.setUiElementName(listComponent.getId());
            } else {
                log.warn("Element of the source list does not exist; skipping");
            }
            FakeParent fakeParent = new FakeParent();
            fakeParent.setUiElementName(this.uiElementName);
            fakeParent.setUiElementType("list");
            listComponent.setParent(Optional.of(fakeParent));
            components.add(listComponent);
        } catch (NoSuchMethodException
                 | ClassNotFoundException
                 | InvocationTargetException
                 | InstantiationException
                 | IllegalAccessException e) {
            log.warn("Failed to create list component for " + genericTypeName, e);
        }
        return this;
    }

    @Deprecated
    public T getStrictly(String id) {
        return getPrecise(id);
    }

    public T getExact(String id) {
        return getPrecise(id);
    }

    /**
     * Retrieves an element from the list by its exact ID, retrying if not immediately found.
     * <p>
     * The method uses a strict comparison to locate an element with a matching {@code id}.
     * It refreshes the list and retries for a configurable number of attempts.
     * If the element is still not found, it logs a failed step in Allure and throws an assertion error.
     * </p>
     * <p>'getPrecise(String id)' is recommended to use when 'get(String id)' can't fulfill specific requirements</p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Performs strict comparison using {@code equals} to find the element.</li>
     *     <li>Retries a configurable number of times before failing.</li>
     *     <li>Logs detailed information about the failure in Allure for debugging.</li>
     * </ul>
     *
     * @param id the exact ID of the element to retrieve
     * @return the matching element of type {@code T} if found
     * @throws AssertionError if the element is not found after all retry attempts
     */
    @SuppressWarnings("unchecked")
    public T getPrecise(String id) {
        lazyBuild();
        int counter = AlluriumConfig.retryAmount();
        Optional<T> target = components.stream().filter(item -> item.getId().equals(id)).findFirst();
        while (counter > 0 && !target.isPresent()) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
            target = components.stream().filter(item -> item.getId().equals(id)).findFirst();
            counter--;
        }
        if (target.isPresent()) return target.get();

        // if element wasn't found,then adding step "get" to allure report with failed status and message
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult()
                .setName(
                        StepTextProvider.getStepText("list_strict_search_by_element", getParent(),
                                Pair.of("{name}", uiElementName), Pair.of("{id}", id))
                );
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        stepResult.setStatus(Status.FAILED);
        Allure.getLifecycle().stopStep(stepUuid);
        Assertions.fail("Element wasn't found in the list " + uiElementName + " by id=" + id);
        return null;
    }

    /**
     * Retrieves an element from the list by a partial match of its ID, retrying if not immediately found.
     * <p>
     * The method performs a non-strict comparison, looking for an element whose {@code id} contains
     * the specified substring. It refreshes the list and retries for a configurable number of attempts.
     * If the element is still not found, it logs a failed step in Allure and throws an assertion error.
     * </p>
     * <p>'get(String id)' is recommended to use in most cases.</p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Performs a partial comparison using {@code contains} to find the element.</li>
     *     <li>Retries a configurable number of times before failing.</li>
     *     <li>Logs detailed information about the failure in Allure for debugging.</li>
     * </ul>
     *
     * @param id the partial ID of the element to retrieve
     * @return the matching element of type {@code T} if found
     * @throws AssertionError if the element is not found after all retry attempts
     */
    @SuppressWarnings("unchecked")
    public T get(String id) {
        lazyBuild();
        int counter = AlluriumConfig.retryAmount();
        Optional<T> target = components.stream().filter(item -> item.getId().contains(id)).findFirst();
        while (counter > 0 && !target.isPresent()) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
            target = components.stream().filter(item -> item.getId().contains(id)).findFirst();
            counter--;
        }
        if (target.isPresent()) return target.get();

        // if element wasn't found,then adding step "get" to allure report with failed status and message
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult()
                .setName(
                        StepTextProvider.getStepText("list_search_by_element_id", getParent(),
                                Pair.of("{name}", uiElementName), Pair.of("{id}", id))
                );
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        stepResult.setStatus(Status.FAILED);
        Allure.getLifecycle().stopStep(stepUuid);
        Assertions.fail("Element wasn't found in the list " + uiElementName + " by id=" + id);
        return null;
    }

    /**
     * Retrieves a visible element from the list by a partial match of its ID, retrying if not immediately found.
     * <p>
     * The method filters for visible elements whose {@code id} contains the specified substring.
     * It refreshes the list and retries for a configurable number of attempts. If the element
     * is still not found, it logs a failed step in Allure and throws an assertion error.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Filters elements to find those that are both visible and match the given partial {@code id}.</li>
     *     <li>Retries fetching the element a configurable number of times.</li>
     *     <li>Logs detailed information about the failure in Allure for debugging.</li>
     * </ul>
     *
     * @param id the partial ID of the visible element to retrieve
     * @return the matching visible element of type {@code T} if found
     * @throws AssertionError if the element is not found after all retry attempts
     */
    public T getVisible(String id) {
        refresh();
        int counter = AlluriumConfig.retryAmount();
        Optional<T> target = components.stream().filter(item -> item.getId().contains(id)).findFirst();
        while (counter > 0 && !target.isPresent()) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
            target = components.stream().filter(item -> item.getRoot().isDisplayed())
                                        .filter(item -> item.getId().contains(id))
                                        .findFirst();
            counter--;
        }
        if (target.isPresent()) return target.get();

        // if element wasn't found,then adding step "get" to allure report with failed status and message
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult()
                .setName(
                        StepTextProvider.getStepText("list_search_by_element_id", getParent(),
                                Pair.of("{name}", uiElementName), Pair.of("{id}", id))
                );
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        stepResult.setStatus(Status.FAILED);
        Allure.getLifecycle().stopStep(stepUuid);
        Assertions.fail("Element wasn't found in the list " + uiElementName + " by id=" + id);
        return null;
    }

    /**
     * Retrieves a visible element from the list by its exact ID, retrying if not immediately found.
     * <p>
     * The method ensures the element is visible and matches the exact {@code id} provided.
     * It refreshes the list and retries for a configurable number of attempts. If the element
     * is still not found, it logs a failed step in Allure and throws an assertion error.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Filters elements by exact match of their {@code id}.</li>
     *     <li>Ensures that the element is visible (`isDisplayed()`).</li>
     *     <li>Retries fetching the element a configurable number of times.</li>
     *     <li>Logs failure details in Allure and throws an error for debugging purposes.</li>
     * </ul>
     *
     * @param id the exact ID of the visible element to retrieve
     * @return the matching visible element of type {@code T} if found
     * @throws AssertionError if the element is not found after all retry attempts
     */
    public T getPreciseVisible(String id) {
        lazyBuild();
        int counter = AlluriumConfig.retryAmount();
        Optional<T> target = components.stream().filter(item -> item.getId().equals(id)).findFirst();
        while (counter > 0 && !target.isPresent()) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
            target = components.stream().filter(item -> item.getRoot().isDisplayed())
                    .filter(item -> item.getId().equals(id))
                    .findFirst();
            counter--;
        }
        if (target.isPresent()) return target.get();

        // if element wasn't found,then adding step "get" to allure report with failed status and message
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult()
                .setName(
                        StepTextProvider.getStepText("list_search_by_element_id", getParent(),
                                Pair.of("{name}", uiElementName), Pair.of("{id}", id))
                );
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        stepResult.setStatus(Status.FAILED);
        Allure.getLifecycle().stopStep(stepUuid);
        Assertions.fail("Element wasn't found in the list " + uiElementName + " by id=" + id);
        return null;
    }

    /**
     * Attempts to retrieve an element from the list that matches the given ID, returning an {@link Optional}.
     * <p>
     * The method searches for the first element whose {@code id} contains the specified value. If no such element
     * is found, it retries for a configurable number of attempts before returning an empty {@link Optional}.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Searches for a partial match of the {@code id}.</li>
     *     <li>Returns an {@link Optional} to handle cases where no element is found.</li>
     *     <li>Retries the search a configurable number of times.</li>
     * </ul>
     *
     * @param id the partial ID to search for
     * @return an {@link Optional} containing the first matching element, or an empty {@link Optional} if not found
     */
    public Optional<T> getNullable(String id) {
        lazyBuild();
        int counter = AlluriumConfig.retryAmount();
        Optional<T> target = components.stream().filter(item -> item.getId().contains(id)).findFirst();
        while (counter > 0 && !target.isPresent()) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
            target = components.stream().filter(item -> item.getId().contains(id)).findFirst();
            counter--;
        }
        return target;
    }

    /**
     * Retrieves all elements from the list whose {@code id} contains the specified value.
     * <p>
     * The method searches for all matching elements in the list and retries if no matches are initially found.
     * It continues refreshing and searching until the configured retry limit is reached.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Searches for a partial match of the {@code id}.</li>
     *     <li>Returns a list of matching elements.</li>
     *     <li>Retries the search a configurable number of times if no matches are found initially.</li>
     * </ul>
     *
     * @param id the partial ID to search for
     * @return a {@link List} of matching elements, or an empty list if no matches are found
     */
    public List<T> getMultiple(String id) {
        lazyBuild();
        int counter = AlluriumConfig.retryAmount();
        List<T> target = components.stream().filter(item -> item.getId().contains(id)).collect(Collectors.toList());
        while (counter > 0 && target.size() <= 0) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
            target = components.stream().filter(item -> item.getId().contains(id)).collect(Collectors.toList());
            counter--;
        }
        return target;
    }

    /**
     * Retrieves all elements from the list whose {@code id} exactly matches the specified value.
     * <p>
     * The method searches for all elements that have an exact match for the provided {@code id}.
     * If no matches are found initially, it retries the search until the retry limit is reached.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Searches for an exact match of the {@code id}.</li>
     *     <li>Returns a list of matching elements.</li>
     *     <li>Retries the search a configurable number of times if no matches are found initially.</li>
     * </ul>
     *
     * @param id the exact ID to search for
     * @return a {@link List} of elements with the exact matching ID, or an empty list if no matches are found
     */
    public List<T> getPreciseMultiple(String id) {
        lazyBuild();
        int counter = AlluriumConfig.retryAmount();
        List<T> target = components.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
        while (counter > 0 && target.size() <= 0) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
            target = components.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
            counter--;
        }
        return target;
    }

    /**
     * Retrieves the element at the specified index in the list.
     * <p>
     * The method ensures that the list has enough elements before attempting to access the specified index.
     * It refreshes the list and retrieves the element at the given index.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Ensures that the list contains at least the number of elements specified by the index.</li>
     *     <li>Refreshes the list to ensure the latest state before retrieving the element.</li>
     * </ul>
     *
     * @param index the zero-based index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is greater than or equal to the list size
     */
    public T get(Integer index) {
        sourceElements.should(CollectionCondition.sizeGreaterThanOrEqual(index));
        refresh();
        return components.get(index);
    }

    /**
     * Retrieves the first element in the list.
     * <p>
     * The method ensures that the list contains at least one element before attempting to access the first element.
     * It refreshes the list to ensure the latest state and then retrieves the first element.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Ensures that the list contains at least one element.</li>
     *     <li>Refreshes the list to ensure the latest state before retrieving the element.</li>
     * </ul>
     *
     * @return the first element in the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public T first() {
        sourceElements.should(CollectionCondition.sizeGreaterThanOrEqual(1));
        refresh();
        return components.get(0);
    }

    /**
     * Retrieves the last element in the list.
     * <p>
     * The method ensures that the list contains at least one element before attempting to access the last element.
     * It refreshes the list to ensure the latest state and then retrieves the last element.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Ensures that the list contains at least one element.</li>
     *     <li>Refreshes the list to ensure the latest state before retrieving the element.</li>
     * </ul>
     *
     * @return the last element in the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public T last() {
        sourceElements.should(CollectionCondition.sizeLessThanOrEqual(getComponents().size() + 1));
        refresh();
        return components.get(components.size() - 1);
    }

    public List<T> getAll() {
        refresh();
        return components;
    }

    /**
     * Asserts that the list satisfies the specified collection conditions.
     * <p>
     * The method allows for applying one or more conditions from Selenide's {@link CollectionCondition} class
     * to the underlying elements in the list. If the conditions are not met, an assertion failure is thrown,
     * and the result is logged to Allure with the status of the operation.
     * </p>
     *
     * <h3>Key Features:</h3>
     * <ul>
     *     <li>Supports multiple {@link CollectionCondition} checks in a single invocation.</li>
     *     <li>Automatically refreshes the list to ensure it reflects the current state of the elements.</li>
     *     <li>Integrates with Allure for detailed reporting, showing the status of the operation in the test report.</li>
     * </ul>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Clears the current list of components.</li>
     *     <li>Applies each condition to the list's source elements.</li>
     *     <li>Rebuilds the list components after the conditions are applied.</li>
     *     <li>Logs the operation status (PASSED or FAILED) to Allure.</li>
     * </ul>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * // Example: Assert that the list contains exactly 5 visible elements.
     * myList.should(CollectionCondition.size(5));
     *
     * // Example: Assert that the list is not empty and all elements are visible.
     * myList.should(CollectionCondition.sizeGreaterThan(0), CollectionCondition.allMatch("visible", SelenideElement::isDisplayed));
     * }</pre>
     *
     * @param conditions one or more {@link CollectionCondition} to apply to the list
     * @throws AssertionError if any of the conditions are not met
     */
    public void should(CollectionCondition... conditions) {
        components.clear();
        String stepUuid = String.valueOf(UUID.randomUUID());
        String conditionsAsString = Arrays.stream(conditions).map(Object::toString).collect(Collectors.joining(", "));
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("list_should_conditions", this.getParent(),
                        Pair.of("{name}", this.wrappedName()),
                        Pair.of("{conditions}", conditionsAsString)
                ));
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            Arrays.stream(conditions).forEach(condition -> sourceElements.should(condition));
            this.sourceElements.asDynamicIterable().forEach(this::add);
        } catch (AssertionError assertionError) {
            errorStatus = true;
            throw assertionError;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep(stepUuid);
        }
    }

    /**
     * Filters the current {@code ListWC} instance and returns a new instance containing only the elements
     * that satisfy the specified condition.
     * <p>
     * <strong>Behavior:</strong>
     * <ul>
     *   <li>Refreshes this list to ensure the underlying elements are up-to-date.</li>
     *   <li>Applies the given {@link Condition} to the {@code sourceElements}.</li>
     *   <li>Creates a new {@code ListWC} instance with the same {@code genericTypeName}, parent, and description as this instance.</li>
     *   <li>Sets the new list's {@code uiElementName} to include the original name plus the condition's textual representation.</li>
     *   <li>Invokes {@link #refresh()} on the new list to build its components.</li>
     * </ul>
     *
     * @param condition the {@link Condition} to apply
     * @return a new {@code ListWC} instance containing only the elements that match the specified condition
     */
    public ListWC<T> filter(Condition condition) {
        refresh();
        ListWC<T> filteredList = new ListWC<>();
        filteredList.genericTypeName = this.genericTypeName;
        filteredList.setUiElementName(String.format("%s, filtered by: %s", this.uiElementName, condition.toString()));
        filteredList.setParent(this.parent);
        filteredList.setDescription(this.description);
        filteredList.setSourceElements(sourceElements.filter(condition));
        filteredList.refresh();
        return filteredList;
    }

    /**
     * Filters the current {@code ListWC} instance and returns a new instance containing only the elements
     * that satisfy all of the specified conditions.
     * <p>
     * <strong>Behavior:</strong>
     * <ul>
     *   <li>Refreshes this list to ensure the underlying elements are up-to-date.</li>
     *   <li>Sequentially applies each provided {@link Condition} to the {@code sourceElements}.</li>
     *   <li>Creates a new {@code ListWC} instance with the same {@code genericTypeName}, parent, and description as this instance.</li>
     *   <li>Sets the new list's {@code uiElementName} to include the original name plus the textual representations of all conditions.</li>
     *   <li>Invokes {@link #refresh()} on the new list to build its components.</li>
     * </ul>
     *
     * @param conditions one or more {@link Condition} objects to apply; elements must pass all of them to remain in the filtered list
     * @return a new {@code ListWC} instance containing only the elements that match all specified conditions
     */
    public ListWC<T> filter(Condition... conditions) {
        refresh();
        ElementsCollection filtered = sourceElements;
        for (Condition cond : conditions) {
            filtered = filtered.filter(cond);
        }
        String conditionsAsString = Arrays.stream(conditions)
                .map(Condition::toString)
                .collect(Collectors.joining(" & "));
        ListWC<T> filteredList = new ListWC<>();
        filteredList.genericTypeName = this.genericTypeName;
        filteredList.setUiElementName(String.format("%s, filtered by: [%s]", this.uiElementName, conditionsAsString));
        filteredList.setParent(this.parent);
        filteredList.setDescription(this.description);
        filteredList.setSourceElements(filtered);
        filteredList.refresh();
        return filteredList;
    }

    /**
     * Checks if any element in the list is currently visible on the page.
     * <p>
     * This method iterates through the source elements to determine if any are displayed.
     * If at least one element is visible, the method returns {@code true}; otherwise, {@code false}.
     * </p>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Uses {@link SelenideElement#isDisplayed()} to check visibility.</li>
     *     <li>Returns {@code true} if at least one element is visible.</li>
     *     <li>Returns {@code false} if no elements are visible or if the list is empty.</li>
     * </ul>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * // Check if any elements in the list are visible
     * boolean isVisible = myList.isDisplayed();
     * }</pre>
     *
     * @return {@code true} if at least one element is visible; {@code false} otherwise
     */
    public boolean isDisplayed() {
        return sourceElements.stream().anyMatch(SelenideElement::isDisplayed);
    }

    /**
     * Returns the total number of components in the list.
     * <p>This method refreshes the list to ensure the count is accurate and up-to-date.</p>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Calls {@link #refresh()} to ensure the list components reflect the latest state.</li>
     *     <li>Returns the size of the components list.</li>
     * </ul>
     *
     * @return the number of components in the list
     */
    public int size() {
        lazyBuild();
        return sourceElements.size();
    }

    /**
     * Retries a condition until it is met or the maximum number of attempts is exhausted.
     *
     * @param condition   the condition to evaluate
     * @param maxAttempts the maximum number of retry attempts
     * @param onRetry     an optional action to perform on each retry (e.g., logging)
     */
    private void retryUntilConditionMet(Supplier<Boolean> condition, int maxAttempts, Runnable onRetry) {
        AtomicInteger attemptsRemaining = new AtomicInteger(maxAttempts);
        while (!condition.get() && attemptsRemaining.get() > 0) {
            if (onRetry != null) {
                onRetry.run();
            }
            Selenide.sleep(AlluriumConfig.retryIntervalMs());
            refresh();
            attemptsRemaining.decrementAndGet();
        }
    }

    /**
     * Checks whether the list contains an element with the exact specified ID.
     * <p>
     * This method iterates through the components to find an element that:
     * <ul>
     *     <li>Is currently visible on the page.</li>
     *     <li>Has an ID that matches the specified {@code id} exactly.</li>
     * </ul>
     * </p>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Calls {@link #refresh()} to ensure the list is up-to-date.</li>
     *     <li>Filters components based on visibility and exact ID match.</li>
     *     <li>Returns {@code true} if such an element is found; otherwise, {@code false}.</li>
     * </ul>
     *
     * <h3>Exception Handling:</h3>
     * <ul>
     *     <li>Catches {@link ElementNotFound} exceptions and logs the message.</li>
     * </ul>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * // Check if the list contains an element with a specific ID
     * boolean hasElement = myList.hasItem("element-id-123");
     * }</pre>
     *
     * @param id the exact ID of the element/widget to search for
     * @return {@code true} if the list contains an element with the exact ID; {@code false} otherwise
     */
    public boolean hasItem(String id) {
        refresh();
        Optional<T> target = Optional.empty();
        try {
            return components.stream()
                    .filter(item -> item.getRoot().isDisplayed())
                    .anyMatch(item -> item.getId().equals(id));
        } catch (ElementNotFound elementNotFound) {
            log.warn("Element not found while checking for ID '{}': {}", id, elementNotFound.getMessage());
            return false;
        }
    }

    /**
     * Checks whether the list contains an element with the specified text.
     * <p>
     * This method iterates through the components to find an element that:
     * <ul>
     *     <li>Is currently visible on the page.</li>
     *     <li>Contains the specified {@code text} in its visible content.</li>
     * </ul>
     * </p>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Calls {@link #refresh()} to ensure the list is up-to-date.</li>
     *     <li>Filters components based on visibility and text match.</li>
     *     <li>Returns {@code true} if such an element is found; otherwise, {@code false}.</li>
     * </ul>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * // Check if the list contains an element with specific text
     * boolean hasElementWithText = myList.hasItemWithText("Sample Text");
     * }</pre>
     *
     * @param text the text to search for in the list elements
     * @return {@code true} if the list contains an element with the specified text; {@code false} otherwise
     */
    public boolean hasItemWithText(String text) {
        refresh();
        return components.parallelStream().anyMatch(item -> {
            try {
                SelenideElement root = item.getRoot(); // Cache the result
                return root.isDisplayed() && root.text().contains(text);
            } catch (ElementNotFound ex) {
                log.warn("Element not found while checking for text '{}': {}", text, ex.getMessage());
                return false;
            }
        });
    }

    /**
     * Assertion: Verifies that the list size matches the provided expected value.
     * If the list size does not match, the method will retry until the configured retry attempts are exhausted.
     * == Step: Processed by Aspect ==
     *
     * @param size the expected size of the list
     * @throws IllegalArgumentException if the expected size is negative
     */
    @SuppressWarnings("unchecked")
    public void assertSize(Integer size) {
        lazyBuild();
        runAssertionStep(
                "assert_size",
                () -> {
                    if (size < 0) {
                        throw new IllegalArgumentException("Assertion size cannot be negative");
                    }
                    sourceElements.shouldHave(CollectionCondition.size(size));
                },
                Pair.of("{name}", wrappedName()),
                Pair.of("{size}", String.valueOf(size))
        );
    }

    /**
     * Assertion: Verifies that there is a specific number of visible components in the list.
     * Retries until the expected size is met or the retry limit is exhausted.
     *
     * @param expectedSize the expected number of visible components
     */
    public void assertVisibleSize(int expectedSize) {
        lazyBuild();
        sourceElements.filter(Condition.visible).shouldHave(CollectionCondition.size(expectedSize));
    }

    /**
     * Assertion: Verifies that the list size is greater than the specified value.
     * Retries until the condition is met or the retry limit is exhausted.
     *
     * @param size the value that the list size should be greater than
     */
    @SuppressWarnings("unchecked")
    public void assertSizeGreaterThan(int size) {
        lazyBuild();
        runAssertionStep(
                "assert_size_greater_than",
                () -> {
                    sourceElements.shouldHave(CollectionCondition.sizeGreaterThan(size));
                },
                Pair.of("{name}", wrappedName()),
                Pair.of("{size}", String.valueOf(size))
        );
    }

    /**
     * Assertion: Verifies that the list size is less than the specified value.
     * Retries until the condition is met or the retry limit is exhausted.
     *
     * @param size the maximum size the list should have
     */
    @SuppressWarnings("unchecked")
    public void assertSizeLessThan(int size) {
        lazyBuild();
        runAssertionStep(
                "assert_size_less_than",
                () -> {
                    if (size <= 0) {
                        throw new IllegalArgumentException("Expected size cannot be negative or zero");
                    }
                    sourceElements.shouldHave(CollectionCondition.sizeLessThan(size));
                },
                Pair.of("{name}", wrappedName()),
                Pair.of("{size}", String.valueOf(size))
        );
    }

    /**
     * Asserts that the list is empty (i.e., contains no components).
     * Retries until the condition is met or the retry limit is exhausted.
     */
    @SuppressWarnings("unchecked")
    public void assertEmpty() {
        lazyBuild();
        runAssertionStep(
                "list_assert_empty",
                () -> {
                    sourceElements.shouldBe(CollectionCondition.empty);
                },
                Pair.of("{name}", wrappedName())
        );
    }

    /**
     * Asserts that all items in the list are visible.
     * <p>
     * This method ensures that at least one element in the list is visible by checking each component's visibility.
     * If no elements are visible, an assertion error is thrown.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public void assertVisible() {
        lazyBuild();
        runAssertionStep(
                "list_assert_visible",
                () -> {
                    Assertions.assertThat(sourceElements.parallelStream().anyMatch(item -> item.is(Condition.visible)))
                    .as(uiElementName).isTrue();
                },
                Pair.of("{name}", wrappedName())
        );
    }

    /**
     * Asserts that no items in the list are visible.
     * <p>
     * This method checks that none of the elements in the list are currently displayed on the page.
     * If any element is found to be visible, an assertion error is thrown.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public void assertNotVisible() {
        lazyBuild();
        runAssertionStep(
                "list_assert_not_visible",
                () -> sourceElements.filter(Condition.visible).shouldHave(CollectionCondition.size(0)),
                Pair.of("{name}", wrappedName())
        );
    }

    /**
     * Asserts that the list contains at least one element with the specified text.
     *
     * @param text the text pattern to search for in the list elements
     */
    @SuppressWarnings("unchecked")
    public void assertHasItemsWithText(String text) {
        lazyBuild();
        runAssertionStep(
                "assert_has_items_with_text",
                () -> sourceElements.findBy(Condition.text(text)).should(Condition.exist),
                Pair.of("{name}", wrappedName()),
                Pair.of("{text}", text)
        );
    }

    /**
     * Asserts that the list does not contain any element with the specified text.
     *
     * @param text the text pattern to search for in the list elements
     */
    @SuppressWarnings("unchecked")
    public void assertHasNoItemsWithText(String text) {
        lazyBuild();
        runAssertionStep(
                "assert_has_no_items_with_text",
                () -> sourceElements.findBy(Condition.text(text)).shouldNot(Condition.exist),
                Pair.of("{name}", wrappedName()),
                Pair.of("{text}", text)
        );
    }

    /**
     * Asserts that the list contains an element with the specified ID.
     *
     * @param id the ID of the expected list item
     */
    @SuppressWarnings("unchecked")
    public void assertHasItem(String id) {
        lazyBuild();
        runAssertionStep(
                "list_assert_has_item",
                () -> {
                    AtomicInteger counter = new AtomicInteger(AlluriumConfig.retryAmount());
                    while (!hasItem(id) && counter.get() > 0) {
                        Selenide.sleep(AlluriumConfig.retryIntervalMs());
                        refresh();
                        counter.decrementAndGet();
                    }
                    Assertions.assertThat(hasItem(id))
                            .as(getUiElementName() + " has item with id=" + id)
                            .isTrue();
                },
                Pair.of("{name}", wrappedName()),
                Pair.of("{id}", id)
        );
    }

    /**
     * Asserts that the list does not contain an element with the specified ID.
     *
     * @param id the ID of the list item to check for absence
     */
    @SuppressWarnings("unchecked")
    public void assertHasNotItem(String id) {
        lazyBuild();
        runAssertionStep(
                "list_assert_has_not_item",
                () -> {
                    AtomicInteger counter = new AtomicInteger(AlluriumConfig.retryAmount());
                    while (hasItem(id) && counter.get() > 0) {
                        Selenide.sleep(AlluriumConfig.retryIntervalMs());
                        refresh();
                        counter.decrementAndGet();
                    }
                    Assertions.assertThat(hasItem(id)).isFalse();
                },
                Pair.of("{name}", wrappedName()),
                Pair.of("{id}", id)
        );
    }

}
