package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class ProgramLogic2 implements ProgramLogic {

    private DataHolder mDataHolder;

    public ProgramLogic2(DataHolder t_dataHolder) {
        mDataHolder = t_dataHolder;
    }


    /**
     * Creates a day.
     */
    public void createDay() {
        Date date = new Date();
        Day day = new Day(date);

        mDataHolder.addDay(day);
    }


    @Override
    public void createDay(String dateString) {

    }

    /**
     * Creates an activity to a specified day.
     * @param id    Id of the day.
     */
    public void createActivity(long id, Activity activity) {
        mDataHolder.addActivity(id, activity);
        System.out.printf("Added activity id: %s\n", activity.getId());
    }


    /**
     * Gets a day by a specified id.
     * @param id
     * @return
     */
    public Day getDay(long id) {
        return mDataHolder.findDayById(id);
    }


    /**
     * Returns a list of days.
     * @return
     */
    public List<Day> getDays() {
        List<Day> days = new ArrayList<>();
        days.add(new Day(new Date(2016, 05, 11)));
        days.add(new Day(new Date(2016, 11, 16)));
        return days;

        // return mDataHolder.findAllDays();
    }

    @Override
    public List<Activity> getAllActivities() {
        return mDataHolder.findAllActivities();
    }

    @Override
    public void addActivity() {

    }


    /**
     * Returns a list of activities by a specified day.
     * @param id    Id of the day.
     * @return
     */
    public List<Activity> getActivities(long id) {
        return mDataHolder.findActivitiesByDayId(id);
    }
}
