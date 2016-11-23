package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.controller.ActivityController;
import de.oszimt.fa45.motivoking.controller.Controller;
import de.oszimt.fa45.motivoking.controller.DayController;
import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class ProgramLogic2 implements ProgramLogic {
    private DataHolder mDataHolder;

    private Controller mDayController;
    private Controller mActivityController;

    public ProgramLogic2(DataHolder t_dataHolder) {
        mDataHolder = t_dataHolder;

        mDayController = new DayController();
        mActivityController = new ActivityController();
    }

    public void createDay() {
        Day day = mDayController.create();
        mDataHolder.addDay(day);
    }


    public void createActivity(int t_id) {
        // TODO assign activity to a selected day

        Activity activity = mActivityController.create();
        mDataHolder.addActivity(activity.getId(), activity);
    }


    public Day getDay(int t_id) {
        return mDataHolder.findDayById(t_id);
    }


    public List<Day> getDays() {
        return mDataHolder.findAllDays();
    }


    public List<Activity> getActivities(int t_id) {
        return mDataHolder.findActivitiesByDayId(t_id);
    }
}
