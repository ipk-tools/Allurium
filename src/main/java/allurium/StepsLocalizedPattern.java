package allurium;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StepsLocalizedPattern {
    element("element", "элемент"),
    widget("widget", "виджет"),
    iframe("iframe", "iframe");

    String english;
    String russian;

    public String getLocalizedValue(String localization) {
        switch (localization) {
            case "russian":
                return russian;
            default:
                return english;
        }
    }
}
