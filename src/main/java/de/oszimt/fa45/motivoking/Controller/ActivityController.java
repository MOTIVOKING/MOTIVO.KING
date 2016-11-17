package de.oszimt.fa45.motivoking.Controller;

import de.oszimt.fa45.motivoking.model.Activity;

/**
 * Created by RedCyberSamurai on 17.11.2016.
 */
public class ActivityController implements Controller {

    @Override
    public Activity create() {
        int id = 1;
        Activity activity = new Activity("Some Activity", 100, 999);

        return activity;
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
