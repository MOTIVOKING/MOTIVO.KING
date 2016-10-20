package de.oszimt.fa45.motivoking.data;

import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by boerg on 12.10.2016.
 */
public interface DataHolder {
    Day findDayById(long dayId);
    List<Day> findAllDays();
    List<Activity> findActivitiesByDayId(long dayId);
    void addDay(Day day);
    void addActivity(long dayId, Activity activity);
}
