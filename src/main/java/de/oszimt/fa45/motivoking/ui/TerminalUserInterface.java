package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class TerminalUserInterface implements UserInterface {
    ProgramLogic m_programLogic;

    public TerminalUserInterface(ProgramLogic t_programLogic) {
        m_programLogic = t_programLogic;
    }

    // TODO interface method
    public void run() {
        // m_programLogic.run();
    }
}
