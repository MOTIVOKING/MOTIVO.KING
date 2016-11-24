package de.oszimt.fa45.motivoking.data.type;

import de.oszimt.fa45.motivoking.model.Day;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedCyberSamurai on 21.10.2016.
 */
public class JsonData extends Data {

    private long dayAI = 0;
    private long activityAI = 0;

    private List<Day> days;


    public JsonData() {
        days = new ArrayList<>();
    }


    public long getAI(String s) {
        long ai = 0;

        switch(s) {
            default:
                System.out.printf("AI of table %s not found.", s);
                break;
            case "day":
                dayAI++;
                ai = dayAI;
                break;
            case "activity":
                activityAI++;
                ai = activityAI;
                break;
        }

        return ai;
    }


    public List<Day> getDays() {
        return days;
    }
}
