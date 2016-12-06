package de.oszimt.fa45.motivoking.ui.terminal;

import de.oszimt.fa45.motivoking.Error;

import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Dante on 05.12.2016.
 */
public class Input {

    private static Scanner mScanner;

    public static void initialize() {
        // initializing input reader
        mScanner = new Scanner(System.in);
        mScanner.useLocale(Locale.GERMAN);
    }


    public static void close() {
        mScanner.close();
    }


    public static boolean isset() {
        return mScanner.hasNextLine();
    }

    public static boolean issetInt() {
        return mScanner.hasNextInt();
    }

    public static boolean issetLong() {
        return mScanner.hasNextLong();
    }


    public static String get() {
        String s = "";

        if(mScanner.hasNextLine()) {
            s = mScanner.nextLine();
        } else {
            Error.set("The given input exceeded the range of a String.");
        }

        return s;
    }


    public static int getInt() {
        int n = 0;

        if(mScanner.hasNextInt()) {
            n = mScanner.nextInt();
            mScanner.nextLine(); // wtf java!!!!!!!
        } else {
            Error.set("The given input exceeded the range of an Integer.");
        }

        return n;
    }


    public static long getLong() {
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
