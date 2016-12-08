package de.oszimt.fa45.motivoking.data;

import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.data.sqlite.QueryBuilder;
import de.oszimt.fa45.motivoking.data.type.SqLiteData;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by boerg on 13.10.2016.
 */
public class SqLiteDataHolder implements DataHolder {
    private int timeout = 1; // in seconds
    private Connection connection;
    private final String DB_NAME = "jdbc:sqlite:testfile.db";

    private DateFormat dateFormat;

    private QueryBuilder qb;
    private boolean connectedSuccessfully = false;


    public SqLiteDataHolder() {

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
        qb = new QueryBuilder();
    }


    public void initializeTable(boolean dropTables) {
        SqLiteData.setTableQuery(dropTables);
        String query = SqLiteData.getTableQuery();

        connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            Error.set("Error: running SQL statement");
            Error.set(e.getMessage());
        }
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

        qb.select("*").from("days").where("id", "=", String.valueOf(dayId));
        String query = qb.getQuery();

        Day d = null;

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                d = new Day();
                d.setDate( dateFormat.parse(rs.getString("date")) );
                d.setId( rs.getLong("id") );
                break;
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Could not read the query correctly. [day by id]");
            Error.set(e.getMessage());
        } catch (ParseException e) {
            Error.set("Error parsing date [day by id]");
        }
        close();

        return d;
    }

    @Override
    public List<Day> findAllDays() {

        qb.select("*").from("days");
        String query = qb.getQuery();

        List<Day> days = new ArrayList<>();

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            // fetching data
            while(rs.next()) {
                Day d = new Day();

                String s = rs.getString("date");
                d.setDate( dateFormat.parse(rs.getString("date")) );
                d.setId( rs.getLong("id") );

                days.add(d);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Could not read the query correctly. [all days]");
            e.printStackTrace();
        } catch (ParseException e) {
            Error.set("Error parsing date.");
        }
        close();

        return days;
    }

    @Override
    public List<Activity> findActivitiesByDayId(long dayId) {

        if(dayId < 1) {
            Error.set("Day ID " + dayId + " not found.");
            return null;
        }

        qb.select("a.*")
                .from("activities as a, dayActivities as dA")
                .where("dA.dayId", "=", String.valueOf(dayId))
                .groupBy("a.id");
        String query = qb.getQuery();

        List<Activity> activities = new ArrayList<>();

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                Activity a = new Activity(
                        rs.getString("name"),
                        rs.getInt("stressLevel"),
                        rs.getInt("relaxLevel")
                );

                a.setId(rs.getLong("id"));

                activities.add(a);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Could not read the query correctly. [find act by id]");
            Error.set(e.getMessage());
        }
        close();

        return activities;
    }

    @Override
    public List<DayActivity> findAllDayActivities() {

        qb.select("*").from("dayActivities");
        String query = qb.getQuery();

        List<DayActivity> dayActivities = new ArrayList<>();

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {

                DayActivity dA = new DayActivity(
                        rs.getLong("dayId"),
                        rs.getLong("activityId")
                );
                dA.setId( rs.getLong("id") );

                dayActivities.add(dA);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Could not read the query correctly. [find all act]");
            Error.set(e.getMessage());
        }
        close();

        return dayActivities;
    }

    @Override
    public Activity findActivityById(long id) {

        qb.select("*").from("activities").where("id", "=", String.valueOf(id));
        String query = qb.getQuery();

        Activity activity = null;

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {

                activity = new Activity(
                        rs.getString("name"),
                        rs.getInt("stressLevel"),
                        rs.getInt("relaxLevel")
                );
                activity.setId( rs.getLong("id") );
                break;
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Could not read the query correctly. [act]");
        }
        close();

        return activity;
    }

    @Override
    public List<Activity> findAllActivities() {
        List<Activity> activities = new ArrayList<>();

        qb.select("*").from("activities");
        String query = qb.getQuery();

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {

                Activity activity = new Activity(
                        rs.getString("name"),
                        rs.getInt("stressLevel"),
                        rs.getInt("relaxLevel")
                );
                activity.setId( rs.getLong("id") );

                activities.add(activity);

                break;
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Could not read the query correctly. [act]");
        }
        close();

        return activities;
    }

    @Override
    public void addDay(Day day) {
        Map<String, String> map = new HashMap<>();
        map.put("date", dateFormat.format(day.getDate()));

        qb.insertInto("days").values(map, true);
        String query = qb.getQuery();

        connect();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            Error.set("Error: running SQL statement [add day]");
            Error.set(e.getMessage());
        }
        close();
    }

    @Override
    public void addActivity(long dayId, Activity activity) {

        // check if day exists
        qb.select("*").from("days").where("id", "=", String.valueOf(dayId));
        String query1 = qb.getQuery();

        List<Day> days = new ArrayList<>();

        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query1);

            while(rs.next()) {

                Day d = new Day();
                d.setDate( dateFormat.parse( rs.getString("date") ) );
                d.setId( rs.getLong("id") );

                days.add(d);
                break;
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Could not read the query correctly. [add act to day :1]");
        } catch (ParseException e) {
            Error.set("Error parsing date.");
        }
        close();

        if(days.size() < 1) {
            Error.set("Day not found!");
            return;
        }

        Map<String, String> map1 = new HashMap<>();

        // add activity
        map1.put("name", activity.getName());
        map1.put("stressLevel", String.valueOf(activity.getStressLevel()));
        map1.put("relaxLevel", String.valueOf(activity.getRelaxLevel()));

        qb.insertInto("activities").values(map1, true);
        String query2 = qb.getQuery();

        connect();
        long activityId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                activityId = rs.getLong(1);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            Error.set("Error: running SQL statement [add act to day :2]");
            Error.set(e.getMessage());
        }
        close();

        Map<String, String> map2 = new HashMap<>();

        // add to merge table
        DayActivity dA = new DayActivity(dayId, activityId);
        map2.put("dayId", String.valueOf(dA.getDayId()));
        map2.put("activityId", String.valueOf(dA.getActivityId()));

        qb.insertInto("dayActivities").values(map2, true);
        String query3 = qb.getQuery();

        connect();
        try {
            PreparedStatement statement = connection.prepareStatement(query3);
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            Error.set("Error: running SQL statement [add act to day :3]");
            Error.set(e.getMessage());
        }
        close();
    }

    public void addActivityToDay(long t_dayId, long t_activityId) {
        Map<String, String> map = new HashMap<>();
        map.put("dayId", String.valueOf(t_dayId));
        map.put("activityId", String.valueOf(t_activityId));

        qb.insertInto("dayActivities").values(map, true);
        String query = qb.getQuery();

        connect();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            Error.set("Error: running SQL statement [add exist act to day]");
            Error.set(e.getMessage());
        }
        close();
    }
}
