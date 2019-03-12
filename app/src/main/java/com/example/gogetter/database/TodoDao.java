package com.example.gogetter.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * The interface that governs the operations that transform
 * entries in TodoDatabase to TodoTask objects.
 */
@Dao
public interface TodoDao {
    @Query("SELECT * FROM task ORDER BY created_at")
    LiveData<List<TodoTask>> loadAllTasks();

    @Insert
    void insertTask(TodoTask todoTask);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TodoTask todoTask);

    @Delete
    void deleteTask(TodoTask todoTask);

    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<TodoTask> loadTaskById(int id);
}
