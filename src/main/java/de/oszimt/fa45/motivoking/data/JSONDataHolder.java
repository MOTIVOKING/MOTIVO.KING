package de.oszimt.fa45.motivoking.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.data.type.JsonData;
import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.model.DayActivity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<Activity> mActivities;
    private List<DayActivity> mDayActivities;

    public JSONDataHolder() {
        // json container
        mGson = new Gson();

        // data to work with
        mData = this.read();

        // list of days
        mDays = mData.getDays();
        // list of activities
        mActivities = mData.getActivities();
        // merge list of activities and the corresponding days
        mDayActivities = mData.getDayActivities();
    }


    /**
     * Reads the JSON file.
     * @return  A JsonData Model.
     */
    private JsonData read() {
        JsonData data = null;

        // create the file if not existent
        File file = new File(FILE_NAME);
        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // fetch the data of the file
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

        // initialize data model if not set
        if(data == null) {
            data = new JsonData();
        }

        return data;
    }


    /**
     * Saves the changes made.
     * @param in    The json string to write to the file.
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

        if(t_dayId < 1 || mDays.size() < 1) {
            Error.set("Day ID " + t_dayId + " not found.");
            return null;
        }

        return mDays.stream().filter(day -> day.getId() == t_dayId).findFirst().orElse(null);
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

        if(t_dayId < 1 || mDays.size() < 1) {
            Error.set("Day ID " + t_dayId + " not found.");
            return null;
        }

        List<DayActivity> dayActivities = mDayActivities.stream()
                .filter(dayActivity -> dayActivity.getDayId() == t_dayId)
                .collect(Collectors.toList());

        return mActivities.stream()
                .filter(a -> dayActivities.stream()
                        .anyMatch(dA -> dA.getActivityId() == a.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DayActivity> findAllActivities() {
        return mDayActivities;
    }


    /**
     * Adds a day to the JSON file.
     * @param t_day    The day to add.
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
            Error.set("Error: Could not add day.");
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

            if(this.findDayById(t_dayId) != null) {
                t_activity.setId( mData.getAI("activity") );
                mActivities.add(t_activity);

                DayActivity dA = new DayActivity(t_dayId, t_activity.getId());
                mDayActivities.add(dA);

                this.write( mGson.toJson(mData) );
            } else {
                Error.set("Activity not added. No day found.");
                return;
            }

            System.out.printf("Added activity id: %s\n", t_activity.getId());
            return;
        }

        Error.set("Day not found!");
    }

}
