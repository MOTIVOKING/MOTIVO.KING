package de.oszimt.fa45.motivoking.Controller;

import de.oszimt.fa45.motivoking.model.Day;

import java.util.Date;

/**
 * Created by RedCyberSamurai on 17.11.2016.
 */
public class DayController implements Controller {

    @Override
    public Day create() {
        Date date = new Date();
        Day day = new Day(date);

        return day;
    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
