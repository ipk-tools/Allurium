package dm.tools;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ElementType {

    BUTTON("button", "кнопка"),
    LINK("link", "ссылка"),
    TEXT("text", "текст"),
    ICON("icon", "иконка"),
    IMAGE("img", "изображение"),
    INPUT("input text field","поле ввода"),
    TEXTFIELD_REQUIRED("required text field", "обязательное текстовое поле ввода"),
    TEXTAREA("textarea", "текстовая область"),
    TEXTAREA_REQUIRED("required text area", "обязательная текстовая область ввода"),
    CHECKBOX("checkbox", "флаг"),
    CHECKBOX_REQUIRED("checkbox", "флаг"),
    RADIOBUTTON("radio button", "радио кнопка"),
    RADIOBUTTON_REQUIRED("radio button", "радио кнопка"),
    UPLOAD_INPUT("upload field", "поле загрузки файла"),
    SELECT("Select", "cписок опций"),
    DROPDOWN_SELECT("dropdown select", "выпадающий список"),
    LINE("line", "линия"),
    STRING("string", "строчка"),
    NUMBER("number", "число"),
    HEADER("header", "загаловок"),
    TITLE("title", "названире"),
    LABEL("label", "лейбл"),
    SIGN("sign", "знак"),
    POINTER("pointer", "указатель"),
    UNIT("unit", "юнит"),
    ITEM("item", "пункт"),
    TAB("tab", "таб"),
    MENU_ITEM("menu item", "пункт меню"),
    LIST_ITEM("list item", "элемент списка"),
    TABLE_RECORD("table record", "запись таблицы"),
    TABLE_CELL("table cell", "ячейка таблицы"),
    CELL("cell", "ячейка"),
    PARTITION("partition", "раздел"),
    PARAGRAPH("paragraph", "параграф"),
    ROW("row", "строка"),
    VALUE("value", "значение"),
    CAROUSEL("carousel", "слайдер"),
    IMAGE_SLIDER("image_slider", "слайдер изображений"),
    SWITCHER("switcher", "переключатель"),
    FORM("form", "форма"),
    SYMBOL("symbol", "символ");

    String english;
    String russian;

    public String getType() {
        switch (AlluriumConfig.localization()) {
            case "ru":
            case "russian":
                return russian;
            default:
                return english;
        }
    }
}
