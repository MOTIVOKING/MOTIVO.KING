package de.oszimt.fa45.motivoking.ui.gui;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.ui.UserInterface;

import java.io.Console;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class TerminalUserInterface implements UserInterface {
    ProgramLogic m_programLogic;
    DataHolder m_dataHolder;

    public TerminalUserInterface(ProgramLogic t_programLogic, DataHolder t_dataHolder) {
        m_programLogic = t_programLogic;
        m_dataHolder = t_dataHolder;
    }

    // TODO interface method
    public void run() {
        // m_programLogic.init(m_dataHolder);
    }
}
