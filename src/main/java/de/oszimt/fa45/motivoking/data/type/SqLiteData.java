package de.oszimt.fa45.motivoking.data.type;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public class SqLiteData extends Data {

    private static String tableQuery;

    public SqLiteData() {}


    public static void setTableQuery(boolean doDrop) {

        tableQuery = "";

        if(doDrop) {
            tableQuery += "DROP TABLE IF EXISTS activities;";
            tableQuery += "DROP TABLE IF EXISTS days;";
            tableQuery += "DROP TABLE IF EXISTS dayActivities;";
        }

        tableQuery += "CREATE TABLE IF NOT EXISTS activities (";
        tableQuery += "id            INTEGER PRIMARY KEY AUTOINCREMENT, ";
        tableQuery += "name          TEXT(50) NOT NULL, ";
        tableQuery += "stressLevel   INT(4) NOT NULL, ";
        tableQuery += "relaxLevel    INT(4) NOT NULL);";

        tableQuery += "CREATE TABLE IF NOT EXISTS days (";
        tableQuery += "id            INTEGER PRIMARY KEY AUTOINCREMENT, ";
        tableQuery += "date          DATE NOT NULL);";

        tableQuery += "CREATE TABLE IF NOT EXISTS dayActivities (";
        tableQuery += "id            INTEGER PRIMARY KEY AUTOINCREMENT, ";
        tableQuery += "activityId    INT NOT NULL, ";
        tableQuery += "dayId         INT NOT NULL);";
    }

    public static String getTableQuery() {

        return tableQuery;
    }
}
