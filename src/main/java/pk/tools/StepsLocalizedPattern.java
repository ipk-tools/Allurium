package pk.tools;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StepsLocalizedPattern {
    element("element", "элемент"),
    icon("icon", "иконка");

    String en;
    String ru;

    public String getLocalizedValue() {
        String loc = "en";
        switch (loc) {
            default:
                return en;
            case "ru":
                return ru;
        }
    }
}
