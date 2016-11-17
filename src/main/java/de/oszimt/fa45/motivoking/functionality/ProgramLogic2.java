package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.Controller.ActivityController;
import de.oszimt.fa45.motivoking.Controller.Controller;
import de.oszimt.fa45.motivoking.Controller.DayController;
import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class ProgramLogic2 implements ProgramLogic {
    private DataHolder m_dataHolder;

    private Controller m_dayController;
    private Controller m_activityController;

    public ProgramLogic2(DataHolder t_dataHolder) {
        m_dataHolder = t_dataHolder;

        m_dayController = new DayController();
        m_activityController = new ActivityController();
    }

    public void createDay() {
        Day day = m_dayController.create();
        m_dataHolder.addDay(day);
    }


    public void createActivity(int id) {
        // TODO assign activity to a selected day

        Activity activity = m_activityController.create();
        m_dataHolder.addActivity(activity.getId(), activity);
    }


    public Day getDay(int id) {
        return m_dataHolder.findDayById(id);
    }


    public List<Day> getDays() {
        return m_dataHolder.findAllDays();
    }


    public List<Activity> getActivities(int id) {
        return m_dataHolder.findActivitiesByDayId(id);
    }
}
