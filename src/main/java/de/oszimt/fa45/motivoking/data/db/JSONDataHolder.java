package de.oszimt.fa45.motivoking.data.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.oszimt.fa45.motivoking.data.DataHolder;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class JSONDataHolder implements DataHolder {
    private Gson gson;

    private final String FILE_NAME = "testfile.json";

    private List<Day> m_days;

    public JSONDataHolder() {
        gson = new Gson();

        m_days = this.read();
    }


    /**
     * Reads the json file
     * @param <T>
     * @return  A list of the given type
     */
    private <T> List<T> read() {
        List<T> out = null;

        File file = new File(FILE_NAME);
        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileReader fReader = new FileReader(FILE_NAME);
            JsonReader jReader = new JsonReader(fReader);

            Type listType = new TypeToken<List<T>>(){}.getType();
            out = gson.fromJson(jReader, listType);

            jReader.close();
            fReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(out == null) {
            out = new ArrayList<>();
        }

        return out;
    }


    /**
     * Saves the changes made
     * @param in
     */
    private void write(String in) {
        try {
            FileWriter fWriter = new FileWriter(FILE_NAME);

            fWriter.write(in);

            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Day findDayById(long dayId) {
        Day t_day = null;

        for(Day day: m_days) {

            if(day.getId() == dayId) {
                t_day = day;
                break;
            }
        }

        return t_day;
    }


    @Override
    public List<Day> findAllDays() {
        return m_days;
    }


    @Override
    public List<Activity> findActivitiesByDayId(long dayId) {
        Day t_day = this.findDayById(dayId);

        return t_day.getActivities();
    }


    @Override
    public void addDay(Day day) {
        m_days.add(day);

        this.write( gson.toJson(m_days) );
    }


    @Override
    public void addActivity(long dayId, Activity activity) {
        Day day = this.findDayById(dayId);
        day.setActivity(activity);

        this.write( gson.toJson(m_days) );
    }
}
