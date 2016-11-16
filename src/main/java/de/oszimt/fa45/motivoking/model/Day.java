package de.oszimt.fa45.motivoking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class Day {
    private long id;
    private Date date;
    private List<Activity> activities;

    public Day() {
        new Day(new Date());
    }

    public Day(Date t_date) {
        date = t_date;
        id = 0;
        activities = new ArrayList<Activity>();
    }


    public void setId(long id) {
        id = id;
    }


    public long getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        date = date;
    }


    public List<Activity> getActivities() {
        return activities;
    }


    public void setActivity(Activity activity) {
        activities.add(activity);
    }
}
