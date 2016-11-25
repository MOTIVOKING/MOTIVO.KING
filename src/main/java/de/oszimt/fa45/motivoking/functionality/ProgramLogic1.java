package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.Error;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class ProgramLogic1 implements ProgramLogic {

    private DataHolder mDataHolder;

    public ProgramLogic1(DataHolder t_dataHolder) {

        mDataHolder = t_dataHolder;
    }


    /**
     * Creates a day.
     * @param dateString    A string of format "YYYY-mm-dd".
     */
    public void createDay(String dateString) {
        // NOTE: supporting multiple date formats?
        DateFormat format = new SimpleDateFormat("YYYY-mm-dd", Locale.GERMANY);

        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            Error.set("Error: Invalid date format.");
            // e.printStackTrace();
        }

        if(date != null) {
            Day day = new Day(date);
            mDataHolder.addDay(day);
        }
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
     * @param id The id of the day.
     * @return  Returns the found day.
     */
    public Day getDay(long id) {
        return mDataHolder.findDayById(id);
    }


    /**
     * Returns a list of days.
     * @return  Day list.
     */
    public List<Day> getDays() {
        return mDataHolder.findAllDays();
    }


    /**
     * Returns a list of activities by a specified day.
     * @param id    Id of the day.
     * @return  Activities list.
     */
    public List<Activity> getActivities(long id) {
        return mDataHolder.findActivitiesByDayId(id);
    }

}
