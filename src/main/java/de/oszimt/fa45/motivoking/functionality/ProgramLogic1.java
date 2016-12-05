package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * @param dateString    A string of format "yyyy-MM-dd".
     */
    public void createDay(String dateString) {
        // NOTE: supporting multiple date formats?
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);

        Date date = null;
        try {
            format.setLenient(false); // date validation
            date = format.parse(dateString);
        } catch (ParseException e) {
            Error.set("Error: Invalid date format.");
            // e.printStackTrace();
        }

        if(date != null) {

            if(format.format(date).equals(dateString)) {
                Day day = new Day(date);
                mDataHolder.addDay(day);
            }
        }
    }


    /**
     * Creates an activity to a specified day.
     * @param id    Id of the day.
     */
    public void createActivity(long id, Activity activity) {
        int relaxLevel = activity.getRelaxLevel();
        int stressLevel = activity.getStressLevel();

        if(relaxLevel > Activity.MAX_LEVEL || relaxLevel < Activity.MIN_LEVEL ||
                stressLevel > Activity.MAX_LEVEL || stressLevel < Activity.MIN_LEVEL) {

            Error.set("Sowohl das Stress-, als auch das Entspannungslevel");
            Error.set("muss eine Zahl zwischen " + Activity.MAX_LEVEL + " und " + Activity.MIN_LEVEL + " enthalten.");
            return;
        }

        mDataHolder.addActivity(id, activity);
    }


    /**
     * Gets a day by a specified id.
     * @param id The id of the day.
     * @return  Returns the found day.
     */
    public Day getDay(long id) {
        Day d = mDataHolder.findDayById(id);
        return d != null ? d : new Day();
    }


    /**
     * Returns a list of days.
     * @return  Day list.
     */
    public List<Day> getDays() {
        List<Day> days = mDataHolder.findAllDays();
        return days != null ? days : new ArrayList<>();
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
     * @return  Activities list.
     */
    public List<Activity> getActivities(long id) {
        List<Activity> activities = mDataHolder.findActivitiesByDayId(id);
        return activities != null ? activities : new ArrayList<>();
    }

}
