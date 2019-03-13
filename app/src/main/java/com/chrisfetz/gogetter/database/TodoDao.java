package com.chrisfetz.gogetter.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
