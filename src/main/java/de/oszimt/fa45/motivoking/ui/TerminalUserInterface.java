package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.model.DayActivity;
import de.oszimt.fa45.motivoking.ui.terminal.View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
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
        mScanner.useLocale(Locale.GERMAN);
        // initializing the view class
        mView = new View();

        mView.clear();
        while(mIsRunning) {
            Error.print();
            mView.menu();

            String input = getString();

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
        long id;
        Day day;
        Activity activity;
        List<Day> days = mProgramLogic.getDays();
        List<DayActivity> dayActivities = mProgramLogic.getDayActivities();

        mView.clear();
        switch(input) {
            case "0":
            case "exit":
                this.mIsRunning = false;
                break;
            case "1": // --- listing the days ---
            case "days":
                mView.listDays(days, dayActivities);
                break;
            case "2": // --- listing the activities ---
            case "activities":
                mView.printDaysTable(days, dayActivities);

                if(days.size() > 0) {
                    System.out.println( mView.chooseDay() );
                    id = getLong();
                    day = days.stream().filter(d -> d.getId() == id).findFirst().orElse(null);

                    mView.clear();
                    if(!Error.isset()) {
                        mView.listActivities(day, mProgramLogic.getActivities(id));
                    }
                }

                break;
            case "3": // TODO stats (?)
                System.out.println("TODO stats\n\n");
                break;
            case "4": // --- creating the day ---
            case "create day":
                System.out.println( mView.createDay() );
                String date = getString();

                if(!Error.isset()) {

                    mProgramLogic.createDay(date);
                }

                this.runPage("days");
                break;
            case "5": // --- creating the activity ---
            case "create activity":
                mView.printDaysTable(days, dayActivities);
                System.out.println( mView.chooseDay() );
                id = getLong();

                if( days.stream().anyMatch(d -> d.getId() ==  id) ) {
                    System.out.println( mView.chooseActivityName() );
                    String name = getString();

                    System.out.println( mView.chooseStressLevel() );
                    int stressLevel = getInt();

                    System.out.println( mView.chooseRelaxLevel() );
                    int relaxLevel = getInt();

                    if(!Error.isset()) {
                        activity = new Activity(name, stressLevel, relaxLevel);
                        mProgramLogic.createActivity(id, activity);
                    }
                } else {
                    Error.set("Error: Day ID not found!");
                }

                this.runPage("days");
                break;
            default: // --- input not recognized ---
                Error.set("Command not recognized");
                break;
        }

    }


    private String getString() {
        String s = "";

        if(mScanner.hasNextLine()) {
            s = mScanner.nextLine();
        } else {
            Error.set("The given input exceeded the range of a String.");
        }

        return s;
    }


    private int getInt() {
        int n = 0;

        if(mScanner.hasNextInt()) {
            n = mScanner.nextInt();
            mScanner.nextLine(); // wtf java!!!!!!!
        } else {
            Error.set("The given input exceeded the range of an Integer.");
        }

        return n;
    }


    private long getLong() {
        long l = 0;

        if(mScanner.hasNextLong()) {
            l = mScanner.nextLong();
            mScanner.nextLine(); // wtf java!!!!!!!
        } else {
            Error.set("The given input exceeded the range of a Long.");
        }

        return l;
    }
}
