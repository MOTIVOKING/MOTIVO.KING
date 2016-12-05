package de.oszimt.fa45.motivoking.data.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by RedCyberSamurai on 02.12.2016.
 */
public class QueryBuilder {

    private String query;

    public QueryBuilder() {
        query = "";
    }


    public String getQuery() {
        String out = query;
        query = "";

        return out;
    }


    public QueryBuilder select(String props) {

        query += "SELECT " + props;
        return this;
    }


    public QueryBuilder from(String table) {

        query += " FROM " + table;
        return this;
    }


    public QueryBuilder where(String target1, String delimiter, String target2) {
        query += " WHERE " + target1 + delimiter + target2;
        return this;
    }


    public QueryBuilder andWhere(String target1, String delimiter, String target2) {
        query += " AND";
        return where(target1, delimiter, target2);
    }


    public QueryBuilder orderBy(String order) {

        query += " ORDER_BY " + order;
        return this;
    }

    public QueryBuilder insertInto(String table) {

        query += "INSERT INTO " + table;
        return this;
    }

    public QueryBuilder values(Map<String, String> map, boolean isInsert) {

        // ignore id when inserting statement is used
        if(isInsert) {

            map.remove("id");
        }

        List<String> queryKeysList = new ArrayList<>();
        List<String> queryValuesList = new ArrayList<>();

        for(Map.Entry<String, String> m : map.entrySet()) {
            queryKeysList.add(m.getKey());
            queryValuesList.add(m.getValue());
        }

        String queryKeys = queryKeysList.stream().collect(Collectors.joining(","));
        String queryValues = queryValuesList.stream().collect(Collectors.joining(","));

        query += " (" + queryKeys + ") VALUES (" + queryValues + ")";

        return this;
    }
}
