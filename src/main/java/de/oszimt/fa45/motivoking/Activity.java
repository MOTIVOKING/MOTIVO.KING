package de.oszimt.fa45.motivoking;

/**
 * Created by boerg on 13.10.2016.
 */
public class Activity {
    private String name;
    private int stressLevel;
    private int relaxLevel;

    public Activity(String name, int stressLevel, int relaxLevel) {
        this.name = name;
        this.stressLevel = stressLevel;
        this.relaxLevel = relaxLevel;
    }

    public String getName() {
        return name;
    }

    public int getStressLevel() {
        return stressLevel;
    }

    public int getRelaxLevel() {
        return relaxLevel;
    }
}
