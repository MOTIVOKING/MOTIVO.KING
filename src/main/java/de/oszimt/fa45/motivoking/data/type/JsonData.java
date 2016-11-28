package de.oszimt.fa45.motivoking.data.type;


/**
 * Created by RedCyberSamurai on 21.10.2016.
 */
public class JsonData extends Data {

    private long dayAI = 0;
    private long activityAI = 0;


    public JsonData() {}


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
}
