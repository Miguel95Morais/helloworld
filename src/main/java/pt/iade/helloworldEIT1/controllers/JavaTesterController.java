package pt.iade.helloworldEIT1.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.helloworldEIT1.models.CurricularUnit;

@RestController
@RequestMapping(path = "/api/java/tester")
public class JavaTesterController {
    private Logger logger = LoggerFactory.getLogger(JavaTesterController.class);
    private ArrayList<CurricularUnit> units = new ArrayList<CurricularUnit>();

    @PostMapping(path = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit) {
        logger.info("Added unit " + unit.getName());
        units.add(unit);
        return unit;
    }

    @GetMapping(path = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnits() {
        logger.info("Get " + units.size() + " Units");
        return units;
    }

    // Arrays don't work
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInformation() {
        logger.info("See information");
        String name = "Miguel Morais";
        int number = 50034581;
        double height = 1.83;
        boolean isFan = true;
        String colours[] = { "Red", "Green", "Blue", "None" };
        String clubs[] = { "SLB", "SCP", "FCP", "BVB" };

        if (isFan) {
            return "Done by " + name + " with number " + number + ".\n" + "I am " + height
                    + " tall and I am a fan of footbal." + "\n" + "My favourite club is " + clubs
                    + " and their colours are " + colours + ".";
        } else if (!isFan) {
            return "Done by " + name + " with number " + number + ".\n" + "I am " + height
                    + " tall  and not a fan of football.";
        } else {
            return ("What are you?");
        }

    }

    @GetMapping(path = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthor(@PathVariable("name") String name) {
        logger.info("Saying Hello to " + name);
        return "Hello " + name;
    }

    @GetMapping(path = "/access/{student}/{covid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent, @PathVariable("covid") boolean hasCovid) {
        if (isStudent && (!hasCovid)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent, @PathVariable("temperature") double hasCovid,
            @PathVariable("classType") String type) {
        if (isStudent && type.equals("presential") && (hasCovid < 37.5 && hasCovid > 34.5)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping(path = "/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getEvacuation(@PathVariable("fire") boolean isfire,
            @PathVariable("numberOfCovids") int numberOfCovids, @PathVariable("powerShutdown") boolean ispowerShutdown,
            @PathVariable("comeBackTime") int comeBackTime) {
        if (isfire) {
            return true;
        } else if (numberOfCovids > 5) {
            return true;
        } else if (ispowerShutdown && comeBackTime > 15) {
            return true;
        }
        return false;
    }

    private double grades[] = { 10, 12, 11.5 };
    private String ucs[] = { "FP", "POO", "BD" };

    // Calculate and return the average
    @GetMapping(path = "/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public double average() {
        double total = 0;
        for (int i = 0; i < grades.length; i++) {
            total = total + grades[i];
        }
        double average = total / (double) grades.length;
        return average;
    }

    // Return the maximum grade
    @GetMapping(path = "/maxgrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double maxGrade() {
        double max = 0;
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] > max) {
                max = grades[i];
            }
        }
        return max;
    }

    // Given the name of the UC return the grade
    @GetMapping(path = "/ucgrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double ucGrade() {
        String uc = "POO";
        double ucgrade = 0;
        for (int i = 1; i < ucs.length; i++) {
            if (ucs[i].equals(uc)) {
                ucgrade = grades[i];
            }
        }
        return ucgrade;
    }

    // Given minimum and maximum grade, return how many UCs have grades in those
    // limits
    @GetMapping(path = "/minmaxgrade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double minmaxGrade() {
        double min = 9.5;
        double max = 12.5;
        double ucgradelimits = 0;
        for (int i = 1; i < ucs.length; i++) {
            if (grades[i] >= min && (grades[i] <= max)) {
                ucgradelimits++;
            }
        }
        return ucgradelimits;
    }

    // Return a string with a text with all UC names and grades
    @GetMapping(path = "/text", produces = MediaType.APPLICATION_JSON_VALUE)
    public String text() {
        String text = "";
        for (int i = 1; i < ucs.length; i++) {
            text = text + ucs[i] + " " + grades[i] + " ";
        }
        return text;
    }

}
