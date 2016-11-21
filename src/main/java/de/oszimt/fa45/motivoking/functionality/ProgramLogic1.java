package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.Controller.ActivityController;
import de.oszimt.fa45.motivoking.Controller.Controller;
import de.oszimt.fa45.motivoking.Controller.DayController;
import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class ProgramLogic1 implements ProgramLogic{
    private DataHolder mDataHolder;

    private Controller mDayController;
    private Controller mActivityController;

    public ProgramLogic1(DataHolder t_dataHolder) {
        mDataHolder = t_dataHolder;

        mDayController = new DayController();
        mActivityController = new ActivityController();
    }

    public void createDay() {
        Day day = mDayController.create();
        mDataHolder.addDay(day);
    }

    public void createActivity(int id) {
        // TODO assign activity to a selected day

        Activity activity = mActivityController.create();
        System.out.printf("Added activity id: %s\n", activity.getId());
        mDataHolder.addActivity(activity.getId(), activity);
    }

    public Day getDay(int id) {
        return mDataHolder.findDayById(id);
    }

    public List<Day> getDays() {
        return mDataHolder.findAllDays();
    }

    public List<Activity> getActivities(int id) {
        return mDataHolder.findActivitiesByDayId(id);
    }
}
