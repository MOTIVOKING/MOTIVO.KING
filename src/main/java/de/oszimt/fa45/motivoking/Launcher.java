package de.oszimt.fa45.motivoking;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.data.JSONDataHolder;
import de.oszimt.fa45.motivoking.data.SqLiteDataHolder;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic1;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic2;
import de.oszimt.fa45.motivoking.ui.TerminalUserInterface;
import de.oszimt.fa45.motivoking.ui.UserInterface;
import de.oszimt.fa45.motivoking.ui.GraphicalUserInterface;
import javafx.application.Application;

import java.util.Scanner;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class Launcher {
    private Scanner mScanner;

    // available options
    private final int DATA_OPTION = 0;
    private final int LOGIC_OPTION = 1;
    private final int UI_OPTION = 2;

    // available layers
    private DataHolder mDataHolder;
    private ProgramLogic mProgramLogic;
    private UserInterface mUserInterface;

    // stored input buffer
    private String[] mArgs;


    /**
     * Gets the arguments buffer from main method
     * @param args
     */
    public Launcher(String[] args) {
        mArgs = args;

        // arguments fallback
        mScanner = new Scanner(System.in);
    }


    /**
     * Run the application
     */
    public void start() {
        int numArgs = mArgs.length;

        // running commands in this order is required
        this.chooseDataManagement(numArgs > DATA_OPTION);
        this.chooseLogic(numArgs > LOGIC_OPTION);
        this.chooseUI(numArgs > UI_OPTION);
    }


    /**
     * Get the question requested by the given index
     * @param index
     * @return
     */
    private String getQuestion(int index) {
        String question;

        switch(index) {
            case UI_OPTION:
                question =  "Wählen Sie eine der folgenden Anwendungsoberflächen:\n";
                question += "1) GUI - Grafische Oberfläche\n";
                question += "2) TUI - Konsolenanwendung\n";
                break;
            case LOGIC_OPTION:
                question =  "Wählen Sie eine der folgenden Fachkonzepte:\n";
                question += "1) Fachkonzept 1\n";
                question += "2) Fachkonzept 2\n";
                break;
            case DATA_OPTION:
                question =  "Wählen Sie eine der folgenden Datenhaltungsmöglichkeiten:\n";
                question += "1) SqLite\n";
                question += "2) JSON\n";
                break;
            default:
                question = "Error loading question of index " + index + "\n";
                break;
        }

        question += "\n";

        return question;
    }

    /**
     * Get option for the required component
     * @param index
     * @param fromArguments
     * @return
     */
    private String getOption(int index, boolean fromArguments) {

        if( fromArguments ) {
            return mArgs[index];
        }

        System.out.println( this.getQuestion(index) );
        return mScanner.nextLine();
    }


    /**
     * Initialize the data management
     * @param hasArgs
     */
    private void chooseDataManagement(boolean hasArgs) {
        String s = this.getOption(DATA_OPTION, hasArgs);

        switch (s) {
            default:
            case "1":
            case "DB":
            case "db":
            case "Db":
            case "Database":
            case "database":
            case "DataBase":
                mDataHolder = new SqLiteDataHolder();
                System.out.println("-> Sqlite");
                break;
            case "2":
            case "JSON":
            case "json":
            case "Json":
            case "File":
            case "file":
                mDataHolder = new JSONDataHolder();
                System.out.println("-> JSON");
                break;
        }
    }


    /**
     * Initialize the logic
     * @param hasArgs
     */
    private void chooseLogic(boolean hasArgs) {
        String s = this.getOption(LOGIC_OPTION, hasArgs);

        switch (s) {
            default:
            case "1":  // TODO: rename
                mProgramLogic = new ProgramLogic1(mDataHolder);
                System.out.println(" -> Fachkonzept 1");
                break;
            case "2": // TODO: rename
                mProgramLogic = new ProgramLogic2(mDataHolder);
                System.out.println(" -> Fachkonzept 2");
                break;
        }
    }


    /**
     * Initialize the UI
     * @param hasArgs
     */
    private void chooseUI(boolean hasArgs) {
        String s = this.getOption(UI_OPTION, hasArgs);

        switch (s) {
            default:
            case "1":
            case "GUI":
            case "gui":
            case "Gui":
//                mUserInterface = new GraphicalUserInterface(mProgramLogic);
//                GraphicalUserInterface graphicalUserInterface = new GraphicalUserInterface();
                Application.launch(GraphicalUserInterface.class);
                GraphicalUserInterface.getInstance().setProgramLogic(mProgramLogic);
//                GraphicalUserInterface.getInstance().initUi();
                System.out.println(" -> GUI");
                break;
            case "2":
            case "TUI":
            case "tui":
            case "Tui":
                mUserInterface = new TerminalUserInterface(mProgramLogic, mScanner);
                System.out.println(" -> TUI");
                break;
        }
    }
}
