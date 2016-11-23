package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

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
     */
    public void createDay(String dateString) {
        System.out.println("creating day");
        DateFormat format = new SimpleDateFormat("YYYY-mm-dd", Locale.GERMANY);

        System.out.println("setting date format");

        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format.");
            e.printStackTrace();
        }
        Day day = new Day(date);

        System.out.println("adding day to database");
        mDataHolder.addDay(day);
    }


    /**
     * Creates an activity to a specified day.
     * @param id    Id of the day.
     */
    public void createActivity(int id) {
        Activity activity = new Activity("Some Activity", 100, 999);

        mDataHolder.addActivity(id, activity);
        System.out.printf("Added activity id: %s\n", activity.getId());
    }


    /**
     * Gets a day by a specified id.
     * @param id
     * @return
     */
    public Day getDay(int id) {
        return mDataHolder.findDayById(id);
    }


    /**
     * Returns a list of days.
     * @return
     */
    public List<Day> getDays() {
        return mDataHolder.findAllDays();
    }


    /**
     * Returns a list of activities by a specified day.
     * @param id    Id of the day.
     * @return
     */
    public List<Activity> getActivities(int id) {
        return mDataHolder.findActivitiesByDayId(id);
    }
}
