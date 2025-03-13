package allurium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StepTextProvider {

    private static StepTextYmlObject stepTextYmlObject;
    private static final String localization = AlluriumConfig.localization();
    private static final int stepDetailingLevel = Integer.parseInt(AlluriumConfig.stepDetailing());

    static {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try (InputStream input = getYmlInputStream()) {
            if (input == null) {
                throw new IOException("Could not find allurium-steps.yml or allurium-steps.yml in resources.");
            }
            stepTextYmlObject = mapper.readValue(input, StepTextYmlObject.class);
        } catch (IOException e) {
            log.warn("Failed during parsing of the steps YAML file.");
            e.printStackTrace();
        }
    }

    private static InputStream getYmlInputStream() {
        // First, attempt to load the user's custom allurium-steps.yml from the classpath
        InputStream input = StepTextProvider.class.getClassLoader().getResourceAsStream("allurium-steps.yml");
        if (input != null) {
            log.info("Loaded user's allurium-steps.yml from classpath.");
            return input;
        }

        // If the user's file is not found, load the library's default allurium-steps.yml
        input = StepTextProvider.class.getClassLoader().getResourceAsStream("allurium-steps.yml");
        if (input != null) {
            log.info("Loaded default allurium-steps.yml from library resources.");
            return input;
        }

        // If neither file is found, return null
        log.warn("Could not find allurium-steps.yml or allurium-steps.yml in the classpath.");
        return null;
    }

    public static String getStepTextTemplate(String stepName) {
        return stepTextYmlObject.getLocalizedSteps(localization).get(stepDetailingLevel).get(stepName);
    }

    private static String getStepTextTemplate(String stepName, String localization, int stepDetailingLevel) {
        return stepTextYmlObject.getLocalizedSteps(localization).get(stepName).get(stepDetailingLevel);
    }


    public static String getStepText(String stepName, Pair<String,String>... patterns) {
        String name = "";
        String elementType = "";
        String parent = "";

        for (Pair<String, String> pattern : patterns) {
            if (pattern.getKey().equals("{name}") && !pattern.getValue().equals("")) name = pattern.getValue();
            if (pattern.getKey().equals("{element}") && !pattern.getValue().equals("")) elementType = pattern.getValue();
            if (pattern.getKey().equals("{parent}") && !pattern.getValue().equals("")) parent = pattern.getValue();
        }

        String pattern = "";
        if (!name.equals("") && !parent.equals("") && AlluriumConfig.stepDetailing().equals("2")) {
            pattern = getStepTextTemplate(stepName, localization, 2);
        } else {
            pattern = getStepTextTemplate(stepName, localization, 1);
        }

        if (elementType.equals(""))
            pattern = pattern.replace("{element}", StepsLocalizedPattern.element.getLocalizedValue(localization));

        for (Pair<String, String> namesPattern : patterns) {
            pattern = pattern.replace(namesPattern.getKey(), namesPattern.getValue());
        }

        return pattern;
    }


    public static String getStepText(String stepName, List<Pair<String,String>> patternValues) {
        Pair<String,String>[] arrayOfPatterns = new Pair[patternValues.size()];
        return getStepText(stepName, patternValues.toArray(arrayOfPatterns));
    }

    public static String getStepText(String stepName, Optional<AbstractWidget> parentElement,
                                     Pair<String,String>... patterns) {
        String name = "";
        String elementType = "";
        String parent = "";

        for (Pair<String, String> pattern : patterns) {
            if (pattern.getKey().equals("{name}") && !pattern.getValue().equals("")) name = pattern.getValue();
            if (pattern.getKey().equals("{element}") && !pattern.getValue().equals("")) elementType = pattern.getValue();
            if (parentElement.isPresent())
                if (pattern.getKey().equals("{parent}") && !pattern.getValue().equals("")) parent = pattern.getValue();
        }

        String pattern = "";
        if (!name.equals("") && parentElement.isPresent() && !parentElement.get().wrappedName()
                .equals(AlluriumConfig.highlighterStart() + AlluriumConfig.highlighterEnd())
                && AlluriumConfig.stepDetailing().equals("2") )
        {
            pattern = getStepTextTemplate(stepName, localization, 2);
        } else {
            pattern = getStepTextTemplate(stepName, localization, 1);
        }

        if (elementType.equals(""))
            pattern = pattern.replace("{element}", StepsLocalizedPattern.element.getLocalizedValue(localization));

        if (parentElement.isPresent())
            pattern = pattern.replace("{parent}", parentElement.get().wrappedName());

        for (Pair<String, String> namesPattern : patterns) {
            pattern = pattern.replace(namesPattern.getKey(), namesPattern.getValue());
        }

        return pattern;
    }
}
