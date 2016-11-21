package de.oszimt.fa45.motivoking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class Day implements Model {
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


    public void setId(long t_id) {
        id = t_id;
    }


    public long getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date t_date) {
        date = t_date;
    }


    public List<Activity> getActivities() {
        return activities;
    }


    public void setActivity(Activity t_activity) {
        activities.add(t_activity);
    }
}
