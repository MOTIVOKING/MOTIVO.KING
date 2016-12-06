package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.model.DayActivity;
import de.oszimt.fa45.motivoking.ui.terminal.Input;
import de.oszimt.fa45.motivoking.ui.terminal.View;

import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class TerminalUserInterface implements UserInterface {
    private boolean mIsRunning = true;

    private View mView;

    private ProgramLogic mProgramLogic;


    /**
     * Initialize the terminal with its needed components
     * @param t_programLogic    The program logic to use.
     */
    public TerminalUserInterface(ProgramLogic t_programLogic) {
        // initializing program logic
        mProgramLogic = t_programLogic;
        // initializing the view class
        mView = new View();

        mView.clear();
        while(mIsRunning) {
            Error.print();
            mView.menu();

            String input = Input.get();

            this.runPage(input);
        }

        Input.close();
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
                    id = Input.getLong();
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
                String date = Input.get();

                if(!Error.isset()) {

                    mProgramLogic.createDay(date);
                }

                this.runPage("days");
                break;
            case "5": // --- creating the activity ---
            case "create activity":
                mView.printDaysTable(days, dayActivities);
                System.out.println( mView.chooseDay() );
                id = Input.getLong();

                if( days.stream().anyMatch(d -> d.getId() ==  id) ) {
                    System.out.println( mView.chooseActivityName() );
                    String name = Input.get();

                    System.out.println( mView.chooseStressLevel() );
                    int stressLevel = Input.getInt();

                    System.out.println( mView.chooseRelaxLevel() );
                    int relaxLevel = Input.getInt();

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
}
