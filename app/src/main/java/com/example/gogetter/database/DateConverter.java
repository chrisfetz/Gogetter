package com.example.gogetter.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Converts dates to a format that can be stored in the room database,
 * or converts dates from the database to their original format.
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        if (timestamp == null){
            return null;
        }
        return new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        if (date == null){
            return null;
        }
        return date.getTime();
    }
}
