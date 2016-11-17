package de.oszimt.fa45.motivoking.data;

import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.data.DataHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by boerg on 13.10.2016.
 */
public class SqLiteDataHolder implements DataHolder {
    private static Connection connection;
    private static final String DB_PATH = System.getProperty("user.home") + "/" + "motivo.king.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }

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

    public SqLiteDataHolder() {

    }

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
    public void addDay(Day day) {

    }

    @Override
    public void addActivity(long dayId, Activity activity) {

    }
}
