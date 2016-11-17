package de.oszimt.fa45.motivoking.model;

/**
 * Created by boerg on 13.10.2016.
 */
public class Activity implements Model {
    private int m_id;
    private String name;
    private int stressLevel;
    private int relaxLevel;

    public Activity(String name, int stressLevel, int relaxLevel) {
        this.m_id = 0;
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

    public int getId() {
        return m_id;
    }

    public void setId(int t_id) {
        m_id = t_id;
    }
}
