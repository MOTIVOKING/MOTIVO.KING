package de.oszimt.fa45.motivoking.data.type;

/**
 * Created by RedCyberSamurai on 24.11.2016.
 */
public class SqLiteData extends Data {

    private String query = "";


    public SqLiteData() {}


    public String getTableQuery() {
        String query = "";

        query += "CREATE TABLE activities (";
        query += "id            INT PRIMARY KEY NOT NULL, ";
        query += "name          TEXT(50) NOT NULL, ";
        query += "stressLevel   INT(4) NOT NULL, ";
        query += "relaxLevel    INT(4) NOT NULL";
        query += ");";

        query += "CREATE TABLE days (";
        query += "date          DATE NOT NULL, ";
        query += "activities    ARRAY NOT NULL";
        query += ");";

        query += "CREATE TABLE dayActivities (";
        query += "id            INT PRIMARY KEY NOT NULL, ";
        query += "activityId    INT NOT NULL, ";
        query += "dayId         INT NOT NULL";
        query += ");";

        return query;
    }


    public String getQuery() {
        String out = query;
        query = "";

        return out;
    }


    public void select(String props) {
        query += "SELECT " + props;
    }


    public void from(String table) {
        query += " FROM " + table;
    }


    public void where(String target1, String delimiter, String target2) {
        query += " WHERE " + target1 + delimiter + target2;
    }


    public void orderBy(String order) {
        query += " ORDER_BY " + order;
    }
}
