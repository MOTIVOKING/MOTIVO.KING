package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.model.Day;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class TerminalUserInterface implements UserInterface {
    private Scanner m_scanner;

    private ProgramLogic m_programLogic;

    private boolean m_isRunning = true;


    /**
     * Initialize the terminal with its needed components
     * @param t_programLogic
     * @param t_scanner
     */
    public TerminalUserInterface(ProgramLogic t_programLogic, Scanner t_scanner) {
        m_programLogic = t_programLogic;
        m_scanner = t_scanner;


        this.clear();
        while(m_isRunning) {

            this.menu();
        }

        m_scanner.close();
        System.out.println("Programm beendet.");
    }


    /**
     * Displaying the main menu
     */
    protected void menu() {
        String msg;
        msg = "Hauptmenü\n";
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

        String input = m_scanner.next();

        this.clear();
        this.runPage(input);
    }

    private void runPage(String input) {
        int id;
        Day day;

        switch(input) {
            case "0":
            case "exit":

                this.m_isRunning = false;
                break;
            case "1": // read Day
            case "days":

                List<Day> days = m_programLogic.getDays();
                this.listDays(days);
                break;
            case "2": // read Activity
            case "activities":
                day = new Day( new Date() );

                this.listActivities(day);
                break;
            case "3": // TODO stats (?)

                System.out.println("TODO stats\n\n");
                break;
            case "4": // create Day
            case "create day":

                System.out.println("TODO day\n\n");
                m_programLogic.createDay();

                this.runPage("days");
                break;
            case "5": // create Activity
            case "create activity":

                // TODO get input to define id
                id = 1;

                System.out.println("TODO activity\n\n");
                m_programLogic.createActivity(id);
                break;
            default:
                System.out.println("Command not recognized\n\n");
                break;
        }
    }


    /**
     * Lists all days stored in the data management system
     */
    protected void listDays(List<Day> t_days) {

        String msg;
        msg = "Liste aller geplanten Tage\n";
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
     * @param day
     */
    protected void listActivities(Day day) {

        // TODO program logic

        String msg;
        msg = "Aktivitäten (Datum: xxxx-xx-xx)\n";
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


    /**
     * Adds a line to the console
     * @return
     */
    private String line() {
        return "---------------------------------------------------------\n";
    }


    /**
     * Clears the console
     */
    private void clear() {
        String s = "";

        for(int i = 0; i < 100; i++) {
            s += "\n";
        }

        System.out.println(s);
    }
}
