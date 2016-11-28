package de.oszimt.fa45.motivoking.data.type;

import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public abstract class Data {

    private List<Day> days = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();


    public List<Day> getDays() {
        return days;
    }


    public List<Activity> getActivities() { return activities; }
}
