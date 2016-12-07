package de.oszimt.fa45.motivoking.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by RedCyberSamurai on 28.11.2016.
 */
@Entity
@Table(name = "dayactivities")
public class DayActivity extends Model {

    private long activityId;
    private long dayId;

    public DayActivity(long t_dayId, long t_activityId) {
        activityId = t_activityId;
        dayId = t_dayId;
    }

    public DayActivity() {
    }


    public long getActivityId() {
        return activityId;
    }

    public long getDayId() {
        return dayId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }
}
