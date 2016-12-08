package de.oszimt.fa45.motivoking.data.type;


import de.oszimt.fa45.motivoking.Error;

/**
 * Created by RedCyberSamurai on 21.10.2016.
 */
public class JsonData extends Data {

    private long dayAI = 0;
    private long activityAI = 0;
    private long dayActivityAI = 0;


    public JsonData() {}


    public long getAI(String s) {
        long ai = 0;

        switch(s) {
            case "day":
                dayAI++;
                ai = dayAI;
                break;
            case "activity":
                activityAI++;
                ai = activityAI;
                break;
            case "dayActivity":
                dayActivityAI++;
                ai = dayActivityAI;
                break;
            default:
                Error.set("AI of table " + s + " not found.");
                break;
        }

        return ai;
    }

}
