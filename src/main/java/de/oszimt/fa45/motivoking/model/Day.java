package de.oszimt.fa45.motivoking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class Day {
    private long            m_id;
    private Date            m_date;
    private List<Activity>  m_activities;

    public Day(Date t_date) {
        m_id = 0; // TODO data holder
        m_date = t_date;
        m_activities = new ArrayList<Activity>();
    }


    public void setId(long t_id) {
        m_id = t_id;
    }


    public long getId() {
        return m_id;
    }


    public Date getDate() {
        return m_date;
    }


    public void setDate(Date t_date) {
        m_date = t_date;
    }


    public List<Activity> getActivities() {
        return m_activities;
    }


    public void setActivity(Activity t_activity) {
        m_activities.add(t_activity);
    }
}
