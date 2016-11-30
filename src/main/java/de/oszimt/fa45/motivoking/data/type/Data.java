package de.oszimt.fa45.motivoking.data.type;

import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public abstract class Data {

    private List<Day> days = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();
    private List<DayActivity> dayActivities = new ArrayList<>();


    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> t_days) { days = t_days; };


    public List<Activity> getActivities() { return activities; }

    public void setActivities(List<Activity> t_activities) { activities = t_activities; }


    public List<DayActivity> getDayActivities() {
        return dayActivities;
    }

    public void setDayActivities(List<DayActivity> t_dayActivities) { dayActivities = t_dayActivities; }
}
