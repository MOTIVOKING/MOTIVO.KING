package de.oszimt.fa45.motivoking;

import de.oszimt.fa45.motivoking.ui.terminal.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public class Error {

    private static List<String> errors = new ArrayList<>();


    /**
     * Adds an error message to the global error list.
     * @param s The error message to add.
     */
    public static void set(String s) {
        errors.add(s);
    }


    /**
     * Provides the list of errors. Deletes the global error list.
     * @return  A list of errors set by the ::set() method.
     */
    public static List<String> get() {

        List<String> e = new ArrayList<>(errors);
        errors.clear();

        return e;
    }


    /**
     * Prints errors to the terminal view.
     * @param view The used view to display messages.
     */
    public static void print(View view) {
        List<String> errors = get();

        if(errors.size() > 0) {
            String errorTitle = "# %-74s #\n";
            String errorMessage = "# > %-72s #\n";

            System.out.print( view.line("#") );
            System.out.format(errorTitle, "Errors:");

            for(String e : errors) {
                System.out.format(errorMessage, e);
            }

            System.out.print( view.line("#") + "\n\n" );
        }
    }
}
