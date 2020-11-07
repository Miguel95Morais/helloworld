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

}