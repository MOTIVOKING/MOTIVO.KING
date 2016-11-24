package de.oszimt.fa45.motivoking.ui.terminal;

import de.oszimt.fa45.motivoking.model.Day;

import java.util.List;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public class View {
    // the actual buffer for output
    private String msg;

    public View() {
        msg = "";
    }


    /**
     * Displaying the main menu
     */
    public void menu() {
        clearBuffer();

        msg += "Hauptmenü\n";
        msg += this.line();
        msg += "\n";
        msg += "1) Tage auflisten\n";
        msg += "2) Aktivitäten auflisten\n";
        msg += "3) Statistik aufrufen\n";
        msg += this.line();
        msg += "4) Tag hinzufügen\n";
        msg += "5) Aktivität hinzufügen\n";
        msg += "\n";
        msg += "0) Exit\n";

        System.out.println(msg);
    }


    /**
     * Adds a line to the console
     * @return  A String separator.
     */
    private String line() {
        return "---------------------------------------------------------\n";
    }


    /**
     * Clears the console
     */
    public void clear() {
        clearBuffer();

        for(int i = 0; i < 100; i++) {
            msg += "\n";
        }

        System.out.println(msg);
    }


    /**
     * Clears the buffer.
     */
    private void clearBuffer() {
        msg = "";
    }

    // -----------------------------------------------------------


    /**
     * Lists all days stored in the data management system
     */
    public void listDays(List<Day> t_days) {
        clearBuffer();

        msg += "Liste aller geplanten Tage\n";
        msg += this.line();
        msg += "\n";
        msg += "xxxx-xx-xx (5 Aktivitäten)\n";
        msg += "xxxx-xx-xx (2 Aktivitäten)\n";
        msg += "xxxx-xx-xx (8 Aktivitäten)\n";
        msg += "xxxx-xx-xx (1 Aktivitäten)\n";

        // TODO generate view by data

        msg += "\n";

        System.out.println(msg);
    }


    /**
     * Lists all activities of a specific day
     * @param day    Day object to use.
     */
    public void listActivities(Day day) {
        clearBuffer();

        // TODO program logic

        msg += "Aktivitäten (Datum: xxxx-xx-xx)\n";
        msg += this.line();
        msg += "\n";
        msg += "Aktivität           |  Stresslevel  |  Entspannungslevel\n";
        msg += this.line();
        msg += "Kaffee trinken      |  20           |  2000\n";
        msg += "Fernsehen           |  500          |  20\n";
        msg += "Mülleimer ausleeren |  9999         |  0\n";
        msg += "Pizza bestellen     |  200          |  -10\n";
        msg += "Computer spielen    |  2000         |  300\n";
        msg += this.line();

        System.out.println(msg);
    }
}
