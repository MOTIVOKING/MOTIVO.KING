package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.model.Day;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by boerg on 16.11.2016.
 */
public class StubLogic implements ProgramLogic {
    @Override
    public void create() {

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

    @Override
    public List<Day> getDays() {
        List<Day> days = new ArrayList<>();
        days.add(new Day(new Date(2016, 05, 11)));
        days.add(new Day(new Date(2016, 11, 16)));
        return days;
    }
}
