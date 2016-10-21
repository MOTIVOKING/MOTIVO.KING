package de.oszimt.fa45.motivoking.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedCyberSamurai on 21.10.2016.
 */
public class JsonData {
    private int m_dayAI = 0;
    private int m_activityAI = 0;

    private List<Day> m_days;

    public JsonData() {
        m_days = new ArrayList<Day>();
    }


    public int getAI(String s) {
        int ai = 0;

        switch(s) {
            default:
                System.out.printf("AI of table %s not found.", s);
                break;
            case "day":
                m_dayAI++;
                ai = m_dayAI;
                break;
            case "activity":
                m_activityAI++;
                ai = m_activityAI;
                break;
        }

        return ai;
    }


    public List<Day> getDays() {
        return m_days;
    }
}
