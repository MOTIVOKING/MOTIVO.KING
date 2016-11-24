package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.ui.terminal.View;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class TerminalUserInterface implements UserInterface {
    private boolean mIsRunning = true;

    private Scanner mScanner;
    private View mView;

    private ProgramLogic mProgramLogic;


    /**
     * Initialize the terminal with its needed components
     * @param t_programLogic    The program logic to use.
     * @param t_scanner         The input reader.
     */
    public TerminalUserInterface(ProgramLogic t_programLogic, Scanner t_scanner) {
        // initializing program logic
        mProgramLogic = t_programLogic;
        // initializing input reader
        mScanner = t_scanner;
        // initializing the view class
        mView = new View();

        mView.clear();
        while(mIsRunning) {

            mView.menu();

            String input = mScanner.next();

            mView.clear();
            this.runPage(input);
        }

        mScanner.close();
        System.out.println("Programm beendet.");
    }


    /**
     * Routing to a specific page.
     * @param input    String for choosing the page.
     */
    private void runPage(String input) {
        int id;
        Day day;

        switch(input) {
            case "0":
            case "exit":
                this.mIsRunning = false;
                break;
            case "1": // read Day
            case "days":
                List<Day> days = mProgramLogic.getDays();

                mView.listDays(days);
                break;
            case "2": // read Activity
            case "activities":
                day = new Day( new Date() );

                mView.listActivities(day);
                break;
            case "3": // TODO stats (?)
                System.out.println("TODO stats\n\n");
                break;
            case "4": // create Day
            case "create day":
                System.out.println("Type in your date in the following format: YYYY-mm-dd\n\n");
                String date = mScanner.next();
                System.out.println(date);
                mProgramLogic.createDay(date);

                this.runPage("days");
                break;
            case "5": // create Activity
            case "create activity":
                System.out.println("Type in the id of the day you want to add the activity:\n\n");
                id = mScanner.nextInt();

                mProgramLogic.createActivity(id);
                this.runPage("days");
                break;
            default:
                System.out.println("Command not recognized\n\n");
                break;
        }
    }
}
