package dm.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StepTextYmlObject {

    public Map<String,Map<Integer,String>> english;
    public Map<String,Map<Integer,String>> russian;

    // for develop only method
    public static void writeExampleFile() {
        StepTextYmlObject obj = new StepTextYmlObject();
        obj.english = new HashMap<>();
        Map<Integer,String> engClick = new HashMap<>();
        engClick.put(1, "click on element");
        engClick.put(2, "click on element 2");
        obj.english.put("click", engClick);
        Map<Integer,String> engHover = new HashMap<>();
        engHover.put(1, "hover on element");
        engHover.put(2, "hover on element in");
        obj.english.put("hover", engHover);

        obj.russian = new HashMap<>();
        Map<Integer,String> rusClick = new HashMap<>();
        rusClick.put(1, "нажимаем на элемент");
        rusClick.put(2, "нажимаем на элемент на");
        obj.russian.put("click", rusClick);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        try {
            mapper.writeValue(new File("src/main/resources/exampleStepsGenerated.yaml"), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String,Map<Integer,String>> getLocalizedSteps(String localization) {
        switch (localization) {
            case "ru":
            case "russian":
                return russian;
            default:
                return english;
        }
    }
}
