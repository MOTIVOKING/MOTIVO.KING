package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.ui.fxml.App;
import javafx.application.Application;
import javafx.event.ActionEvent;

/**
 * Created by boerg on 13.10.2016.
 */
public class GraphicalUserInterface implements UserInterface {

    private static ProgramLogic mProgramLogic;

    /**
     * GUI constructor, launching the window app.
     * @param tProgramLogic    Program logic to work with
     */
    public GraphicalUserInterface(ProgramLogic tProgramLogic) {
        System.out.println("::GraphicalUserInterface");

        mProgramLogic = tProgramLogic;
        Application.launch(App.class);
    }


    /**
     * Getter for program logic
     * (static is bad code style here though... fml javafx...)
     * @return
     */
    public static ProgramLogic getProgramLogic() {
        return mProgramLogic;
    }


}
