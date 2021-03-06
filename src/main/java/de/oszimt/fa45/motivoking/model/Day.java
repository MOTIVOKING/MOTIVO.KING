package de.oszimt.fa45.motivoking.model;

import java.util.Date;

/**
 * Created by boerg on 13.10.2016.
 */
public class Day extends Model {

    private Date date;


    /**
     * Initializes the day of the current date.
     */
    public Day() {
        new Day( new Date() );
    }


    /**
     * Initializes the day with a specific date.
     * @param t_date    The date to set.
     */
    public Day(Date t_date) {
        date = t_date;
        setId(0);
    }


    /**
     * Gets the date of the day.
     * @return  Date with date value.
     */
    public Date getDate() {
        return date;
    }


    /**
     * Sets the date of the day.
     * @param t_date    The date to set.
     */
    public void setDate(Date t_date) {
        date = t_date;
    }

    @Override
    public String toString() {
        return date == null ? "error" : date.toString();
    }
}
