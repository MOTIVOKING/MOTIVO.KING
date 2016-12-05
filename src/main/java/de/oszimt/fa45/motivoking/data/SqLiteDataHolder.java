package de.oszimt.fa45.motivoking.data;

import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.data.sqlite.QueryBuilder;
import de.oszimt.fa45.motivoking.data.type.SqLiteData;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * Created by boerg on 13.10.2016.
 */
public class SqLiteDataHolder implements DataHolder {
    private int timeout = 1; // in seconds
    private Connection connection;
    private Statement statement;
    private final String DB_NAME = "jdbc:sqlite:testfile.db";

    private QueryBuilder qb;
    private boolean connectedSuccessfully = false;


    public SqLiteDataHolder() {

        qb = new QueryBuilder();
    }


    public void initializeTable(boolean dropTables) {
        SqLiteData.setTableQuery(dropTables);
        connect();

        runQuery(SqLiteData.getTableQuery());

        close();
    }


    private void runQuery(String query) {

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            Error.set("Error: SQL statement");
            Error.set(e.getMessage());
        }
    }


    private SqLiteData read(String query) {
        SqLiteData data = null;
        connect();

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);


            // TODO

        } catch (SQLException e) {
            Error.set("Could not read the query correctly.");
        }

        close();
        return data;
    }


    private long write(String query, boolean isInsert) {
        long id = 0;
        connect();

        if(isInsert) {
            try {
                // TODO data insert / delete query

                statement.executeUpdate(query);
                ResultSet rs = statement.getResultSet();


                // getting the generated id of the entity
                rs = statement.getGeneratedKeys();
                if(rs.next()) {
                    id = rs.getLong(1);
                }

                connection.commit();
            } catch (SQLException e) {
                Error.set("Data could not be inserted to SQL DB.");
                System.out.println( e.getMessage() );
            }
        }
        close();

        return id;
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

        if(Error.isset() || !connectedSuccessfully) {
            Error.print();
            System.exit(1);
        }
    }


    private void close() {

        try {
            connection.close();
        } catch (SQLException e) {
            Error.set("Error closing SqLite.");
            System.out.println( e.getMessage() );
        }

        if(Error.isset()) {
            Error.print();
            System.exit(1);
        }
    }


    @Override
    public Day findDayById(long dayId) {
        if(dayId < 1) {
            Error.set("Day ID " + dayId + " not found.");
            return null;
        }

        qb.select("id").from("days").where("id", "=", String.valueOf(dayId));

        String query = qb.getQuery();
        return this.read(query).getDays().stream().findFirst().orElse(null);
    }

    @Override
    public List<Day> findAllDays() {
        qb.select("*").from("days");

        String query = qb.getQuery();
        return this.read(query).getDays();
    }

    @Override
    public List<Activity> findActivitiesByDayId(long dayId) {

        if(dayId < 1) {
            Error.set("Day ID " + dayId + " not found.");
            return null;
        }

        qb.select("a.*").from("activities as a, dayActivities as dA").where("dA.dayId", "=", String.valueOf(dayId));

        String query = qb.getQuery();
        return this.read(query).getActivities();
    }

    @Override
    public List<DayActivity> findAllDayActivities() {
        qb.select("*").from("activities");

        String query = qb.getQuery();
        return this.read(query).getDayActivities();
    }

    @Override
    public List<Activity> findAllActivities() {
        return null;
    }

    @Override
    public void addDay(Day day) {
        Map<String, String> map = null;
        try {
            map = getFields(day);
        } catch (IllegalAccessException e) {
            Error.set("Cannot get fields of the specified model.");
            return;
        }
        qb.insertInto("days").values(map, true);

        String query = qb.getQuery();
        this.write(query, true);
    }

    @Override
    public void addActivity(long dayId, Activity activity) {
        String query;
        Map<String, String> map;
        Field[] fields;

        // check if day exists
        qb.select("id").from("days").where("id", "=", String.valueOf(dayId));

        query = qb.getQuery();
        SqLiteData tmp = this.read(query);

        if(tmp.getDays().size() < 1) {
            Error.set("Day not found!");
            return;
        }

        // add activity
        try {
            map = getFields(activity);
        } catch (IllegalAccessException e) {
            Error.set("Cannot get fields of the specified model.");
            return;
        }
        qb.insertInto("activity").values(map, true);

        query = qb.getQuery();
        long activityId = this.write(query, true);

        // add to merge table
        DayActivity dA = new DayActivity(dayId, activityId);
        try {
            map = getFields(dA);
        } catch (IllegalAccessException e) {
            Error.set("Cannot get fields of the specified model.");
            return;
        }

        qb.insertInto("dayActivities").values(map, true);

        query = qb.getQuery();
        this.write(query, true);
    }

    private <T> Map<String, String> getFields(T entity) throws IllegalAccessException {
        Map<String, String> map = new TreeMap<>();
        Field[] fields = entity.getClass().getFields();

        for(Field f : fields) {
            map.put(f.getName(), (String) f.get(entity));
        }

        return map;
    }
}
