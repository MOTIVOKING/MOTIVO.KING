package de.oszimt.fa45.motivoking;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.logic.ProgramLogic;
import de.oszimt.fa45.motivoking.ui.UserInterface;

/**
 * Created by boerg on 12.10.2016.
 */
public class Main {

    private DataHolder dataHolder;
    private ProgramLogic programLogic;
    private UserInterface userInterface;

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        // ...
    }
}
