package de.oszimt.fa45.motivoking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class Day implements Model {
    private long            m_id;
    private Date            m_date;
    private List<Activity>  m_activities;

    public Day() {
        m_date = new Date();
        this.init();
    }

    public Day(Date t_date) {
        m_date = t_date;
        this.init();
    }


    private void init() {
        m_id = 0;
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
