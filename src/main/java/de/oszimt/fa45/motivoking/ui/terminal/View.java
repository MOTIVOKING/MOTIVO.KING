package de.oszimt.fa45.motivoking.ui.terminal;

import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public class View {
    // the actual buffer for output
    private String msg;
    private static final int WIDTH = 78;

    private DateFormat dateFormat;

    public View() {

        msg = "";
        dateFormat = new SimpleDateFormat("YYYY-mm-dd", Locale.GERMANY);
    }


    /**
     * Displaying the main menu
     */
    public void menu() {
        clearBuffer();

        msg += "Hauptmenü\n";
        msg += this.line("-");
        msg += "\n";
        msg += "1) Tage auflisten\n";
        msg += "2) Aktivitäten auflisten\n";
        msg += "3) Statistik aufrufen\n";
        msg += this.line("-");
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
    public static String line(String t_character) {
        String s = "";
        for(int i = 0; i < WIDTH; i++) {
            s += t_character;
        }

        s += "\n";
        return s;
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
        msg += this.line("-");
        msg += "\n";

        System.out.println(msg);

        printDaysTable(t_days);
    }


    /**
     * Lists all activities of a specific day
     * @param day    Day object to use.
     */
    public void listActivities(Day day, List<Activity> activities) {
        if(day == null) {
            Error.set("Ungültige Eingabe!");
            return;
        }
        clearBuffer();

        msg += "Aktivitäten vom " + dateFormat.format(day.getDate()) + "\n";
        msg += this.line("-");

        System.out.println(msg);

        printActivitiesTable(activities);
    }

    public void printDaysTable(List<Day> t_days) {
        String tableSetup = "| %-9d | %-50s |\n";

        System.out.format("+-----------+----------------------------------------------------+\n");
        System.out.format("+ ID        + Datum                                              +\n");
        System.out.format("+-----------+----------------------------------------------------+\n");

        if(t_days.size() > 0) {
            for(Day d : t_days) {
                Date date = d.getDate();
                String sDate = dateFormat.format(date);

                int numActivities = d.getActivities().size();

                // msg += sDate + " (" + numActivities + " Aktivitäten)\n";

                System.out.format(tableSetup, d.getId(), sDate + " (" + numActivities + " Aktivitäten)");
            }

            System.out.format("+-----------+----------------------------------------------------+\n\n");
        } else {
            msg = "\n --- Die Liste ist leer --- \n\n";

            System.out.println(msg);
        }

    }


    public void printActivitiesTable(List<Activity> activities) {
        String tableSetup = "| %-40s | %-11d | %-17d |\n";

        System.out.format("+------------------------------------------+-------------+-------------------+\n");
        System.out.format("+ Aktivität                                | Stresslevel | Entspannungslevel +\n");
        System.out.format("+------------------------------------------+-------------+-------------------+\n");

        if(activities.size() > 0) {
            for(Activity a : activities) {
                System.out.format(tableSetup, a.getName(), a.getStressLevel(), a.getRelaxLevel());
            }

            System.out.format("+------------------------------------------+-------------+-------------------+\n\n");
        } else {
            msg = "\n --- Keine Aktivität vorhanden --- \n\n";

            System.out.println(msg);
        }

    }


    public String chooseDay() {
        return "Wähle eine Tag aus [ID]:\n";
    }


    public String createDay() {
        return "Gib das Datum im folgendem Format ein: YYYY-mm-dd\n\n";
    }


    public String chooseActivityName() {
        return "Gib den Namen deiner Aktivität an:\n\n";
    }


    public String chooseStressLevel() {
        return "Gib an, welchen Stresslevel deine Aktivität besitzt (-9999 bis 9999):\n\n";
    }


    public String chooseRelaxLevel() {
        return "Gib an, welchen Entspannungslevel deine Aktivität besitzt (-9999 bis 9999):\n\n";
    }
}
