package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by boerg on 12.10.2016.
 */
public interface ProgramLogic {
    void createDay();
    void createActivity(int id);
    Day getDay(int id);
    List<Day> getDays();
    List<Activity> getActivities(int id);
}
