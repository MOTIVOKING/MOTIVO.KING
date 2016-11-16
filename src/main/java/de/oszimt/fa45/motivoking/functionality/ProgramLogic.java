package de.oszimt.fa45.motivoking.functionality;

import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by boerg on 12.10.2016.
 */
public interface ProgramLogic {
    void create();
    void read();
    void update();
    void delete();

    List<Day> getDays();
}
