package de.oszimt.fa45.motivoking;

import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.data.db.SqLiteDataHolder;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.ui.UserInterface;
import de.oszimt.fa45.motivoking.ui.gui.GraphicalUserInterface;

import java.util.Scanner;

/**
 * Created by boerg on 12.10.2016.
 * start parameters 1. ui, 2. functionality, 3. dataholder
 */
public class Main {

    private DataHolder dataHolder;
    private ProgramLogic programLogic;
    private UserInterface userInterface;

    private Scanner scanner;

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        Main main = new Main(args);
        // ...
    }

    private Main(String[] args) {
        if (args.length > 0) {
            checkArgOne(args[0]);
            if (args.length > 1) {
                checkArgTwo(args[2]);
                if (args.length > 2) {
                    checkArgThree(args[2]);
                } else {
                    askDataholder();
                }
            } else {
                askLogic();
            }
        } else {
            askUi();
        }
        System.out.println(" --- STARTE SOFTWARE ---");
        // TODO: launch all components
    }

    private void askUi() {
        String answer = ask("Möchten Sie\n   1) die grafische Oberfläche verwenden (default)" +
                " oder \n   2) d Textoberfläche\nnutzen?");
        checkArgOne(answer);
        askLogic();
    }

    private void askLogic(){
        String answer = ask("Möchten Sie\n   1) das Fachkonzept 1 (default)" +
                " oder \n   2) das Fachkonzept 2\nnutzen?");
        checkArgTwo(answer);
        askDataholder();
    }

    private void askDataholder() {
        String answer = ask("Möchten Sie\n   1) die Datenbank (default)" +
                " oder \n   2) die JSON-Datei 2\nnutzen?");
        checkArgThree(answer);
    }

    private String ask(String question) {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.print(question + ":");
        String input = scanner.next();
        return input;
    }

    private void checkArgOne(String s) {
        switch (s) {
            case "2":
            case "TUI":
            case "tui":
            case "Tui":
//                  construct TUI ...
                System.out.println(" -> TUI");
                break;
            case "1":
            case "GUI":
            case "gui":
            case "Gui":
            default:
                userInterface = new GraphicalUserInterface();
                System.out.println(" -> GUI");
        }
    }

    private void checkArgTwo(String s) {
        switch (s) {
            case "2": // TODO: rename
                // construct logic 2
                System.out.println(" -> Logik 2");
                break;
            case "1":  // TODO: rename
            default:
                // construct logic 1
                System.out.println(" ->Logik 1");
        }
    }

    private void checkArgThree(String s) {
        switch (s) {
            case "2":
            case "JSON":
            case "json":
            case "Json":
            case "File":
            case "file":
                // construct json dataholder
                System.out.println(" -> JSON-File Data");
                break;
            case "1":
            case "DB":
            case "db":
            case "Db":
            case "Database":
            case "database":
            case "DataBase":
            default:
                dataHolder = new SqLiteDataHolder();
        }
    }
}
