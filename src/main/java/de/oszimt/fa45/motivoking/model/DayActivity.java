package de.oszimt.fa45.motivoking.model;

/**
 * Created by RedCyberSamurai on 28.11.2016.
 */
public class DayActivity extends Model {

    private long activityId;
    private long dayId;

    public DayActivity(long t_dayId, long t_activityId) {
        setId(0);
        activityId = t_activityId;
        dayId = t_dayId;
    }


    public long getActivityId() {
        return activityId;
    }


    public long getDayId() {
        return dayId;
    }
}
