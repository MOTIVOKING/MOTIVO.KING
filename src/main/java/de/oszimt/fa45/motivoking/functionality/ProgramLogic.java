package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.util.List;

/**
 * Created by boerg on 12.10.2016.
 */
public interface ProgramLogic {
    
    void createDay(String dateString);
    void createActivity(long id, Activity activity);

    void addActivity(long t_dayId, long t_activityId);

    Day getDay(long id);
    List<Day> getDays();
    List<DayActivity> getDayActivities();

    List<Activity> getAllActivities();
    List<Activity> getActivities(long id);
}