package de.oszimt.fa45.motivoking.data.db;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class JSONDataHolder implements DataHolder {

    public JSONDataHolder() {

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
