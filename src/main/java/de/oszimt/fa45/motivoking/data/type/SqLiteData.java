package de.oszimt.fa45.motivoking.data.type;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public class SqLiteData extends Data {

    private static String tableQuery;
    private long generatedKey = 0;

    public SqLiteData() {}


    public long getGeneratedKey() {
        long k = generatedKey;
        generatedKey = 0;

        return k;
    }


    public void setGeneratedKey(long t_key) {
        generatedKey = t_key;
    }


    public static void setTableQuery(boolean doDrop) {

        tableQuery = "";

        if(doDrop) {
            tableQuery += "DROP TABLE IF EXISTS activities;";
            tableQuery += "DROP TABLE IF EXISTS days;";
            tableQuery += "DROP TABLE IF EXISTS dayActivities;";
        }

        tableQuery += "CREATE TABLE IF NOT EXISTS activities (";
        tableQuery += "id            INT PRIMARY KEY NOT NULL, ";
        tableQuery += "name          TEXT(50) NOT NULL, ";
        tableQuery += "stressLevel   INT(4) NOT NULL, ";
        tableQuery += "relaxLevel    INT(4) NOT NULL);";

        tableQuery += "CREATE TABLE IF NOT EXISTS days (";
        tableQuery += "date          DATE NOT NULL, ";
        tableQuery += "activities    ARRAY NOT NULL);";

        tableQuery += "CREATE TABLE IF NOT EXISTS dayActivities (";
        tableQuery += "id            INT PRIMARY KEY NOT NULL, ";
        tableQuery += "activityId    INT NOT NULL, ";
        tableQuery += "dayId         INT NOT NULL);";
    }

    public static String getTableQuery() {

        return tableQuery;
    }
}
