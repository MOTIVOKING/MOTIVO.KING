package de.oszimt.fa45.motivoking.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedCyberSamurai on 21.10.2016.
 */
public class JsonData implements Model {
    private int dayAI = 0;
    private int activityAI = 0;

    private List<Day> days;

    public JsonData() {
        days = new ArrayList<Day>();
    }


    public int getAI(String s) {
        int ai = 0;

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
