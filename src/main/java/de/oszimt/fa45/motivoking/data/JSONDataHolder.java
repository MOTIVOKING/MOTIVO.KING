package de.oszimt.fa45.motivoking.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.model.JsonData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by RedCyberSamurai on 17.10.2016.
 */
public class JSONDataHolder implements DataHolder {
    // target file name
    private final String FILE_NAME = "testfile.json";

    // available members
    private JsonData mData;
    private Gson mGson;
    private List<Day> mDays;

    public JSONDataHolder() {
        mGson = new Gson();

        mData = this.read();
        mDays = mData.getDays();
    }


    /**
     * Reads the JSON file.
     * @return  A JsonData Model.
     */
    private JsonData read() {
        JsonData data = null;

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

            Type dataType = new TypeToken<JsonData>(){}.getType();
            data = mGson.fromJson(jReader, dataType);

            jReader.close();
            fReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(data == null) {
            data = new JsonData();
        }

        return data;
    }


    /**
     * Saves the changes made.
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


    /**
     * Finds the day by given long value.
     * @param t_dayId    The id of the Day entity. The value must be > 1.
     * @return  A Day Model or null.
     */
    public Day findDayById(long t_dayId) {

        if(t_dayId < 1) {
            System.out.printf("Day ID %s not found.", t_dayId);
            return null;
        }

        Day t_day = mDays.stream().filter(day -> day.getId() == t_dayId).findFirst().orElse(null);

        return t_day;
    }


    /**
     * Lists all days.
     * @return  An Array with Day Models.
     */
    @Override
    public List<Day> findAllDays() {
        return mDays;
    }


    /**
     * Lists activities by a given long value
     * @param t_dayId    The id of the Day entity. The value must be > 1.
     * @return  An Array with Activity Models
     */
    @Override
    public List<Activity> findActivitiesByDayId(long t_dayId) {

        if(t_dayId < 1) {
            System.out.printf("Day ID %s not found.", t_dayId);
            return null;
        }

        Day t_day = this.findDayById(t_dayId);

        return t_day.getActivities();
    }


    /**
     * Adds a day to the JSON file.
     * @param t_day
     */
    @Override
    public void addDay(Day t_day) {

        if(t_day != null) {
            Day day = t_day;
            day.setId( mData.getAI("day") );
            mDays.add(day);
            System.out.println("Day added (" + day.getDate().toString() +  ")");

            this.write( mGson.toJson(mData) );
        } else {
            System.out.println("Error: Could not add day.\n");
        }
    }


    /**
     * Adds an Activity to a Day of a given id. The Result will be added to the JSON file.
     * @param t_dayId       The id of the Day entity. The value must be > 1.
     * @param t_activity    The Activity Model to save.
     */
    @Override
    public void addActivity(long t_dayId, Activity t_activity) {

        if(t_dayId > 0 && t_activity != null) {
            Day day = this.findDayById(t_dayId);

            if(day != null) {
                t_activity.setId( mData.getAI("activity") );
                day.setActivity(t_activity);

                this.write( mGson.toJson(mData) );

                System.out.println("Activity: " + t_activity.getName() +
                        ", Stress: " + t_activity.getStressLevel() +
                        ", Relax: " + t_activity.getRelaxLevel()
                );
            } else {
                System.out.println("Activity not added. No day found.\n");
            }
        }

    }
}
