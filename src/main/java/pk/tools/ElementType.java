package pk.tools;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ElementType {

    BUTTON("button", "кнопка"),
    LINK("link", "ссылка"),
    TEXT("text", "текст"),
    ICON("icon", "иконка"),
    IMAGE("img", "изображение"),
    TAG("tag", "тег"),
    INPUT("input text field","поле ввода");

    String en;
    String ru;

    public String getType() {
        String loc = "ru";
        switch (loc) {
            default:
                return en;
            case "ru":
                return ru;
        }
    }
}
