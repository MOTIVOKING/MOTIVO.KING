package de.oszimt.fa45.motivoking.model;

/**
 * Created by boerg on 13.10.2016.
 */
public class Activity extends Model {

    public static final int MAX_LEVEL = 9999;
    public static final int MIN_LEVEL = -9999;

    private long id;
    private String name;
    private int stressLevel;
    private int relaxLevel;


    public final long getId() {
        return id;
    }


    public final void setId(long t_id) {
        id = t_id;
    }


    /**
     * Initializes an activity with it s given parameters.
     * @param t_name           The name to set.
     * @param t_stressLevel    The stress level to set.
     * @param t_relaxLevel     The relax level to set.
     */
    public Activity(String t_name, int t_stressLevel, int t_relaxLevel) {
        setId(0);
        name = t_name;
        stressLevel = t_stressLevel;
        relaxLevel = t_relaxLevel;
    }


    /**
     * Gets the name of the activity.
     * @return  String with name value.
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the stress level of the activity.
     * @return  Integer with stress level value.
     */
    public int getStressLevel() {
        return stressLevel;
    }


    /**
     * Gets the relax level of the activity.
     * @return  Integer with relax level value.
     */
    public int getRelaxLevel() {
        return relaxLevel;
    }

    @Override
    public String toString() {
        return name;
    }
}
