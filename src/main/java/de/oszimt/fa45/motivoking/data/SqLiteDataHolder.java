package de.oszimt.fa45.motivoking.data;

import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.data.DataHolder;

import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class SqLiteDataHolder implements DataHolder {
    @Override
    public Day findDayById(long dayId) {
        return null;
    }

    @Override
    public List<Day> findAllDays() {
        return null;
    }

    @Override
    public List<Activity> findActivitiesByDayId(long dayId) {
        return null;
    }

    @Override
    public void addDay(Day day) {

    }

    @Override
    public void addActivity(long dayId, Activity activity) {

    }
}
