package de.oszimt.fa45.motivoking.data;

import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.data.type.SqLiteData;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class SqLiteDataHolder implements DataHolder {
    private int timeout = 1; // in seconds
    private Connection connection;
    private final String DB_NAME = "jdbc:sqlite:testfile.db";

    private boolean connectedSuccessfully = false;

    private SqLiteData mData;

    private List<Day> mDays;
    private List<Activity> mActivities;


    public SqLiteDataHolder() {
        mData = this.read();

        if(connectedSuccessfully) {
            System.out.println("SqLite connected successfully!");
        }

        /*

        // list of days
        mDays = mData.getDays();
        // list of activities
        mActivities = mData.getActivities();

        */
    }


    private SqLiteData read() {
        SqLiteData sqlData = null;
        connect();

        // TODO: create table only once


        close();
        return null;
    }


    private void write() {
        connect();

        // TODO: save data into database and base model (insertion/deletion)

        close();
    }


    private void connect() {

        connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_NAME);

            // checking connection once for the startup
            if(connectedSuccessfully || connection.isValid(timeout)) {
                connectedSuccessfully = true;
            }
        } catch (ClassNotFoundException e) {
            Error.set("Error resolving class name for SqLite.");
            Error.set( e.getMessage() );
        } catch (SQLException e) {
            Error.set("Error running SqLite.");
            Error.set( e.getMessage() );
        }

        if(Error.isset()) {
            Error.print();
            System.exit(1);
        }
    }


    private void close() {

        try {
            connection.close();
        } catch (SQLException e) {
            Error.set("Error closing SqLite.");
            Error.set( e.getMessage() );
        }

        if(Error.isset()) {
            Error.print();
            System.exit(1);
        }
    }









    // private static final String DB_PATH = System.getProperty("user.home") + "/" + "motivo.king.db";

    /*
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }
    */


    /*
    private void initDBConnection() {
        try {
            if (connection != null)
                return;
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (!connection.isClosed())
                System.out.println("...Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (!connection.isClosed() && connection != null) {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to Database closed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    */

    @Override
    public Day findDayById(long dayId) {
        return null;
    }

    @Override
    public List<Day> findAllDays() {
        return null;
    }

    @Override
    public List<Activity> findActivitiesByDayId(long dayId) {
        return null;
    }

    @Override
    public List<DayActivity> findAllActivities() {
        return null;
    }

    @Override
    public void addDay(Day day) {

    }

    @Override
    public void addActivity(long dayId, Activity activity) {

    }
}
