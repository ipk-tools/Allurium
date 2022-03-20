package pk.tools;

import com.epam.jdi.tools.pairs.Pair;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public enum StepText {
    Click(
            "Click on {element} {name}}",
            "Click on {element} {name}} in {parent}",
            "Нажимаем - {element} {name}",
            "Нажимаем - {element} {name} в виджете {parent}"
    ),
    Hover(
            "Hover cursor on {element} {name}",
            "Hover cursor on {element} {name} in {parent}",
            "Наводим курсор на - {element} {name}",
            "Наводим курсор на - {element} {name} в виджете {parent}"
    ),
    Scroll(
            "Scroll to {element} {name}",
            "Scroll to {element} {name} in {parent}",
            "Прокручиваем к элементу {element} {name}",
            "Прокручиваем к элементу {element} {name} в виджете {parent}"
    ),
    Get_text(
            "Reading text from {element} {name}",
            "Reading text from {element} {name} in {parent}",
            "Получаем текст из - {element} {name}",
            "Получаем текст из - {element} {name} в виджете {parent}"
    ),
    Assert_text(
            "Check {element} {name} contains text: {text}",
            "Check {element} {name} contains text: {text} in {parent}",
            "Проверяем '{element} {name}' на наличие текста: {text}",
            "Проверяем '{element} {name}' на наличие текста: {text} в виджете {parent}"
    ),
    Assert_value(
            "Check {element} {name} contains value: {text}",
            "Check {element} {name} contains value: {text} in {parent}",
            "Проверяем '{element} {name}' содержит значение: {text}",
            "Проверяем '{element} {name}' содержит значение: {text} в виджете {parent}"
    ),
    Assert_exist(
            "Verify that {element} {name} is exist",
            "Verify that {element} {name} is exist in {parent}",
            "Проверяем что '{element} {name}' присутствует на странице",
            "Проверяем что '{element} {name}' присутствует на странице в виджете {parent}"
    ),
    Assert_not_exist(
            "Verify that {element} {name} is not exist",
            "Verify that {element} {name} is not exist in {parent}",
            "Проверяем что '{element} {name}' отсутствует на странице",
            "Проверяем что '{element} {name}' отсутствует на странице в виджете {parent}"
    ),
    Assert_visible(
            "Verify that {element} {name} is visible on page",
            "Verify that {element} {name} is visible on page in {parent}",
            "Проверяем, что '{element} {name}' отображается на странице",
            "Проверяем, что '{element} {name}' отображается на странице в виджете {parent}"
    ),

    Assert_visibleInViewPort(
            "Verify that {element} {name} is visible in view port on page",
            "Verify that {element} {name} is visible in view port on page in {parent}",
            "Проверяем, что '{element} {name}' отображается в видимой части страницы",
            "Проверяем, что '{element} {name}' отображается в видимой части страницы в виджете {parent}"
    ),
    Assert_not_visible(
            "Verify that {element} {name} is not visible on page",
            "Verify that {element} {name} is not visible on page in {parent}",
            "Проверяем, что {element} {name} не отображается на странице",
            "Проверяем, что {element} {name} не отображается на странице в виджете {parent}"
    ),
    Assert_visible_with_duration(
            "Verify that {element} {name} will be shown during next {sec} seconds",
            "Verify that {element} {name} will be shown during next {sec} seconds in {parent}",
            "Проверяем, что {element} {name} отобразится на странице в течение {sec} секунд",
            "Проверяем, что {element} {name} отобразится на странице в течение {sec} секунд в виджете {parent}"
    ),
    Assert_has_css_class(
            "Verify {element} {name} has CSS class {clazz}",
            "Verify {element} {name} has CSS class {clazz} in {parent}",
            "Проверяем, что {element} {name} имеет CSS класс {clazz}",
            "Проверяем, что {element} {name} имеет CSS класс {clazz} в виджете {parent}"
    ),
    Assert_has_not_css_class(
            "Verify {element} {name} has not CSS class {clazz}",
            "Verify {element} {name} has not CSS class {clazz} in {parent}",
            "Проверяем, что {element} {name} не имеет CSS класс {clazz}",
            "Проверяем, что {element} {name} не имеет CSS класс {clazz} в виджете {parent}"
    ),
    Clear_text_field(
            "Clear text input field {name}",
            "Clear text input field {name} in widget {parent}",
            "Очищаем поле ввода {name}",
            "Очищаем поле ввода {name} в виджете {parent}"
    ),
    Verify_text_field_blank(
            "Verify input field {name} is blank",
            "Verify input field {name} in widget {parent} is blank",
            "Проверяем, что поле ввода {name} пустое",
            "Проверяем, что поле ввода {name} пустое в виджете {parent}"
    ),
    Field_has_value(
            "Input field {name} is not blank, when it should be",
            "Input field {name} in {parent} widget is not blank, when it should be",
            "Поле ввода {name} содержит значение, когда должно быть пустым",
            "Поле ввода {name} в виджете {parent} содержит значение, когда должно быть пустым"
    ),
    Text_field_press_enter(
            "Press key ENTER in text field {name} input field",
            "Press key ENTER in text field {name} input field in widget {parent}",
            "Нажимаем клавишу ENTER в текстовом поле ввода {name}",
            "Нажимаем клавишу ENTER в текстовом поле ввода {name} в виджете {parent}"
    ),
    Write(
            "Write text {text} into input field {name}",
            "Write text {text} into input field {name} in {parent} widget",
            "Вводим текст {text} в поле ввода {name}",
            "Вводим текст {text} в поле ввода {name} в виджете {parent}"
    ),
    Drad_and_Drop(
            "Drag {from_name} to {to_name}",
            "Drag {from_name} to {to_name}",
            "Перетаскиваем {from_name} в {to_name}",
            "Перетаскиваем {from_name} в {to_name}"
    );


    String en;
    String enDetailed;
    String ru;
    String ruDetailed;

    private String getLocalizedPattern() {
        String loc = "en";
        switch (loc) {
            default:
                return en;
            case "ru":
                return ru;
        }
    }

    private String getLocalizedDetailedPattern() {
        String loc = "en";
        switch (loc) {
            default:
                return enDetailed;
            case "ru":
                return ruDetailed;
        }
    }

    public String getStepText(Pair<String,String>... patterns) {
        String name = "";
        String elementType = "";
        String parent = "";

        for (Pair<String, String> pattern : patterns) {
            if (pattern.key.equals("{name}") && !pattern.value.equals("")) name = pattern.value;
            if (pattern.key.equals("{element}") && !pattern.value.equals("")) elementType = pattern.value;
            if (pattern.key.equals("{parent}") && !pattern.value.equals("")) parent = pattern.value;
        }

        String pattern = "";
        if (!name.equals("") && !parent.equals("")) {
            pattern = getLocalizedDetailedPattern();
        } else {
            pattern = getLocalizedPattern();
        }

        if (elementType.equals(""))
            pattern = pattern.replace("{element}", StepsLocalizedPattern.element.getLocalizedValue());

        for (Pair<String, String> namesPattern : patterns) {
            pattern = pattern.replace(namesPattern.key, namesPattern.value);
        }

        return pattern;
    }
    

    public String getStepText(List<Pair<String,String>> patternValues) {
        Pair<String,String>[] arrayOfPatterns = new Pair[patternValues.size()];
        return getStepText(patternValues.toArray(arrayOfPatterns));
    }
}
