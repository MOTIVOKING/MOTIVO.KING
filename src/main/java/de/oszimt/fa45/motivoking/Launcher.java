package de.oszimt.fa45.motivoking;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.data.db.JSONDataHolder;
import de.oszimt.fa45.motivoking.data.db.SqLiteDataHolder;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic1;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic2;
import de.oszimt.fa45.motivoking.ui.TerminalUserInterface;
import de.oszimt.fa45.motivoking.ui.UserInterface;
import de.oszimt.fa45.motivoking.ui.gui.GraphicalUserInterface;

import java.util.Scanner;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class Launcher {
    private Scanner m_scanner;

    // available options
    private final int DATA_OPTION = 0;
    private final int LOGIC_OPTION = 1;
    private final int UI_OPTION = 2;

    // available layers
    private DataHolder m_dataHolder;
    private ProgramLogic m_programLogic;
    private UserInterface m_userInterface;

    // stored input buffer
    private String[] m_args;


    /**
     * Gets the arguments buffer from main method
     * @param t_args
     */
    public Launcher(String[] t_args) {
        m_args = t_args;

        // arguments fallback
        m_scanner = new Scanner(System.in);
    }


    /**
     * Starts the program using terminal arguments
     */
    public void init() {
        int numArgs = m_args.length;

        // running commands in this order is required
        this.chooseDataManagement(numArgs > DATA_OPTION);
        this.chooseLogic(numArgs > LOGIC_OPTION);
        this.chooseUI(numArgs > UI_OPTION);
    }


    /**
     * Run the application
     */
    public void start() {
        System.out.println(" --- STARTE SOFTWARE ---");

        m_userInterface.activate();
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
            return m_args[index];
        }

        System.out.println( this.getQuestion(index) );
        return m_scanner.next();
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
                m_dataHolder = new SqLiteDataHolder();
                System.out.println("-> Sqlite");
                break;
            case "2":
            case "JSON":
            case "json":
            case "Json":
            case "File":
            case "file":
                m_dataHolder = new JSONDataHolder();
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
                m_programLogic = new ProgramLogic1( m_dataHolder );
                System.out.println(" -> Fachkonzept 1");
                break;
            case "2": // TODO: rename
                m_programLogic = new ProgramLogic2( m_dataHolder );
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
                // TODO new UI(m_programLogic)
                m_userInterface = new GraphicalUserInterface();
                System.out.println(" -> GUI");
                break;
            case "2":
            case "TUI":
            case "tui":
            case "Tui":
                m_userInterface = new TerminalUserInterface( m_programLogic, m_scanner );

                System.out.println(" -> TUI");
                break;
        }
    }
}
