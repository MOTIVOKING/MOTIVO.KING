package de.oszimt.fa45.motivoking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class Day extends Model {

    private Date date;
    private List<Activity> activities;


    /**
     * Initializes the day of the current date.
     */
    public Day() {
        new Day( new Date() );
    }


    /**
     * Initializes the day with a specific date.
     * @param t_date    The date to set.
     */
    public Day(Date t_date) {
        date = t_date;
        setId(0);
        activities = new ArrayList<>();
    }


    /**
     * Gets the date of the day.
     * @return  Date with date value.
     */
    public Date getDate() {
        return date;
    }


    /**
     * Sets the date of the day.
     * @param t_date    The date to set.
     */
    public void setDate(Date t_date) {
        date = t_date;
    }


    /**
     * Returns a list of activities of the day.
     * @return  Collection of activities of the day.
     */
    public List<Activity> getActivities() {
        return activities;
    }


    /**
     * Adds an activity to the day.
     * @param t_activity    The activity to add.
     */
    public void setActivity(Activity t_activity) {
        activities.add(t_activity);
    }
}
