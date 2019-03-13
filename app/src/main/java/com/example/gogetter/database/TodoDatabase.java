package com.example.gogetter.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import android.util.Log;


/**
 * The abstract class that defines my Room database.
 * Contains a singleton constructor to prevent multiple instances
 * of my database from being created.
 */
@Database(entities =  {TodoTask.class}, version = 2, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class TodoDatabase extends RoomDatabase {

   private static final String TAG = TodoDatabase.class.getSimpleName();
   private static final Object LOCK = new Object();
   private static final String DATABASE_NAME = "gogetterdb";
   private static TodoDatabase sInstance;

   public static TodoDatabase getInstance(Context context){
      if (sInstance == null) {
         synchronized (LOCK) {
            Log.d(TAG, "Creating TodoDatabase instance");
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TodoDatabase.class, TodoDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
         }
      }

      Log.d(TAG, "Getting TodoDatabase instance");
      return sInstance;
   }

   public abstract TodoDao todoDao();
}
